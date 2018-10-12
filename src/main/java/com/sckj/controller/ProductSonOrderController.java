package com.sckj.controller;

import com.sckj.common.ResultData;
import com.sckj.constant.MessageConstants;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.exception.BusinessException;
import com.sckj.model.dto.ProductSonOrderDTO;
import com.sckj.service.IProductSonOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
* 描述：订单子表控制层
* @author hww
* @date 2018/09/05
*/
@RestController
@RequestMapping(value = {"/bak/productSonOrder"})
public class ProductSonOrderController {

    @Autowired
    private IProductSonOrderService productSonOrderService;

    /**
    * 描述：根据Id 查询
    * @param id  订单子表id
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findById(@PathVariable("id") String id)throws Exception {
        try{
            ResultData resultData = new ResultData();
            ProductSonOrderDTO productSonOrderDTO = productSonOrderService.findDTOById(id);
            resultData.setData(productSonOrderDTO);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述:创建订单子表
    * @param productSonOrderDTO  订单子表DTO
    */
    @RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData create(@RequestBody ProductSonOrderDTO productSonOrderDTO) throws Exception {
        try {
            ResultData resultData = new ResultData();
            resultData.setData(productSonOrderService.createProductSonOrder(productSonOrderDTO));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述：删除订单子表
    * @param id 订单子表id
    */
    @RequestMapping(value = "/deleteById", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteById(@PathVariable("id") String id) throws Exception {
        productSonOrderService.deleteById(id);
    }

    /**
    * 描述：更新订单子表
    */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData updateProductSonOrder(@RequestBody ProductSonOrderDTO productSonOrderDTO) throws Exception {
        try {
            ResultData resultData = new ResultData();
            resultData.setData(productSonOrderService.updateProductSonOrder(productSonOrderDTO));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

}