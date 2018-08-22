package com.sckj.controller;

import com.sckj.common.ResultData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/shoppingCartController"})
public class ShoppingController {
    @RequestMapping(value = "/addGoods", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData addGoods(@RequestParam(value = "goodId") String goodId, @RequestParam(value = "userId")String userId , @RequestParam(value = "cartType") int cartType){
        ResultData resultData = new ResultData();
        return resultData;
    }

    @RequestMapping(value = "/removeGoods", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData removeGoods(@RequestParam(value = "goodId") String goodId, @RequestParam(value = "userId") int userId){
        ResultData resultData = new ResultData();
        return resultData;
    }

}
