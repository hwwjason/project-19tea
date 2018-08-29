package com.sckj.controller;

import com.sckj.common.ResultData;
import com.sckj.dao.UserCartMapper;
import com.sckj.jpa.UserCartJpa;
import com.sckj.pojo.UserCart;
import com.sckj.service.IUserCartService;
import com.sckj.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = {"/shoppingCartController"})
public class ShoppingController {



    @Autowired
    private IUserCartService userCartService;

    @RequestMapping(value = "/addGoods", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData addGoods(@RequestParam(value = "productId") String productId, @RequestParam(value = "userId")String userId , @RequestParam(value = "cartType") String cartType){
        ResultData resultData = new ResultData();
        resultData.setData(userCartService.addGoods(productId,userId,cartType));
        return resultData;
    }

    @RequestMapping(value = "/removeGoods", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData removeGoods(@RequestParam(value = "productId") String goodId, @RequestParam(value = "userId") int userId){
        ResultData resultData = new ResultData();
        return resultData;
    }

}
