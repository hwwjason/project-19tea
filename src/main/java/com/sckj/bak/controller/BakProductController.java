package com.sckj.bak.controller;

import com.sckj.common.ResultData;
import com.sckj.pojo.ProductList;
import com.sckj.pojo.ProductListWithBLOBs;
import com.sckj.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bak/product")
public class BakProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping(value = "/putProductToStorage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultData putProductToStorage(@RequestBody ProductListWithBLOBs productList){
        ResultData resultData = new ResultData();
        productService.putProductToStorage(productList);
        return resultData;
    }
}
