package com.sckj.GJP.example;

import com.sckj.constant.GJPConstants;
import net.sf.json.JSONObject;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GetToken {
	public String DoGetToken(String param) throws Exception
	{
//		String code = GetAuthCode(param);
		String code = GJPConstants.AUTH_CODE;


		//获取p参数
		JSONObject obj = new JSONObject();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		Date now = new Date();
		String time = dateFormat.format(now);
		obj.put("TimeStamp", time);
		obj.put("GrantType", "auth_token");
		obj.put("AuthParam", code);
		String jsonParam = obj.toString();
		AESCoder coder = new AESCoder();
		String p = coder.encrypt(jsonParam, Config.app_secret);

		//获取sign
		JSONObject signObj = new JSONObject();
		signObj.put("appkey", Config.appkey);
		signObj.put("p", p);
		signObj.put("signkey", Config.sign_key);
		String sign = coder.SHA256(signObj.toString());

		//授权获取
		Map<String, String> map = new HashMap<String, String>();
		map.put("appkey",Config.appkey);
		map.put("p",  URLEncoder.encode(p, "utf-8"));
		map.put("sign",sign);
		String postString = "";
		for (String in : map.keySet()) {
			postString += in + "=" +map.get(in) +"&";
		}
		postString = postString.substring(0, postString.length() - 1);
		String ret =  HttpRequest.sendPost(Config.get_token_url, postString);

		//解析返回结果
		JSONObject jsonObject=JSONObject.fromObject(ret);
		String resp = jsonObject.getJSONObject("response").get("response").toString();
		String token = coder.decrypt(resp,  Config.app_secret);
		return token;
	}

	public String GetAuthCode(String param) throws Exception
	{
		String AuthCode = "";
		String[] params = param.trim().split("&");
		for(int i = 0; i < params.length; i++){
			if(params[i].toLowerCase().indexOf("auth_code") >= 0 && params[i].toLowerCase().indexOf("=") >= 0)
			{
				AuthCode = params[i].substring(params[i].toLowerCase().indexOf("="));
				break;
			}
		}
		if(AuthCode != "" && AuthCode.length() > 1)
		{
			AuthCode = AuthCode.substring(1);
		}
		else
		{
			throw new Exception("获取返回code失败");
		}
		return AuthCode;
	}

}


