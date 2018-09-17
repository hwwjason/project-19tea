package com.sckj.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sckj.common.ResultData;
import com.sckj.constant.MiniAppConstants;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.exception.BusinessException;
import com.sckj.model.*;
import com.sckj.model.dto.ProductOrderDTO;
import com.sckj.repository.*;
import com.sckj.service.ICouponService;
import com.sckj.service.ICouponUserService;
import com.sckj.service.IProductOrderService;
import com.sckj.service.IWeiXinService;
import com.sckj.utils.BeanUtils;
import com.sckj.utils.weixin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.weixin4j.WeixinException;
import org.weixin4j.WeixinSupport;
import org.weixin4j.http.HttpsClient;
import org.weixin4j.http.Response;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeiXinServiceImp extends WeixinSupport implements IWeiXinService{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String appid = MiniAppConstants.APP_ID;	    //微信小程序appid
    private static final String secret = MiniAppConstants.APP_SECRET;	//微信小程序密钥
    private static final String grant_type = "authorization_code";

    @Autowired
    private UserListJpa userListJpa;

    @Autowired
    private IProductOrderService productOrderService;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private ProductSonOrderRepository productSonOrderRepository;

    @Autowired
    private ICouponUserService couponUserService;

    /**
     * 小程序后台登录，向微信平台发送获取access_token请求，并返回openId
     * @param code
     * @param request
     * @return
     * @throws WeixinException
     * @throws IOException
     */
    public Map<String, Object> login(String code, HttpServletRequest request) throws WeixinException, IOException {
        if (code == null || code.equals("")) {
            throw new WeixinException("invalid null, code is null.");
        }

        Map<String, Object> ret = new HashMap<String, Object>();
        //拼接参数
        String param = "?grant_type=" + grant_type + "&appid=" + appid + "&secret=" + secret + "&js_code=" + code;

        //创建请求对象
        HttpsClient http = new HttpsClient();
        //调用获取access_token接口
        Response res = http.get("https://api.weixin.qq.com/sns/jscode2session" + param);
        //根据请求结果判定，是否验证成功
        JSONObject jsonObj = res.asJSONObject();
        if (jsonObj != null) {
            Object errcode = jsonObj.get("errcode");
            if (errcode != null) {
                //返回异常信息
                throw new WeixinException(getCause(Integer.parseInt(errcode.toString())));
            }

            ObjectMapper mapper = new ObjectMapper();
            OAuthJsToken oauthJsToken = mapper.readValue(jsonObj.toJSONString(),OAuthJsToken.class);

            logger.info("openid=" + oauthJsToken.getOpenid());
            ret.put("openid", oauthJsToken.getOpenid());
        }
        return ret;
    }

    /**
     * @Description: 发起微信支付
     * @param buyuserId
     * @param cartType
     * @param couponUserid
     * @param request
     * @return
     */
    public  Map<String, Object> wxPay(String buyuserId, String cartType, String couponUserid, HttpServletRequest request)  {
        ProductOrderDTO productOrderDTO = new ProductOrderDTO();
        productOrderDTO.setBuyuserId(buyuserId);
        productOrderDTO.setCartType(cartType);
        productOrderDTO.setCouponUserid(couponUserid);
        try {
            productOrderDTO = productOrderService.createTempProductOrder(productOrderDTO);
        } catch (Exception e) {
            throw new BusinessException("创建订单失败，请联系管理员");
        }

        List<UserList> userLists = userListJpa.findByUserId(buyuserId);
        String openid = userLists.get(0).getOpenid();

        try{
            //生成的随机字符串
            String nonce_str = StringUtils.getRandomStringByLength(32);
            //商品名称
            String body = "19Tea茶研社订单-"+productOrderDTO.getId();
            //获取本机的ip地址
            String spbill_create_ip = IpUtils.getIpAddr(request);

            String orderNo = productOrderDTO.getId();
            String money = "1";//支付金额，单位：分，这边需要转成字符串类型，否则后面的签名会失败

            Map<String, String> packageParams = new HashMap<String, String>();
            packageParams.put("appid", WxPayConfig.appid);
            packageParams.put("mch_id", WxPayConfig.mch_id);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("body", body);
            packageParams.put("out_trade_no", orderNo);//商户订单号
            packageParams.put("total_fee", money);//支付金额，这边需要转成字符串类型，否则后面的签名会失败
            packageParams.put("spbill_create_ip", spbill_create_ip);
            packageParams.put("notify_url", WxPayConfig.notify_url);
            packageParams.put("trade_type", WxPayConfig.TRADETYPE);
            packageParams.put("openid", openid);

            // 除去数组中的空值和签名参数
            packageParams = PayUtil.paraFilter(packageParams);
            String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串

            //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
            String mysign = PayUtil.sign(prestr, WxPayConfig.key, "utf-8").toUpperCase();
            logger.info("=======================第一次签名：" + mysign + "=====================");

            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
            String xml = "<xml>" + "<appid>" + WxPayConfig.appid + "</appid>"
                    + "<body><![CDATA[" + body + "]]></body>"
                    + "<mch_id>" + WxPayConfig.mch_id + "</mch_id>"
                    + "<nonce_str>" + nonce_str + "</nonce_str>"
                    + "<notify_url>" + WxPayConfig.notify_url + "</notify_url>"
                    + "<openid>" + openid + "</openid>"
                    + "<out_trade_no>" + orderNo + "</out_trade_no>"
                    + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
                    + "<total_fee>" + money + "</total_fee>"
                    + "<trade_type>" + WxPayConfig.TRADETYPE + "</trade_type>"
                    + "<sign>" + mysign + "</sign>"
                    + "</xml>";

            System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);

            //调用统一下单接口，并接受返回的结果
            String result = PayUtil.httpRequest(WxPayConfig.pay_url, "POST", xml);

            System.out.println("调试模式_统一下单接口 返回XML数据：" + result);

            // 将解析结果存储在HashMap中
            Map map = PayUtil.doXMLParse(result);

            String return_code = (String) map.get("return_code");//返回状态码

            //返回给移动端需要的参数
            Map<String, Object> response = new HashMap<String, Object>();
            if(return_code == "SUCCESS" || return_code.equals(return_code)){
                // 业务结果
                String prepay_id = (String) map.get("prepay_id");//返回的预付单信息
                response.put("nonceStr", nonce_str);
                response.put("package", "prepay_id=" + prepay_id);
                Long timeStamp = System.currentTimeMillis() / 1000;
                response.put("timeStamp", timeStamp + "");//这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误

                String stringSignTemp = "appId=" + WxPayConfig.appid + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id+ "&signType=" + WxPayConfig.SIGNTYPE + "&timeStamp=" + timeStamp;
                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
                String paySign = PayUtil.sign(stringSignTemp, WxPayConfig.key, "utf-8").toUpperCase();
                logger.info("=======================第二次签名：" + paySign + "=====================");

                response.put("paySign", paySign);

                //保存订单和子订单的
                ProductOrder productOrder = new ProductOrder();
                BeanUtils.copyProperties(productOrder,productOrderDTO);
                productOrderRepository.saveAndFlush(productOrder);
                List<ProductSonOrder> productSonOrders = productOrderDTO.getProductSonOrder();
                productSonOrderRepository.saveAll(productSonOrders);
                //扣除库存
                Map<String, ProductList> productListMap = productOrderDTO.getProductListMap();
                List<ProductList> productLists = new ArrayList<>();
                for (ProductSonOrder sonOrder : productSonOrders) {
                    ProductList productList = productListMap.get(sonOrder.getProductid());
                    int sellNum = productList.getSellNum() == null ? 0 : productList.getSellNum();
                    int stock = productList.getStock()==null? 0 : productList.getStock();
                    int buyNum = sonOrder.getBuynum();
                    if(stock - buyNum < 0){
                        throw new BusinessException("商品:"+productList.getTitle()+"："+productList.getCode()+",库存不足，请联系管理员");
                    }
                    productList.setSellNum(sellNum + buyNum);
                    productList.setStock(stock - buyNum);
                }
                //同步库存到管家婆

                //改变优惠券状态
                String couPonUserid = productOrderDTO.getCouponUserid();
                if(com.sckj.utils.StringUtils.isNotEmpty(couponUserid)){
                    CouponUser couponUser = couponUserService.findDTOById(couPonUserid);
                    couponUser.setIsuse("1");
                    couponUserService.saveAndFlush(couponUser);
                }

            }

            response.put("appid", WxPayConfig.appid);

            //resultData.setData(response);
            return response;
        }catch(Exception e){
            e.printStackTrace();
            throw new BusinessException("发起支付失败,请联系管理员:"+e);
        }
    }

    /**
     * 微信支付(回调)
     * @param request
     * @param response
     * @throws Exception
     */
    public void wxNotify(HttpServletRequest request,HttpServletResponse response) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        br.close();
        //sb为微信返回的xml
        String notityXml = sb.toString();
        String resXml = "";
        System.out.println("接收到的报文：" + notityXml);

        Map map = PayUtil.doXMLParse(notityXml);

        String returnCode = (String) map.get("return_code");
        if("SUCCESS".equals(returnCode)){
            //验证签名是否正确
            if(PayUtil.verify(PayUtil.createLinkString(map), (String)map.get("sign"), WxPayConfig.key, "utf-8")){
                /**此处添加自己的业务逻辑代码start**/


                /**此处添加自己的业务逻辑代码end**/

                //通知微信服务器已经支付成功
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            }
        }else{
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        System.out.println(resXml);
        System.out.println("微信支付回调数据结束");

        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }
}
