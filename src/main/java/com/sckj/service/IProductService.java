package com.sckj.service;

import com.sckj.dto.ProductListDTO;
import com.sckj.pojo.ProductList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IProductService {

   /**
    * 商品入库
    * @param productList
    */
   void putProductToStorage(ProductList productList);

   /**
    * 商品入库
    * @param
    */
   void putProductToStorage(HttpServletRequest request) throws Exception;

   void deleteProduct(String[] ids);

   /**
    * 更新商品状态
    * @param
    */
   void updateProduct(ProductList product) throws Exception;

   /**
    * 批量更新
    * @param products
    * @throws Exception
    */
   void updateProducts(List<ProductList> products) throws Exception;

   /**
    * 获取商品列表
    * @return
    */
   List<ProductListDTO> getProductList(Map<String,Object> productListMap);


}
