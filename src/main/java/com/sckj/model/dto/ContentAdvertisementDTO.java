package com.sckj.model.dto;

import com.sckj.enums.ContentTypeEnum;
import com.sckj.model.ContentAdvertisement;

import java.util.List;

/**
* 描述：广告长图配置DTO
* @author hww
* @date 2018/09/25
*/
public class ContentAdvertisementDTO extends ContentAdvertisement{
    private List<String> ids;

    private List<ContentAdvertisement> secondContentAdvertisements;

    private String type = ContentTypeEnum.CONTENT_ADVER.toString();

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

    public List<ContentAdvertisement> getSecondContentAdvertisements() {
        return secondContentAdvertisements;
    }

    public void setSecondContentAdvertisements(List<ContentAdvertisement> secondContentAdvertisements) {
        this.secondContentAdvertisements = secondContentAdvertisements;
    }
}
