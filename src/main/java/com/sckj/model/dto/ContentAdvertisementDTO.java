package com.sckj.model.dto;

import com.sckj.model.ContentAdvertisement;

import java.util.List;

/**
* 描述：广告长图配置DTO
* @author hww
* @date 2018/09/25
*/
public class ContentAdvertisementDTO extends ContentAdvertisement{
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
