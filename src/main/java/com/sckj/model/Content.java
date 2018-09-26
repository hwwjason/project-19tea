package com.sckj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.persistence.*;
import java.util.Date;
import java.math.BigDecimal;

/**
* 描述：内容管理模型
* @author hww
* @date 2018/09/25
*/
@Entity
@Table(name="sckj_content")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Content {

    /**
    *
    */
    @Id
    private String id;




    /**
    *类型(0,首页配置，1频道配置)
    */
    @Column(name = "type",columnDefinition = "VARCHAR")
    private String type;


    @Column(name = "orders",columnDefinition = "TEXT")
    private String orders;


    /**
    *
    */
    @Column(name = "name",columnDefinition = "VARCHAR")
    private String name;




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
    *0未启动，1使用中，
    */
    @Column(name = "status",columnDefinition = "VARCHAR")
    private String status;





    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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


    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }
}
