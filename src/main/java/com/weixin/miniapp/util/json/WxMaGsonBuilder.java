package com.weixin.miniapp.util.json;

import com.weixin.miniapp.bean.WxMaTemplateMessage;
import com.weixin.miniapp.bean.analysis.WxMaRetainInfo;
import com.weixin.miniapp.bean.analysis.WxMaUserPortrait;
import com.weixin.miniapp.bean.analysis.WxMaVisitDistribution;
import com.weixin.miniapp.bean.code.WxMaCodeCommitRequest;
import com.weixin.miniapp.bean.code.WxMaCodeVersionDistribution;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMaGsonBuilder {
  private static final GsonBuilder INSTANCE = new GsonBuilder();

  static {
    INSTANCE.disableHtmlEscaping();
    INSTANCE.registerTypeAdapter(WxMaTemplateMessage.class, new WxMaTemplateMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMaCodeCommitRequest.class, new WxMaCodeCommitRequestGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMaCodeVersionDistribution.class, new WxMaCodeVersionDistributionGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMaVisitDistribution.class, new WxMaVisitDistributionGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMaRetainInfo.class, new WxMaRetainInfoGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMaUserPortrait.class, new WxMaUserPortraitGsonAdapter());
  }

  public static Gson create() {
    return INSTANCE.create();
  }

}
