package com.sckj.GJP.example;


public class Config {

//	1，app key：68943923115886070418838901844741
//			2，app secret ：ONxYDyNaCoyTzsp83JoQ3YYuMPHxk3j7
//3，sign key：lezitiancheng

//	线上参数申请和查看地址  https://authcentral.wsgjp.com/AppKey/ApplyAppkey
//	线上环境erp登录地址：http://login.wsgjp.com/
//	线上授权获取授权认证码访问地址： https://authcentral.wsgjp.com/account/login?
//	线上获取token信息时请访问地址 http://apigateway.wsgjp.com.cn/api/token
//	线上接口地址： http://apigateway.wsgjp.com.cn/api/


//	接下来 利用授权认证码获取token信息。获取token信息时请访问地址 http://ca.mygjp.com:8002/api/token
	//授权测试时获取授权认证码请访问地址： http://ca.mygjp.com:666/account/login?
//	测试环境erp登录地址：http://ca.mygjp.com:8001/



	public static String api_link = "http://ca.mygjp.com:8010/api";
	public static String companyName = "TestMall";
	public static String userName = "test";
	public static String userpass = "grasp@101";
	public static String appkey = "68943923115886070418838901844741";
	public static String app_secret = "ONxYDyNaCoyTzsp83JoQ3YYuMPHxk3j7";
	public static String sign_key = "lezitiancheng";
//	public static String get_token_url = "http://ca.mygjp.com:8010/api/token";
	public static String get_token_url = "http://ca.mygjp.com:8002/api/token";
	public static String redirect_url =  "http://localhost:8081/GetToken.jsp";
	public static String auth_code_url = String.format("%sappkey=%s&redirect_url=%s&keyword=test", "http://ca.mygjp.com:8001/account/login?",appkey,redirect_url);
	public static String shop_key = "e4994811-bbb8-44cd-86cb-328b263be7e0";
	public static String token = "zK4f4hRxpwehi651mBPM3gkeQEqw2BH96SG32kOr";


	public String GetAuthCodeUrl(String redirect_url)
	{
//		String ret = String.format("%sappkey=%s&redirect_url=%s&keyword=test", "http://ca.mygjp.com:8012/account/login?",appkey,redirect_url); ;

		String ret = String.format("%sappkey=%s&redirect_url=%s&keyword=test", "http://ca.mygjp.com:8001/account/login?",appkey,redirect_url); ;
		return ret;
	}
}
