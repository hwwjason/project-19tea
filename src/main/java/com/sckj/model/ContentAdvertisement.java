package com.sckj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.persistence.*;
import java.util.Date;
import java.math.BigDecimal;

/**
* 描述：广告长图配置模型
* @author hww
* @date 2018/09/25
*/
@Entity
@Table(name="sckj_content_advertisement")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class ContentAdvertisement {

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


    @Column(name = "contentid",columnDefinition = "VARCHAR")
    private String contentid;

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
    *0 频道，1商品，2链接
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


    @Column(name = "orders",columnDefinition = "VARCHAR")
    private String orders;

    /**
     * 层级（1第一层级，2第二层级）
     */
    @Column(name = "level",columnDefinition = "VARCHAR")
    private String level;

    /**
    *0永久，1时间配置
    */
    @Column(name = "time_type",columnDefinition = "VARCHAR")
    private String timeType;




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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentid() {
        return contentid;
    }

    public void setContentid(String contentid) {
        this.contentid = contentid;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getJumpType() {
        return jumpType;
    }

    public void setJumpType(String jumpType) {
        this.jumpType = jumpType;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

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
