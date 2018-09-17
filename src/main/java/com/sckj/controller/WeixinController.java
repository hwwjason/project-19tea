package com.sckj.controller;

import com.alibaba.fastjson.JSONObject;
import com.sckj.common.ResultData;
import com.sckj.constant.MessageConstants;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.exception.BusinessException;
import com.sckj.model.UserList;
import com.sckj.model.dto.ProductOrderDTO;
import com.sckj.repository.UserListJpa;
import com.sckj.service.IProductOrderService;
import com.sckj.service.IWeiXinService;
import com.sckj.utils.weixin.IpUtils;
import com.sckj.utils.weixin.StringUtils;
import com.sckj.utils.weixin.PayUtil;
import com.sckj.utils.weixin.WxPayConfig;
import com.sckj.utils.weixin.OAuthJsToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sckj.constant.MiniAppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@RequestMapping("/weixin")
@RestController
public class WeixinController extends WeixinSupport{

    private Logger logger = LoggerFactory.getLogger(getClass());

//    private static final String appid = MiniAppConstants.APP_ID;	    //微信小程序appid
//    private static final String secret = MiniAppConstants.APP_SECRET;	//微信小程序密钥
//    private static final String grant_type = "authorization_code";
//
//    @Autowired
//    private  UserListJpa userListJpa;
//
//    @Autowired
//    private IProductOrderService productOrderService;

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
        } catch (Exception e){
            logger.error("Error 登录失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
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
    public ResultData wxPay(String buyuserId, String cartType, String couponUserid, HttpServletRequest request)  {
        try {
            ResultData resultData = new ResultData();
            resultData.setData( weiXinService.wxPay(buyuserId,cartType,couponUserid,request));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 发起微信支付失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
     * @Description:微信支付
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
        } catch (Exception e){
            logger.error("Error 微信支付失败", e);
        }
    }
}
