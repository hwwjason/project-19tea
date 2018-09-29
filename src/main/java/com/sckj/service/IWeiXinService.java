package com.sckj.service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sckj.utils.weixin.OAuthJsToken;
import org.weixin4j.WeixinException;
import org.weixin4j.http.HttpsClient;
import org.weixin4j.http.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public interface IWeiXinService {

    Map<String, Object> login(String code, HttpServletRequest request) throws WeixinException, IOException;

    /**
     * @Description: 发起微信支付
     * @param buyuserId
     * @param cartType
     * @param couponUserid
     * @param request
     * @return
     */
    Map<String, Object> wxPay(String buyuserId, String cartType, String couponUserid, String userRemark, HttpServletRequest request);

    /**
     * 微信支付(回调)
     * @param request
     * @param response
     * @throws Exception
     */
    void wxNotify(HttpServletRequest request,HttpServletResponse response) throws Exception;
}
