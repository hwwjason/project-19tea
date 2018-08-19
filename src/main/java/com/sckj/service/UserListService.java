package com.sckj.service;

import com.sckj.dto.WechatLoginInfo;
import com.weixin.miniapp.bean.WxMaPhoneNumberInfo;
import com.weixin.miniapp.bean.WxMaUserInfo;

public interface UserListService {

    public WxMaUserInfo getUserInfo(String sessionKey, String signature, String rawData, String encryptedData, String iv) ;

    public WxMaPhoneNumberInfo getUserPhone(String sessionKey, String signature, String rawData, String encryptedData, String iv) ;

    public WechatLoginInfo login(String code, String encryptedData, String iv, String rawData, String signature);

    public WechatLoginInfo speedLogin(String userId,String encryptedData, String iv);

}
