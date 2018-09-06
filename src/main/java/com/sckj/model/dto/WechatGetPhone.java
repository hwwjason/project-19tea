package com.sckj.model.dto;

/**
 * 微信小程序获取手机号码
 */
public class WechatGetPhone
{
    //返回的encryptedData，包括敏感数据在内的完整用户信息的加密数据
    public String encryptedData ;
    //返回的iv，加密算法的初始向量
    public String iv ;

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }
}