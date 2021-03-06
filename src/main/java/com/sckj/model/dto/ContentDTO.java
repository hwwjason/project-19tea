package com.sckj.model.dto;

import com.sckj.model.Content;
import com.sckj.model.ContentForm;

import java.util.List;

/**
* 描述：内容管理DTO
* @author hww
* @date 2018/09/25
*/
public class ContentDTO extends Content{
    private List<ContentForm> contentForms;

    private List<ContentFormDTO> contentFormDTOS;

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

    public List<ContentFormDTO> getContentFormDTOS() {
        return contentFormDTOS;
    }

    public void setContentFormDTOS(List<ContentFormDTO> contentFormDTOS) {
        this.contentFormDTOS = contentFormDTOS;
    }
}
