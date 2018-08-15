package com.weixin.miniapp.api.impl;

import org.apache.commons.codec.digest.DigestUtils;

import com.weixin.miniapp.api.WxMaService;
import com.weixin.miniapp.api.WxMaUserService;
import com.weixin.miniapp.bean.WxMaJscode2SessionResult;
import com.weixin.miniapp.bean.WxMaPhoneNumberInfo;
import com.weixin.miniapp.bean.WxMaUserInfo;
import com.weixin.miniapp.util.crypt.WxMaCryptUtils;
import com.weixin.common.error.WxErrorException;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMaUserServiceImpl implements WxMaUserService {
  private WxMaService service;

  public WxMaUserServiceImpl(WxMaService service) {
    this.service = service;
  }

  @Override
  public WxMaJscode2SessionResult getSessionInfo(String jsCode) throws WxErrorException {
    return service.jsCode2SessionInfo(jsCode);
  }

  @Override
  public WxMaUserInfo getUserInfo(String sessionKey, String encryptedData, String ivStr) {
    return WxMaUserInfo.fromJson(WxMaCryptUtils.decrypt(sessionKey, encryptedData, ivStr));
  }

  @Override
  public WxMaPhoneNumberInfo getPhoneNoInfo(String sessionKey, String encryptedData, String ivStr) {
    return WxMaPhoneNumberInfo.fromJson(WxMaCryptUtils.decrypt(sessionKey, encryptedData, ivStr));
  }

  @Override
  public boolean checkUserInfo(String sessionKey, String rawData, String signature) {
    final String generatedSignature = DigestUtils.sha1Hex(rawData + sessionKey);
    return generatedSignature.equals(signature);
  }

}
