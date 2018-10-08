package com.sckj.helper;

import com.sckj.GJP.example.AESCoder;
import com.sckj.GJP.example.Config;
import com.sckj.GJP.example.HttpRequest;
import com.sckj.model.GJP.GJPRequest.GJPBaseRequest;
import com.sckj.model.GJP.GJPRequest.GJPProductinfoRequest;
import net.sf.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GJPHelp {

    public static   String sendPost(GJPBaseRequest gjpBaseRequest,Map<String, String> param)  throws Exception
    {

        String ret = "";
        //业务参数获取
        //接口调用参数组装
        try {
            param = getPostParams(gjpBaseRequest, Config.token, Config.appkey, Config.sign_key,param);
            //post报文
            String postString = "";
            for (String in : param.keySet()) {
                postString += in + "=" + URLEncoder.encode(param.get(in),"utf-8")  +"&";
//                postString += in + "=" + param.get(in)  +"&";
            }
            postString = postString.substring(0, postString.length() - 1);
            ret =  HttpRequest.sendPost(Config.api_link, postString);
            System.out.println("sendPost finish");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ret;
    }

    private  static  Map<String, String> getPostParams(GJPBaseRequest baseRequest, String token, String appKey, String signKey, Map<String, String> txtParams) throws NoSuchAlgorithmException
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        Date now = new Date();
        String time = dateFormat.format(now);

        //添加业务参数
        buildOrderParams(baseRequest, txtParams);
        // 添加系统参数
        txtParams.put("method", baseRequest.getApiName());
        txtParams.put("appkey", appKey);
        txtParams.put("timestamp", time);
        txtParams.put("token", token);
        //签名参数
        AESCoder coder = new AESCoder();
        txtParams.put("sign", coder.SignRequest(txtParams, signKey));
        return txtParams;
    }

    private static   void buildOrderParams(GJPBaseRequest baseRequest, Map<String, String> parameters)
    {
        parameters.put("shopkey", Config.shop_key);
        //parameters.put("orders",  JSONArray.fromObject(baseRequest.baseModels).toString());
    }

    public static  GJPProductinfoRequest getProductinfoRequest()
    {
        GJPProductinfoRequest productinfoRequest = new GJPProductinfoRequest();
        return productinfoRequest;
    }

    @Test
    public static void test(){
        try {
            Map<String, String> param = new HashMap<>();
            sendPost(getProductinfoRequest(),param);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
