package com.sckj.utils.weixin;

import com.sckj.constant.MiniAppConstants;

/**
 * @Description:
 * @Date: 2018/4/8
 * @Author: wcf
 */
public class WxPayConfig {
    //小程序appid
    public static final String appid = MiniAppConstants.APP_ID;
    //微信支付的商户id
    public static final String mch_id = MiniAppConstants.MCH_ID;
    //微信支付的商户密钥
    public static final String key = MiniAppConstants.APP_SECRET;
    //支付成功后的服务器回调url
    public static final String notify_url = String.format("https://%s/weixin/wxNotify",MiniAppConstants.URL);
    //签名方式
    public static final String SIGNTYPE = "MD5";
    //交易类型
    public static final String TRADETYPE = "JSAPI";
    //微信统一下单接口地址
    public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
