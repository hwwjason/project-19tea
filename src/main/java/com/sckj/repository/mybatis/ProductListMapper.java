package com.sckj.repository.mybatis;

import com.sckj.model.dto.ProductListDTO;
import com.sckj.model.ProductList;

import java.util.List;
import java.util.Map;

public interface ProductListMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sckj_product_list
     *
     * @mbg.generated
     */
    int insert(ProductList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sckj_product_list
     *
     * @mbg.generated
     */
    int insertSelective(ProductList record);

    int deleteProductByIds(String[] ids);

    List<ProductListDTO> getProductList(Map<String,Object> map);

    ProductList  getOne(String id);

    ProductListDTO  getOneDTO(String id);
}