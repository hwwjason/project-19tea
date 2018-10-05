package com.sckj.model.dto;

import com.sckj.model.ContentForm;

import java.util.List;

/**
* 描述：组件内容配置DTO
* @author hww
* @date 2018/10/03
*/
public class ContentFormDTO extends ContentForm{

    List<ContentForm> contentForms;

    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public List<ContentForm> getContentForms() {
        return contentForms;
    }

    public void setContentForms(List<ContentForm> contentForms) {
        this.contentForms = contentForms;
    }
}
