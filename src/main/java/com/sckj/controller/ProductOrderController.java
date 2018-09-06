package com.sckj.controller;

import com.sckj.constant.MessageConstants;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.exception.BusinessException;
import com.sckj.service.IProductOrderService;
import com.sckj.model.ProductOrder;
import com.sckj.model.dto.ProductOrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import com.sckj.common.ResultData;

/**
* 描述：订单列表控制层
* @author hww
* @date 2018/09/06
*/
@RestController
@RequestMapping("/bak/productOrder")
public class ProductOrderController {

    private static final Logger logger = LoggerFactory.getLogger(ProductOrderController.class);

    @Autowired
    private IProductOrderService productOrderService;

    /**
    * 描述：根据Id 查询
    * @param id  订单列表id
    */
    @RequestMapping(value = "/findById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findById(@RequestParam String id)throws Exception {
        try{
            ResultData resultData = new ResultData();
            ProductOrderDTO productOrderDTO = productOrderService.findDTOById(id);
            resultData.setData(productOrderDTO);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述:创建订单列表
    * @param productOrderDTO  订单列表DTO
    */
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData create(@RequestBody ProductOrderDTO productOrderDTO) throws Exception {
        try {
            ResultData resultData = new ResultData();
            resultData.setData(productOrderService.createProductOrder(productOrderDTO));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述：删除订单列表
    * @param id 订单列表id
    */
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData deleteById(@RequestParam String id) throws Exception {
        try {
            ResultData resultData = new ResultData();
            productOrderService.deleteById(id);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述：更新订单列表
    *
    */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData updateProductOrder(@RequestBody ProductOrderDTO productOrderDTO) throws Exception {
        try {
            ResultData resultData = new ResultData();
            resultData.setData(productOrderService.updateProductOrder(productOrderDTO));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

}