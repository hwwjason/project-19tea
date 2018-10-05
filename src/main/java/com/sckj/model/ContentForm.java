package com.sckj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.persistence.*;
import java.util.Date;
import java.math.BigDecimal;

/**
* 描述：组件内容配置模型
* @author hww
* @date 2018/10/03
*/
@Entity
@Table(name="sckj_content_form")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class ContentForm {

    /**
    *
    */
    @Id
    private String id;

    @Column(name = "name",columnDefinition = "VARCHAR")
    private String name;


    /**
    *contentid,所属content的id
    */
    @Column(name = "contentid",columnDefinition = "VARCHAR")
    private String contentid;




    /**
    *父节点id
    */
    @Column(name = "parentid",columnDefinition = "VARCHAR")
    private String parentid;




    /**
    *宽度（分割线专有属性）
    */
    @Column(name = "width",columnDefinition = "VARCHAR")
    private String width;




    /**
    *调整类型(0 频道，1商品，2链接)
    */
    @Column(name = "jump_type",columnDefinition = "VARCHAR")
    private String jumpType;




    /**
    *调整链接
    */
    @Column(name = "jump_url",columnDefinition = "VARCHAR")
    private String jumpUrl;




    /**
    *
    */
    @Column(name = "img_url",columnDefinition = "VARCHAR")
    private String imgUrl;




    /**
    *商品编码
    */
    @Column(name = "product_code",columnDefinition = "VARCHAR")
    private String productCode;




    /**
    *
    */



    @Column(name = "product_price",columnDefinition = "DECIMAL")
    private BigDecimal productPrice;
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
    *层级(第一层1，第二层2)
    */
    @Column(name = "level",columnDefinition = "VARCHAR")
    private String level;




    /**
    *
    */
    @Column(name = "tag_first",columnDefinition = "VARCHAR")
    private String tagFirst;




    /**
    *
    */
    @Column(name = "tag_second",columnDefinition = "VARCHAR")
    private String tagSecond;




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

    /**
     *("0","Banner配置"),("1","商品滑动栏"),("2","分割线"),("3","广告长图"),("4","商品通栏");
     */
    @Column(name = "form_type",columnDefinition = "VARCHAR")
    private String formType;



    public String getId() {
        return this.id;
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
        return this.contentid;
    }

    public void setContentid(String contentid) {
        this.contentid = contentid;
    }


    public String getParentid() {
        return this.parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }


    public String getWidth() {
        return this.width;
    }

    public void setWidth(String width) {
        this.width = width;
    }


    public String getJumpType() {
        return this.jumpType;
    }

    public void setJumpType(String jumpType) {
        this.jumpType = jumpType;
    }


    public String getJumpUrl() {
        return this.jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }


    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    public String getProductCode() {
        return this.productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }



    public BigDecimal getProductPrice() {
    return this.productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
    this.productPrice = productPrice;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getOrders() {
        return this.orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }


    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


    public String getTagFirst() {
        return this.tagFirst;
    }

    public void setTagFirst(String tagFirst) {
        this.tagFirst = tagFirst;
    }


    public String getTagSecond() {
        return this.tagSecond;
    }

    public void setTagSecond(String tagSecond) {
        this.tagSecond = tagSecond;
    }


    public String getTimeType() {
        return this.timeType;
    }

    public void setTimeType(String timeType) {
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


    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }
}
