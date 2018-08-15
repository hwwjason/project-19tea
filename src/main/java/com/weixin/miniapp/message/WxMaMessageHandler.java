package com.weixin.miniapp.message;

import com.weixin.miniapp.api.WxMaService;
import com.weixin.miniapp.bean.WxMaMessage;
import com.weixin.common.error.WxErrorException;
import com.weixin.common.session.WxSessionManager;

import java.util.Map;

/**
 * 处理小程序推送消息的处理器接口
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMaMessageHandler {

  void handle(WxMaMessage message, Map<String, Object> context,
              WxMaService service, WxSessionManager sessionManager) throws WxErrorException;

}
