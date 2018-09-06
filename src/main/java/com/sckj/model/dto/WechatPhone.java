package com.sckj.model.dto;

/**
 * 微信用户绑定的手机号
 */
public class WechatPhone
{
    public String phoneNumber ;
    public String purePhoneNumber ;
    public String countryCode ;
    public WechatUserInfo.Watermark watermark ;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPurePhoneNumber() {
        return purePhoneNumber;
    }

    public void setPurePhoneNumber(String purePhoneNumber) {
        this.purePhoneNumber = purePhoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public WechatUserInfo.Watermark getWatermark() {
        return watermark;
    }

    public void setWatermark(WechatUserInfo.Watermark watermark) {
        this.watermark = watermark;
    }
}