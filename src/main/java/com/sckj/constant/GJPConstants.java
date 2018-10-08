package com.sckj.constant;

import com.sckj.utils.HttpUtils;

public class GJPConstants {
    //管家婆接口参数
    public static final String APP_KEY = "10651245836115605605707684031430";
    public static final String APP_SECRET = "2ACKNJTXmQ6aGKFztrMatfGQrg7XCMwi";
    public static final String SIGN_KEY = "Sa6WFjAwRqZdAc4vGbAG";

    public static final String SHOP_KEY = "743d2311-221e-427b-8506-86ca1c96d678";//e4994811-bbb8-44cd-86cb-328b263be7e0

    public static final String GET_PARA_URL = "https://authcentral.wsgjp.com/AppKey/ApplyAppkey";//参数申请和查看地址
    public static final String LOGIN_CODE = "https://authcentral.wsgjp.com/account/login?";//授权获取授权认证码访问地址
    public static final String GET_TOKEN_URL = "http://apigateway.wsgjp.com.cn/api/token";
    public static final String API_LINK = "http://apigateway.wsgjp.com.cn/api/";
    public static final String REDIRECT_URL = "https://"+ HttpUtils.getServerUrl()+"/GetToken.jsp";


    public static final String ERP_LOGIN_URL = "http://login.wsgjp.com/";//erp登录地址
    public static final String COMPANY_NAME = "上海茶缘禅舍茶业有限公司";
    public static final String USER_NAME = "周榆";
    public static final String PASSWORD = "zhouyu828..";

    public static final String AUTH_CODE="szNyOVU6mONmznv5tJWApj36sHVtDzzb";

    public static final String TOKEN = "Q4lz8Grnd7l8ti3DTeJ4RjL9L1pupePALLxEu120";
    public static final String REFRESH_TOKEN = "16hI6QXoCncFcrYz0kikY47e5U0Ozn6NMBPlaoRB";


}
