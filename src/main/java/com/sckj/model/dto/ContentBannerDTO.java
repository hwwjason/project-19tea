package com.sckj.model.dto;

import com.sckj.model.ContentBanner;

import java.util.List;

/**
* 描述：Banner配置DTO
* @author hww
* @date 2018/09/25
*/
public class ContentBannerDTO extends ContentBanner{
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
