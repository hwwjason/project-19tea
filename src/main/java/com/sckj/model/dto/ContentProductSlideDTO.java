package com.sckj.model.dto;

import com.sckj.model.ContentProductSlide;

import java.util.List;

/**
* 描述：商品滑动配置DTO
* @author hww
* @date 2018/09/25
*/
public class ContentProductSlideDTO extends ContentProductSlide{
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
