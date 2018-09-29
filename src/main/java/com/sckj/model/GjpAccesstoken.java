package com.sckj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.persistence.*;
import java.util.Date;
import java.math.BigDecimal;

/**
* 描述：管家婆token模型
* @author hww
* @date 2018/09/28
*/
@Entity
@Table(name="sckj_gjp_accesstoken")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class GjpAccesstoken {

    /**
    *
    */
    @Id
    private String id;


    /**
    *网店key
    */
    @Column(name = "appkey",columnDefinition = "VARCHAR")
    private String appkey;




    /**
    *权限码
    */
    @Column(name = "auth_token",columnDefinition = "VARCHAR")
    private String authToken;




    /**
    *
    */
    @Column(name = "expires_in",columnDefinition = "VARCHAR")
    private String expiresIn;




    /**
    *刷新所需token
    */
    @Column(name = "refresh_token",columnDefinition = "VARCHAR")
    private String refreshToken;




    /**
    *
    */
    @Column(name = "re_expires_in",columnDefinition = "VARCHAR")
    private String reExpiresIn;




    /**
    *
    */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "timestamp",columnDefinition = "TIMESTAMP")
    private Date timestamp;



    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getAppkey() {
        return this.appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }


    public String getAuthToken() {
        return this.authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }


    public String getExpiresIn() {
        return this.expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }


    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


    public String getReExpiresIn() {
        return this.reExpiresIn;
    }

    public void setReExpiresIn(String reExpiresIn) {
        this.reExpiresIn = reExpiresIn;
    }


    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }



}
