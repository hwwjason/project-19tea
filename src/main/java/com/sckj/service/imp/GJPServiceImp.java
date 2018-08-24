package com.sckj.service.imp;

import com.sckj.pojo.ProductList;
import com.sckj.service.IGJPService;
import com.sckj.utils.StringUtils;
import com.sckj.utils.UUIDUtils;
import org.apache.http.protocol.HttpContext;

public class GJPServiceImp implements IGJPService {

    //测试环境
    private static final String appKey = "68943923115886070418838901844741";
    private static final String signKey = "lezitiancheng";
   // private static final String apiUrl = System.Configuration.ConfigurationSettings.AppSettings["tokenUrl"] + "api/";
    private static final String shopKey = "e7c5289b-07ee-492f-b84f-97ee9659c8b7";

    //获取商品信息列表
    public void Productinfo()
    {

    }


    public String GetGJPToken()
    {
        String tokent = "";
        return "";
    }



}
