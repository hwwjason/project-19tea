package com.sckj.controller;

import com.sckj.common.ResultData;
import com.sckj.dao.UserCartMapper;
import com.sckj.dto.UserCartDTO;
import com.sckj.jpa.UserCartJpa;
import com.sckj.pojo.UserCart;
import com.sckj.service.IUserCartService;
import com.sckj.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = {"/wechat/shopping"})
public class ShoppingController {

    @Autowired
    private IUserCartService userCartService;

    @RequestMapping(value = "/changeGoods", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData changeGoods(@RequestParam(value = "productId") String productId, @RequestParam(value = "userId")String userId , @RequestParam(value = "cartType") String cartType,@RequestParam(value = "count") int count){
        ResultData resultData = new ResultData();
        resultData.setData(userCartService.changeGoods(productId,userId,cartType,count));
        return resultData;
    }

    @RequestMapping(value = "/removeGoods", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData removeGoods(@RequestBody UserCartDTO userCartDTO){
        ResultData resultData = new ResultData();
        resultData.setData(userCartService.removeGoods(userCartDTO.getIds(),userCartDTO.getUserid(),userCartDTO.getCartType()));
        return resultData;
    }

    @RequestMapping(value = "/getUserCart", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData getUserCart(@RequestParam(value = "userId") String userId,
                                  @RequestParam(value = "cartType") String cartType){
        ResultData resultData = new ResultData();
        resultData.setData(userCartService.getUserCart(userId,cartType));
        return resultData;
    }

    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData updateProduct(@RequestBody List<UserCartDTO> userCartDTO){
        ResultData resultData = new ResultData();
        resultData.setData(userCartService.updateProduct(userCartDTO));
        return resultData;
    }

}
