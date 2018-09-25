package com.sckj.model.dto;

import com.sckj.model.ContentLine;

import java.util.List;

/**
* 描述：分割线配置DTO
* @author hww
* @date 2018/09/25
*/
public class ContentLineDTO extends ContentLine{
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
