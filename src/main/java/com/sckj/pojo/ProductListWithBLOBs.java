package com.sckj.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@Entity
@Table(name = "sckj_product_list")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ProductListWithBLOBs extends ProductList {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.img
     *
     * @mbg.generated
     */
    @Column(name = "img",columnDefinition = "TEXT")
    private String img;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sckj_product_list.introduce
     *
     * @mbg.generated
     */
    @Column(name = "introduce",columnDefinition = "TEXT")
    private String introduce;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.img
     *
     * @return the value of sckj_product_list.img
     *
     * @mbg.generated
     */
    public String getImg() {
        return img;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.img
     *
     * @param img the value for sckj_product_list.img
     *
     * @mbg.generated
     */
    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sckj_product_list.introduce
     *
     * @return the value of sckj_product_list.introduce
     *
     * @mbg.generated
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sckj_product_list.introduce
     *
     * @param introduce the value for sckj_product_list.introduce
     *
     * @mbg.generated
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }
}