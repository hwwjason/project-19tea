package com.weixin.miniapp.message;

import com.weixin.miniapp.bean.WxMaMessage;

/**
 * 消息匹配器，用在消息路由的时候
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMaMessageMatcher {

  /**
   * 消息是否匹配某种模式
   */
  boolean match(WxMaMessage message);

}
