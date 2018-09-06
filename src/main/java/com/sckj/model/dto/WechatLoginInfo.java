package com.sckj.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 微信小程序登录信息结构
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class WechatLoginInfo
{
    //login返回的code
    public String code ;
    //getUserInfo返回的encryptedData，包括敏感数据在内的完整用户信息的加密数据
    public String encryptedData ;
    //getUserInfo返回的iv，加密算法的初始向量
    public String iv ;
    //getUserInfo返回的rawData，不包括敏感信息的原始数据字符串，用于计算签名
    public String rawData ;
    //getUserInfo返回的signature，使用 sha1( rawData + sessionkey ) 得到字符串，用于校验用户信息
    public String signature ;

    public String state; //登录状态

    public String userId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}