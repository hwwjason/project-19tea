package com.sckj.bak.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sckj.common.Query;
import com.sckj.common.ResultData;
import com.sckj.dto.ProductListDTO;
import com.sckj.dto.SckjUserListDTO;
import com.sckj.pojo.ProductList;
import com.sckj.pojo.ProductListWithBLOBs;
import com.sckj.service.IProductService;
import com.sckj.utils.JsonUtils;
import com.sckj.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bak/product")
public class BakProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping(value = "/testUpdateProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultData updateProduct() throws Exception{
        ResultData resultData = new ResultData();
        for (int i=0;i<99;i++){
            ProductListWithBLOBs productList = new ProductListWithBLOBs();
            productList.setId(UUIDUtils.generate());
//            productList.setTitle("标题"+i);
//            productList.setCode("编码"+i);
            productService.putProductToStorage(productList);
        }
        return resultData;
    }

    @RequestMapping(value = "/putProductToStorage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultData putProductToStorage(ProductListWithBLOBs productList){
        ResultData resultData = new ResultData();
        productService.putProductToStorage(productList);
        return resultData;
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultData deleteProduct(@RequestParam String[] ids){
        ResultData resultData = new ResultData();
        productService.deleteProduct(ids);
        return resultData;
    }

    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultData updateProduct(@RequestBody ProductListWithBLOBs productList) throws Exception{
        ResultData resultData = new ResultData();
        productService.updateProduct(productList);
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

}
