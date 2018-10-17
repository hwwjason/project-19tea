package com.sckj.service;

import com.sckj.model.dto.SckjUserListDTO;
import com.sckj.model.dto.WechatLoginInfo;
import com.weixin.miniapp.bean.WxMaPhoneNumberInfo;
import com.weixin.miniapp.bean.WxMaUserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserListService {

     WxMaUserInfo getUserInfo(String sessionKey, String signature, String rawData, String encryptedData, String iv) ;

     WxMaPhoneNumberInfo getUserPhone(String sessionKey, String signature, String rawData, String encryptedData, String iv) ;

     WechatLoginInfo login(String code, String encryptedData, String iv, String rawData, String signature);

     WechatLoginInfo speedLogin(String userId,String encryptedData, String iv);

     Page<SckjUserListDTO> findUserListPage(SckjUserListDTO sckjUserListDTO, Pageable pageable);

     boolean isLogin(String userId) throws Exception;

}
