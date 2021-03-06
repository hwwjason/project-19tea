package com.sckj.model.dto;

import com.sckj.enums.FormTypeEnum;
import com.sckj.model.ContentProductSlide;

import java.util.List;

/**
* 描述：商品滑动配置DTO
* @author hww
* @date 2018/09/25
*/
public class ContentProductSlideDTO extends ContentProductSlide{
    private List<String> ids;

    private String type =  FormTypeEnum.CONTENT_PRODUCT_SLIDE.toString();

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
}
