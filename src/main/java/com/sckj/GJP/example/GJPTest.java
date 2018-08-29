package com.sckj.GJP.example;


import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.jupiter.api.Test;

public class GJPTest {

    @Test
    public void getAuthCode() throws Exception{

//        公司名称：TestMall
//        用户名：test
//        密码：grasp@101
//        String CompanyName = "TestMall";
//        String UserName = "TestMall";
//        String PassWord = "grasp@101";
//
//        String canShu = "";
//        String result = HttpRequest.sendPost(Config.auth_code_url, canShu);
//        System.out.println(result);
//
        GetToken getToken = new GetToken();
        getToken.DoGetToken("");



    }
}
