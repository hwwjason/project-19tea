package com.sckj.model.dto;

import com.sckj.model.GjpAccesstoken;

import java.util.List;

/**
* 描述：管家婆tokenDTO
* @author hww
* @date 2018/09/28
*/
public class GjpAccesstokenDTO extends GjpAccesstoken{
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
