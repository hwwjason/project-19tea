package com.sckj.GJP.example;


import com.sckj.constant.GJPConstants;
import com.sckj.constant.GJPConstants;

public class Config {

	public static String api_link = GJPConstants.API_LINK;


	public static String appkey = GJPConstants.APP_KEY;
	public static String app_secret = GJPConstants.APP_SECRET;
	public static String sign_key = GJPConstants.SIGN_KEY;

	public static String get_token_url = GJPConstants.GET_TOKEN_URL;
	public static String redirect_url =  GJPConstants.REDIRECT_URL;
	public static String login_code = GJPConstants.LOGIN_CODE;
	public static String shop_key = GJPConstants.SHOP_KEY;
	public static String auth_code = GJPConstants.AUTH_CODE;
	public static String token = GJPConstants.TOKEN;

	public static String auth_code_url = String.format("%sappkey=%s&redirect_url=%s&keyword=test", login_code,appkey,redirect_url);

	public String GetAuthCodeUrl(String redirect_url) throws Exception {
		return auth_code_url;
	}
}
