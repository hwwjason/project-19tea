package com.weixin.miniapp.api.impl;

import com.weixin.miniapp.api.WxMaMsgService;
import com.weixin.miniapp.api.WxMaService;
import com.weixin.miniapp.bean.WxMaKefuMessage;
import com.weixin.miniapp.bean.WxMaTemplateMessage;
import com.weixin.miniapp.constant.WxMaConstants;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.weixin.common.error.WxError;
import com.weixin.common.error.WxErrorException;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMaMsgServiceImpl implements WxMaMsgService {
  private static final JsonParser JSON_PARSER = new JsonParser();
  private WxMaService wxMaService;

  public WxMaMsgServiceImpl(WxMaService wxMaService) {
    this.wxMaService = wxMaService;
  }

  @Override
  public boolean sendKefuMsg(WxMaKefuMessage message) throws WxErrorException {
    String responseContent = this.wxMaService.post(KEFU_MESSAGE_SEND_URL, message.toJson());
    return responseContent != null;
  }

  @Override
  public void sendTemplateMsg(WxMaTemplateMessage templateMessage) throws WxErrorException {
    String responseContent = this.wxMaService.post(TEMPLATE_MSG_SEND_URL, templateMessage.toJson());
    JsonObject jsonObject = JSON_PARSER.parse(responseContent).getAsJsonObject();
    if (jsonObject.get(WxMaConstants.ERRCODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent));
    }
  }

}
