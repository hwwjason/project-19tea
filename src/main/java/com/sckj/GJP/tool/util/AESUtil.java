package com.sckj.GJP.tool.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import sun.misc.BASE64Encoder;


public class AESUtil {

	private static final String KEY_ALGORITHM = "AES";
	private Cipher cipher;
	
	private SecretKeySpec key;

	public void init(byte[] keyBytes,String algorithmstr ) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException{
		
		int  base = 16;
		
		if(keyBytes.length % base!=0){
			int groups = keyBytes.length/base+(keyBytes.length % base != 0 ? 1 : 0);
			byte[] tmp = new byte[base*groups];
			Arrays.fill(tmp, (byte)0);
			System.arraycopy(keyBytes, 0, tmp, 0, keyBytes.length);
			keyBytes=tmp;
		}
		Security.addProvider(new BouncyCastleProvider());
		key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
		cipher=Cipher.getInstance(algorithmstr,"BC");
	}
	
	
	/**
	 * AES 加密
	 * @param str
	 * @param keyByte
	 * @param iv
	 * @param algorithmstr
	 * @return
	 * @throws Exception
	 */
	
	public String AESencrypt(String str,String keyByte,String iv,String algorithmstr) throws Exception{
		
		
		byte[] keyBytes = keyByte.getBytes("US-ASCII");
		byte[] content = str.getBytes("UTF-8");
		byte[] encryptedText = null;
		byte[] ivByte = iv.getBytes("US-ASCII");
		String encryptStr="";
		
		init(keyBytes,algorithmstr);
		cipher.init(Cipher.ENCRYPT_MODE,key , new IvParameterSpec(ivByte));
		encryptedText=cipher.doFinal(content);
		encryptStr=new BASE64Encoder().encode(encryptedText).replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");
		//encryptStr =Base64Utils.encode(encryptedText).trim().replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "") ;
		return encryptStr;
	}
	
	
	/**
	 *AES解密
	 * @param str
	 * @param keyByte
	 * @param iv
	 * @param algorithmstr
	 * @return
	 * @throws Exception
	 */
	public String AESdecrypt(String str,String keyByte,String iv,String algorithmstr) throws Exception{
		
		byte[] keyBytes = keyByte.getBytes("US-ASCII");
		byte[] encryptedText = null;
		byte[] ivByte = iv.getBytes("US-ASCII");
		String decryptStr="";
		
		init(keyBytes,algorithmstr);
		
		byte[] encrypted1= Base64Utils.decode(str);
		cipher.init(Cipher.DECRYPT_MODE,key , new IvParameterSpec(ivByte));
		encryptedText=cipher.doFinal(encrypted1);
		decryptStr =new String(encryptedText).trim() .replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");
		return decryptStr;
	}
	
	/**
	 * SHA 256 加密(java自带)
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public String SHAEncrypt_Java(String str) throws Exception{
		MessageDigest  md =null;
		String strDes = null;
		String encName = "SHA-256";
		byte[] bt = str.getBytes("UTF-8");
		md = MessageDigest.getInstance(encName);
		md.update(bt);
		strDes=byte2Hex(md.digest());
		return strDes;
		
	}
	
	public static String byte2Hex(byte[] bts){
		
		StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bts.length;i++){
            temp = Integer.toHexString(bts[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
	}
	
	
	
	/**
	 * SHA 256 加密(Apache工具类)
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public String SHAEncrypt_Apache(String str) throws Exception{
		MessageDigest  md =null;
		String strDes = null;
		String encName = "SHA-256";
		byte[] bt = str.getBytes("UTF-8");
		md = MessageDigest.getInstance(encName);
		byte[] hash = md.digest(str.getBytes("UTF-8"));
		strDes=Hex.encodeHexString(hash);
		return strDes;
		
	}
}
