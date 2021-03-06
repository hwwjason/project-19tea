package com.sckj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_info")
public class UserInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userInfo.userId
     *
     * @mbggenerated
     */
    @Id
    @Column(name = "userid",columnDefinition = "VARCHAR")
    private String userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userInfo.userName
     *
     * @mbggenerated
     */
    @Column(name = "username",columnDefinition = "VARCHAR")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userInfo.phone
     *
     * @mbggenerated
     */
    @Column(name = "phone",columnDefinition = "VARCHAR")
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userInfo.sex
     *
     * @mbggenerated
     */
    @Column(name = "sex",columnDefinition = "VARCHAR")
    private String sex;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userInfo.userId
     *
     * @return the value of userInfo.userId
     *
     * @mbggenerated
     */
    public String getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userInfo.userId
     *
     * @param userid the value for userInfo.userId
     *
     * @mbggenerated
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userInfo.userName
     *
     * @return the value of userInfo.userName
     *
     * @mbggenerated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userInfo.userName
     *
     * @param username the value for userInfo.userName
     *
     * @mbggenerated
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userInfo.phone
     *
     * @return the value of userInfo.phone
     *
     * @mbggenerated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userInfo.phone
     *
     * @param phone the value for userInfo.phone
     *
     * @mbggenerated
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userInfo.sex
     *
     * @return the value of userInfo.sex
     *
     * @mbggenerated
     */
    public String getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userInfo.sex
     *
     * @param sex the value for userInfo.sex
     *
     * @mbggenerated
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }
}