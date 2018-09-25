package com.sckj.model.dto;

import com.sckj.model.ContentProductColumn;

import java.util.List;

/**
* 描述：商品通栏配置DTO
* @author hww
* @date 2018/09/25
*/
public class ContentProductColumnDTO extends ContentProductColumn{
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
