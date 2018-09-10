package com.sckj.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.persistence.*;
import java.util.Date;
import java.math.BigDecimal;

/**
* 描述：用户地址模型
* @author hww
* @date 2018/09/10
*/
@Entity
@Table(name="sckj_user_address",uniqueConstraints = @UniqueConstraint(columnNames = {"userid" }))
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class UserAddress {

    /**
    *
    */
    @Id
    private String id;




    /**
    *
    */
    @Column(name = "userid",columnDefinition = "VARCHAR")
    private String userid;




    /**
    *
    */
    @Column(name = "name",columnDefinition = "VARCHAR")
    private String name;




    /**
    *
    */
    @Column(name = "tel",columnDefinition = "VARCHAR")
    private String tel;




    /**
    *省
    */
    @Column(name = "province",columnDefinition = "VARCHAR")
    private String province;




    /**
    *市
    */
    @Column(name = "city",columnDefinition = "VARCHAR")
    private String city;




    /**
    *区域
    */
    @Column(name = "area",columnDefinition = "VARCHAR")
    private String area;




    /**
    *地址
    */
    @Column(name = "address",columnDefinition = "VARCHAR")
    private String address;




    /**
    *提示
    */
    @Column(name = "tip",columnDefinition = "VARCHAR")
    private String tip;




    /**
    *默认
    */
    @Column(name = "isdefault",columnDefinition = "VARCHAR")
    private String isdefault;




    /**
    *
    */
    @Column(name = "status",columnDefinition = "VARCHAR")
    private String status;





    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }


    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }


    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getTip() {
        return this.tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }


    public String getIsdefault() {
        return this.isdefault;
    }

    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault;
    }


    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
