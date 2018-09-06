package com.sckj.service;

import com.sckj.model.dto.SckjUserListDTO;
import com.sckj.model.dto.WechatLoginInfo;
import com.weixin.miniapp.bean.WxMaPhoneNumberInfo;
import com.weixin.miniapp.bean.WxMaUserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserListService {

    public WxMaUserInfo getUserInfo(String sessionKey, String signature, String rawData, String encryptedData, String iv) ;

    public WxMaPhoneNumberInfo getUserPhone(String sessionKey, String signature, String rawData, String encryptedData, String iv) ;

    public WechatLoginInfo login(String code, String encryptedData, String iv, String rawData, String signature);

    public WechatLoginInfo speedLogin(String userId,String encryptedData, String iv);

    public Page<SckjUserListDTO> findUserListPage(SckjUserListDTO sckjUserListDTO, Pageable pageable);

}
