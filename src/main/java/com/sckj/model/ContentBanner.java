package com.sckj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.persistence.*;
import java.util.Date;
import java.math.BigDecimal;

/**
* 描述：Banner配置模型
* @author hww
* @date 2018/09/25
*/
@Entity
@Table(name="sckj_content_banner")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class ContentBanner {

    /**
    *
    */
    @Id
    private String id;

    /**
    *
    */
    @Column(name = "name",columnDefinition = "VARCHAR")
    private String name;




    /**
    *
    */


    @Column(name = "contentid",columnDefinition = "INTEGER")
    private int contentid;

    /**
    *
    */
    @Column(name = "parentid",columnDefinition = "VARCHAR")
    private String parentid;




    /**
    *
    */
    @Column(name = "imgurl",columnDefinition = "VARCHAR")
    private String imgurl;




    /**
    *0频道，1商品，2链接
    */
    @Column(name = "jump_type",columnDefinition = "VARCHAR")
    private String jumpType;




    /**
    *商品编码
    */
    @Column(name = "product_code",columnDefinition = "VARCHAR")
    private String productCode;




    /**
    *
    */
    @Column(name = "product_name",columnDefinition = "VARCHAR")
    private String productName;




    /**
    *
    */
    @Column(name = "order",columnDefinition = "VARCHAR")
    private String order;




    /**
    *0永久，1时间配置
    */


    @Column(name = "time_type",columnDefinition = "INTEGER")
    private int timeType;

    /**
    *
    */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "start_time",columnDefinition = "TIMESTAMP")
    private Date startTime;


    /**
    *
    */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "end_time",columnDefinition = "TIMESTAMP")
    private Date endTime;



    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }




    public int getContentid() {
    return this.contentid;
    }

    public void setContentid(int contentid) {
    this.contentid = contentid;
    }
    public String getParentid() {
        return this.parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }


    public String getImgurl() {
        return this.imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }


    public String getJumpType() {
        return this.jumpType;
    }

    public void setJumpType(String jumpType) {
        this.jumpType = jumpType;
    }


    public String getProductCode() {
        return this.productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }


    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order) {
        this.order = order;
    }




    public int getTimeType() {
    return this.timeType;
    }

    public void setTimeType(int timeType) {
    this.timeType = timeType;
    }
    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }


    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
