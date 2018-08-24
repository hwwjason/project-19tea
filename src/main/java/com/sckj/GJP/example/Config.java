package com.sckj.GJP.example;


public class Config {
	public static String api_link = "http://ca.mygjp.com:8010/api";
	public static String companyName = "TestMall";
	public static String userName = "test";
	public static String userpass = "grasp@101";
	public static String appkey = "68943923115886070418838901844741";
	public static String app_secret = "ONxYDyNaCoyTzsp83JoQ3YYuMPHxk3j7";
	public static String sign_key = "lezitiancheng";
	public static String get_token_url = "http://ca.mygjp.com:8010/api/token";
	public static String redirect_url =  "http://localhost:8080/GetToken/GetToken.jsp";
	public static String auth_code_url = String.format("%sappkey=%s&redirect_url=%s&keyword=test", "http://ca.mygjp.com:8012/account/login?",appkey,redirect_url);
	public static String shop_key = "e4994811-bbb8-44cd-86cb-328b263be7e0";
	public static String token = "zK4f4hRxpwehi651mBPM3gkeQEqw2BH96SG32kOr";


	public String GetAuthCodeUrl(String redirect_url)
	{
		String ret = String.format("%sappkey=%s&redirect_url=%s&keyword=test", "http://ca.mygjp.com:8012/account/login?",appkey,redirect_url); ;

		return ret;
	}
}
