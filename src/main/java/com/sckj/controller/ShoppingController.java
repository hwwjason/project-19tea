package com.sckj.controller;

import com.sckj.common.ResultData;
import com.sckj.constant.MessageConstants;
import com.sckj.model.dto.UserCartDTO;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.exception.BusinessException;
import com.sckj.service.IUserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/wechat/shopping"})
public class ShoppingController {

    @Autowired
    private IUserCartService userCartService;

    @RequestMapping(value = "/changeGoods", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData changeGoods(@RequestParam(value = "productId") String productId, @RequestParam(value = "userId")String userId , @RequestParam(value = "cartType") String cartType,@RequestParam(value = "count") int count){
        try{
            ResultData resultData = new ResultData();
            resultData.setData(userCartService.changeGoods(productId,userId,cartType,count));
            return resultData;
        }catch (BusinessException e){
           throw e;
        } catch (Exception e){
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    @RequestMapping(value = "/removeGoods", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData removeGoods(@RequestBody UserCartDTO userCartDTO){
        try {
            ResultData resultData = new ResultData();
            resultData.setData(userCartService.removeGoods(userCartDTO.getIds(), userCartDTO.getUserid(), userCartDTO.getCartType()));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    @RequestMapping(value = "/getUserCart", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData getUserCart(@RequestParam(value = "userId") String userId,
                                  @RequestParam(value = "cartType") String cartType){
        try {
            ResultData resultData = new ResultData();
            resultData.setData(userCartService.getUserCart(userId,cartType));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData updateProduct(@RequestBody List<UserCartDTO> userCartDTO){
        try {
            ResultData resultData = new ResultData();
            resultData.setData(userCartService.updateProduct(userCartDTO));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }
}
