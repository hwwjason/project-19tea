package com.sckj.service;

import com.sckj.model.dto.ProductListDTO;
import com.sckj.model.ProductList;

import javax.servlet.http.HttpServletRequest;
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

   /**
    * 获取商品
    * @return
    */
   ProductList getProductById(String id);

   ProductList getProductByCode(String code);

   /**
    * 获取商品
    * @param id
    * @return
    */
   ProductListDTO getProductDTOById(String id);

   /**
    * 同步库存
    * @return
    * @throws Exception
    */
   List<ProductListDTO>  synchStock (List<String> codes) throws Exception;

}
