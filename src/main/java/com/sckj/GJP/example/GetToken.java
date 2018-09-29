package com.sckj.GJP.example;

import com.sckj.constant.GJPConstants;
import com.sckj.model.GjpAccesstoken;
import com.sckj.repository.GjpAccesstokenRepository;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.JsonUtils;
import com.sckj.utils.UUIDUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class GetToken {
	@Autowired
	private GjpAccesstokenRepository gjpAccesstokenRepository;

	public String DoGetToken(String param) throws Exception
	{
		String code = GetAuthCode(param);
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
		Map<String,Object> tokenMap = JsonUtils.parseJSON2Map(token);
		GjpAccesstoken gjpAccesstoken = new GjpAccesstoken();
		gjpAccesstoken.setId(UUIDUtils.generate());
		gjpAccesstoken.setAppkey((String) tokenMap.get("auth_token"));
		gjpAccesstoken.setAuthToken((String) tokenMap.get("auth_token"));
		gjpAccesstoken.setExpiresIn(tokenMap.get("expires_in").toString());
		gjpAccesstoken.setRefreshToken((String) tokenMap.get("refresh_token"));
		gjpAccesstoken.setReExpiresIn(tokenMap.get("re_expires_in").toString());
		gjpAccesstoken.setTimestamp(DateTimeUtils.getCurrentDate());
		//gjpAccesstokenRepository.save(gjpAccesstoken);
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


