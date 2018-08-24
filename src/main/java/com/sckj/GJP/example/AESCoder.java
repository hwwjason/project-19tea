package com.sckj.GJP.example;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AESCoder {

    final String algorithmStr = "AES/CBC/PKCS5Padding";
    final String KEY_ALGORITHM = "AES";

    //private static
    AESCoder(){

    }

    // 加密
    public  String encrypt(String sSrc, String sKey) throws Exception {
        String ivParameter = sKey.substring(5, 5+16);
        Cipher cipher = Cipher.getInstance(algorithmStr);
        byte[] raw = sKey.getBytes("US-ASCII");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes("US-ASCII"));//使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("UTF-8"));
        return new BASE64Encoder().encode(encrypted).replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");//此处使用BASE64做转码。
    }

    // 解密
    public  String decrypt(String sSrc, String sKey) throws Exception {
        try {
            String ivParameter = sKey.substring(5, 5+16);
            byte[] raw = sKey.getBytes("US-ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(algorithmStr);
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes("US-ASCII"));
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);//先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original,"UTF-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

    public  String SHA256(String str)
    {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return encodeStr;
    }


    private  String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }


    public  String SignRequest(Map<String, String> parameters, String signKey) throws NoSuchAlgorithmException
    {
        String result = "";
// 第一步：把字典按Key的字母顺序排序
        Collection<String> keyset= parameters.keySet();
        List list=new ArrayList<String>(keyset);
        Collections.sort(list);
// 第二步：把所有参数名和参数值串在一起
        String query = "";


        for(int i=0;i<list.size();i++){
            if		(list.get(i) != null && list.get(i).toString().length() > 0 && parameters.get(list.get(i)) != null && parameters.get(list.get(i)).length() > 0)
                query =query + list.get(i) + parameters.get(list.get(i));
        }

        query =query + signKey;
//创建具有指定算法名称的信息摘要
        MessageDigest md5 = MessageDigest.getInstance("MD5");
//使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
        byte[] results;
        try {
            results = md5.digest(query.getBytes("UTF-8"));
            result = byte2Hex(results);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//将得到的字节数组变成字符串返回
        return result;
    }





}
