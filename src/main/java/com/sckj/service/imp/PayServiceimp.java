package com.sckj.service.imp;

//import com.github.binarywang.wxpay.config.WxPayConfig;
//import com.github.binarywang.wxpay.service.WxPayService;
//import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
//import com.sckj.constant.PayConstant;
import com.sckj.constant.MiniAppConstants;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

public class PayServiceimp {

    @Test
    private void test(){
//        WxPayConfig payConfig = new WxPayConfig();
//        WxPayService payService = new WxPayServiceImpl();
//        payService.setConfig(payConfig);

        //第一步：对参数按照key=value的格式，并按照参数名ASCII字典序排序如下：
        String stringA = String.format("appid=%s&body=%s&device_info=%s&mch_id=%s&nonce_str=%s", MiniAppConstants.APP_ID,MiniAppConstants.BODY,
                MiniAppConstants.DEVICE_INFO,MiniAppConstants.MCH_ID,MiniAppConstants.NONCE_STR);

        System.out.println("字符串："+stringA);

        //第二步：拼接API密钥：
        String stringSignTemp=stringA+"&key=192006250b4c09247ec02edce69f6a2d"; //注：key为商户平台设置的密钥key

        String sign= DigestUtils.md5Hex(stringSignTemp).toUpperCase();//="9A0A8659F005D6984697E2CA0A9CF3B7";//注：MD5签名方式


        //sign=hash_hmac("sha256",stringSignTemp,key).toUpperCase()="6A9AE1657590FD6257D693A078E1C3E4BB6BA4DC30B23E0EE2496E54170DACD6"; //注：HMAC-SHA256签名方式
    }
}
