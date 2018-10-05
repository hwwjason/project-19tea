package com.sckj.model.dto;

import com.sckj.enums.ContentTypeEnum;
import com.sckj.model.ContentBanner;

import java.util.List;

/**
* 描述：Banner配置DTO
* @author hww
* @date 2018/09/25
*/
public class ContentBannerDTO extends ContentBanner{
    private List<String> ids;

    private List<ContentBannerDTO> contentBannerDTOS;

    private String type = ContentTypeEnum.CONTENT_BANNER.toString();;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ContentBannerDTO> getContentBannerDTOS() {
        return contentBannerDTOS;
    }

    public void setContentBannerDTOS(List<ContentBannerDTO> contentBannerDTOS) {
        this.contentBannerDTOS = contentBannerDTOS;
    }
}
