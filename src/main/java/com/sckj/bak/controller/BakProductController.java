package com.sckj.bak.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sckj.common.Query;
import com.sckj.common.ResultData;
import com.sckj.model.dto.ProductListDTO;
import com.sckj.model.ProductList;
import com.sckj.service.IProductService;
import com.sckj.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * 商品控制器
 */
@RestController
@RequestMapping("/bak/product")
public class BakProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping(value = "/testUpdateProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultData updateProduct() throws Exception{
        ResultData resultData = new ResultData();
        for (int i=0;i<99;i++){
            ProductList productList = new ProductList();
            productList.setId(UUIDUtils.generate());
            productService.putProductToStorage(productList);
        }
        return resultData;
    }

    @RequestMapping(value = "/putProductToStorage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultData putProductToStorage(HttpServletRequest request) throws Exception {
        ResultData resultData = new ResultData();
        productService.putProductToStorage(request);
        return resultData;
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultData deleteProduct(@RequestParam String[] ids){
        ResultData resultData = new ResultData();
        productService.deleteProduct(ids);
        return resultData;
    }

    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultData updateProduct(@RequestBody ProductList product) throws Exception{
        ResultData resultData = new ResultData();
        productService.updateProduct(product);
        return resultData;
    }

    @RequestMapping(value = "/updateProducts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultData updateProducts(@RequestBody List<ProductList> products) throws Exception{
        ResultData resultData = new ResultData();
        productService.updateProducts(products);
        return resultData;
    }


    @RequestMapping(value = "/getProductList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData getProductList(@RequestBody Query query){
        ResultData resultData = new ResultData();
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        Map<String,Object> map = (Map<String, Object>) query.getCondition();
        List<ProductListDTO> sckjUserList = productService.getProductList(map);
        PageInfo<ProductListDTO> pageInfo = new PageInfo<ProductListDTO>(sckjUserList);
        resultData.setData(pageInfo);
        return resultData;
    }

    @RequestMapping(value = "/getProductById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData getProductById(@RequestParam("id") String id){
        ResultData resultData = new ResultData();
        ProductListDTO productList = productService.getProductDTOById(id);
        resultData.setData(productList);
        return resultData;
    }

    @RequestMapping(value = "/getProductByCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData getProductByCode(@RequestParam("code") String code){
        ResultData resultData = new ResultData();
        ProductList productList = productService.getProductByCode(code);
        resultData.setData(productList);
        return resultData;
    }

    @RequestMapping(value = "/synchStock", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultData synchStock(@RequestParam List<String> codes) throws Exception{
        ResultData resultData = new ResultData();
        List<ProductListDTO>  productListDTOS = productService.synchStock(codes);
        resultData.setData(productListDTOS);
        return resultData;
    }

}
