package com.sckj.GJP.example;


import com.sckj.constant.GJPTESTConstants;
import com.sckj.constant.GJPTESTConstants;

public class Config {

	public static String api_link = GJPTESTConstants.API_LINK;


	public static String appkey = GJPTESTConstants.APP_KEY;
	public static String app_secret = GJPTESTConstants.APP_SECRET;
	public static String sign_key = GJPTESTConstants.SIGN_KEY;

	public static String get_token_url = GJPTESTConstants.GET_TOKEN_URL;
	public static String redirect_url =  GJPTESTConstants.REDIRECT_URL;
	public static String login_code = GJPTESTConstants.LOGIN_CODE;
	public static String shop_key = GJPTESTConstants.SHOP_KEY;
	public static String auth_code = GJPTESTConstants.AUTH_CODE;
	public static String token = GJPTESTConstants.TOKEN;

	public static String auth_code_url = String.format("%sappkey=%s&redirect_url=%s&keyword=test", login_code,appkey,redirect_url);

	public String GetAuthCodeUrl(String redirect_url) throws Exception {
		return auth_code_url;
	}
}
