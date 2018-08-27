package com.sckj.GJP.tool.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sckj.GJP.tool.entity.RsModel;
import com.sckj.GJP.tool.util.AESUtil;
import com.sckj.GJP.tool.util.HttpsClientUtil;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;



import com.google.gson.Gson;



public class Test {

	static String appkey="68943923115886070418838901844741";
	static String secretStr="ONxYDyNaCoyTzsp83JoQ3YYuMPHxk3j7";
	static String auth_code="yYvJPGsh7woXgl1mRUS9CRKD5dDdnCdn";
	static String signKey="lezitiancheng";
	static String algorithmstr="AES/CBC/PKCS7Padding";


	static String tokenUrl= "http://ca.mygjp.com:8002/api/token";
	public static void getToken() throws Exception{
		//获取token接口
		//第一步   获取P参数
		LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
		map.put("TimeStamp", getCurTime());
		map.put("GrantType", "auth_token");
		map.put("AuthParam", auth_code);


		Gson  gson =new Gson();
		String mapjoson= gson.toJson(map);
		System.out.println("=====获取token接口======="+mapjoson);


		String iv = secretStr.substring(5,21);
		AESUtil  AESUtil =new AESUtil();
		String p = AESUtil.AESencrypt(mapjoson, secretStr, iv, algorithmstr);
		System.out.println("=====AES加密后的字符串=获得的p参数的值======"+p);

		System.out.println("=====AES加解密密后的字符串=获得的p参数的值======"+ AESUtil.AESdecrypt(p, secretStr, iv, algorithmstr));

		//第二步   获取SignKey
		LinkedHashMap<String,String> mapSign = new LinkedHashMap<String,String>();
		//Map mapSign = new HashMap();
		mapSign.put("appkey", appkey);
		mapSign.put("p", p);
		mapSign.put("signkey", signKey);
		String signStr = gson.toJson(mapSign);
		System.out.println("=====SignKey的值======"+signStr);
		AESUtil  AESUtil2 =new AESUtil();

		String signRst = AESUtil2.SHAEncrypt_Apache(signStr);
		System.out.println("=====SHA加密后signRst的值======"+signRst);

		//第三步   调用接口
		HttpClient http = new DefaultHttpClient();
		//http= new HttpsClientUtil().wrapClient(http);
		System.setProperty("javax.net.debug","ssl");

		http.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
		List<NameValuePair>  nvps = new ArrayList<NameValuePair>();
		nvps.add( new BasicNameValuePair("appkey", appkey));
		nvps.add( new BasicNameValuePair("p", p));
		nvps.add( new BasicNameValuePair("sign", signRst));

		HttpPost method = new HttpPost(tokenUrl);
		method.setEntity(new UrlEncodedFormEntity(nvps));

		HttpResponse  reponse = http.execute(method);
		String reponseStr = EntityUtils.toString(reponse.getEntity());
		System.out.println("=====调用获取token接口返回======"+reponseStr);

		RsModel rspModel = gson.fromJson(reponseStr, RsModel.class);

		Map responseMap = rspModel.getResponse();
		String resultResponse =responseMap.get("response").toString();
		String responseJson  = AESUtil.AESdecrypt(resultResponse, secretStr, iv, algorithmstr);
		System.out.println("======"+responseJson);
	}


	public static void main(String[] args) throws Exception {
		getToken() ;
		//refreshToken("111");
		//getCurTime();
//		AESUtil  AESUtil =new AESUtil();
//String srt = "0123456789";
//System.out.println(AESUtil.SHAEncrypt(srt));
//		
		//String Str = "{\"signkey\":\"hrbb-LOyLg3I6dKoE3PG5\",\"p\":\"p8L/djSRmYVcj83X8vylvrEtscBdVAGLjbIm6OTjOWLj1n9+M9XLDiiDy0mwcvBWOdbc4KTUV0QG0hiNyl0Cxg+HegYZr98Ep5dmYDupzu8d5Mfk5x+Eym0Wx24H2vB3Tor7Bjl52O99MAzDFY0Vpg\u003d\u003d\",\"appkey\":\"30378877211223672178040181303135\"}";

	}
	public static void refreshToken(String token_str) throws Exception{
		//获取token接口
		//第一步   获取P参数
		Map map = new HashMap();
		map.put("TimeStamp", getCurTime());
		map.put("GrantType", "refresh_token");
		map.put("AuthParam", token_str);

		Gson  gson =new Gson();
		String mapjoson= gson.toJson(map);
		System.out.println("=====获取token接口======="+mapjoson);


		String iv = secretStr.substring(5,21);
		AESUtil AESUtil =new AESUtil();
		String p = AESUtil.AESencrypt(mapjoson, secretStr, iv, algorithmstr);
		System.out.println("=====AES加密后的字符串=获得的p参数的值======"+p);
		//第二步   获取SignKey
		Map mapSign = new HashMap();
		mapSign.put("appkey", appkey);
		mapSign.put("p", p);
		mapSign.put("signkey", signKey);
		String signStr = gson.toJson(mapSign);
		System.out.println("=====SignKey的值======"+signStr);

		String signRst = AESUtil.SHAEncrypt_Java(signStr);
		System.out.println("=====SHA加密后SignKey的值======"+signRst);

		//第三步   调用接口
		HttpClient http = new DefaultHttpClient();
		http= new HttpsClientUtil().wrapClient(http);

		http.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
		List<NameValuePair>  nvps = new ArrayList<NameValuePair>();
		nvps.add( new BasicNameValuePair("appkey", appkey));
		nvps.add( new BasicNameValuePair("sign", signRst));
		nvps.add( new BasicNameValuePair("p", p));
		System.setProperty("javax.net.debug","ssl");
		HttpPost method = new HttpPost(tokenUrl);
		method.setEntity(new UrlEncodedFormEntity(nvps));

		HttpResponse  reponse = http.execute(method);
		String reponseStr = EntityUtils.toString(reponse.getEntity());
		System.out.println("=====调用获取token接口返回======"+reponseStr);


		RsModel rspModel = gson.fromJson(reponseStr, RsModel.class);

		Map responseMap = rspModel.getResponse();
		String resultResponse =responseMap.get("response").toString();
		String responseJson  = AESUtil.AESdecrypt(resultResponse, secretStr, iv, algorithmstr);
		System.out.println("======"+responseJson);

	}

	public static String getCurTime(){
		Date d = new Date();
		SimpleDateFormat  f = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

		System.out.println("当前时间：" +f.format(d));
		return f.format(d);
	}
}
