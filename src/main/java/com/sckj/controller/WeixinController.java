package com.sckj.controller;

import com.sckj.common.ResultData;
import com.sckj.constant.MessageConstants;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.exception.BusinessException;
import com.sckj.service.IWeiXinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.weixin4j.WeixinException;
import org.weixin4j.WeixinSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@RequestMapping("/weixin")
@RestController
public class WeixinController extends WeixinSupport{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IWeiXinService weiXinService;

    /**
     * 小程序后台登录，向微信平台发送获取access_token请求，并返回openId
     *
     * @param code
     * @return openid
     * @throws WeixinException
     * @throws IOException
     * @since Weixin4J 1.0.0
     */
    @RequestMapping("/login")
    public ResultData login(String code, HttpServletRequest request) throws WeixinException, IOException {
        try {
            ResultData resultData = new ResultData();
            resultData.setData( weiXinService.login(code,request));
            return resultData;
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            logger.error("Error 登录失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
     * @Description: 发起微信支付
     * @param orderId
     * @return
     */
    @RequestMapping("/wxToPay")
    public ResultData wxToPay(String orderId) throws Exception {
        try {
            ResultData resultData = new ResultData();
            resultData.setData( weiXinService.wxToPay(orderId));
            return resultData;
        }catch (BusinessException e){
            logger.error(e.getMessage());
            throw e;
        } catch (Exception e){
            logger.error("Error 发起微信支付失败", e);
            throw e;
        }
    }

    /**
     * @Description: 发起微信支付
     * @param buyuserId
     * @param cartType
     * @param couponUserid
     * @param request
     * @return
     */
    @RequestMapping("/wxPay")
    public ResultData wxPay(String buyuserId, String cartType, String couponUserid, String userRemark ,HttpServletRequest request)  {
        try {
            ResultData resultData = new ResultData();
            resultData.setData( weiXinService.wxPay(buyuserId,cartType,couponUserid,userRemark,request));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 发起微信支付失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
     * @Description:微信支付回调
     * @return
     * @author dzg
     * @throws Exception
     * @throws WeixinException
     * @date 2016年12月2日
     */
    @RequestMapping(value="/wxNotify")
    public void wxNotify(HttpServletRequest request,HttpServletResponse response) throws Exception{
        try {
            weiXinService.wxNotify(request,response);
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            logger.error("Error 微信支付失败", e);
        }
    }

    /**
     * @Description:退款
     * @return
     * @author dzg
     * @throws Exception
     * @throws WeixinException
     * @date 2016年12月2日
     */
    @RequestMapping(value="/wxRefund")
    public void wxApplyRefund(String buyuserId, String orderId ,HttpServletRequest request)  {
        try {
            weiXinService.wxApplyRefund(buyuserId,orderId,request);
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            logger.error("Error 微信支付失败", e);
        }
    }
}
