package com.weixin.common.api;

import com.weixin.common.error.WxErrorException;

/**
 * WxErrorException处理器.
 */
public interface WxErrorExceptionHandler {

  void handle(WxErrorException e);

}
