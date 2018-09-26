package com.sckj.model.dto;

import com.sckj.model.Content;

import java.util.List;

/**
* 描述：内容管理DTO
* @author hww
* @date 2018/09/25
*/
public class ContentDTO extends Content{
    private List<String> ids;

    private String type;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }
}
