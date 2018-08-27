package com.sckj.GJP.tool.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	private final  String HEX = "0123456789abcdef";

	/**
	 * MD5加密
	 * @param strSrc
	 * @return
	 * @throws Exception
	 */
	public String MD5Encrypt(String strSrc) throws Exception{
		String finalStr = "";
		MessageDigest  md5 = MessageDigest.getInstance("MD5");

		byte[] md = md5.digest(strSrc.getBytes());
		StringBuilder sb = new StringBuilder(md.length*2);
		for(byte b:md){

			sb.append(HEX.charAt((b>>4)&0x0f));

			sb.append(HEX.charAt(b&0x0f));


		}
		finalStr = sb.toString().toUpperCase();
		return finalStr;
	}
}
