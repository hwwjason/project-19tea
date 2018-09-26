package com.sckj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.persistence.*;
import java.util.Date;
import java.math.BigDecimal;

/**
* 描述：分割线配置模型
* @author hww
* @date 2018/09/25
*/
@Entity
@Table(name="sckj_content_line")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class ContentLine {

    /**
    *
    */
    @Id
    private String id;


    /**
    *
    */


    @Column(name = "content_id",columnDefinition = "VARCHAR")
    private String contentId;

    /**
    *
    */
    @Column(name = "name",columnDefinition = "VARCHAR")
    private String name;




    /**
    *
    */
    @Column(name = "width",columnDefinition = "VARCHAR")
    private String width;





    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getWidth() {
        return this.width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time",columnDefinition = "TIMESTAMP")
    private Date createTime;


    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_time",columnDefinition = "TIMESTAMP")
    private Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
