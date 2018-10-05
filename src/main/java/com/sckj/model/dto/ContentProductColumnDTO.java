package com.sckj.model.dto;

import com.sckj.enums.ContentTypeEnum;
import com.sckj.model.ContentProductColumn;

import java.util.List;

/**
* 描述：商品通栏配置DTO
* @author hww
* @date 2018/09/25
*/
public class ContentProductColumnDTO extends ContentProductColumn{
    private List<String> ids;

    private List<ContentProductColumn> contentProductColumns;

    private String type= ContentTypeEnum.CONTENT_PRODUCT_CLOUMN.toString();

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

    public List<ContentProductColumn> getContentProductColumns() {
        return contentProductColumns;
    }

    public void setContentProductColumns(List<ContentProductColumn> contentProductColumns) {
        this.contentProductColumns = contentProductColumns;
    }
}
