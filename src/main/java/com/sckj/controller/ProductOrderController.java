package com.sckj.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sckj.common.Query;
import com.sckj.common.ResultData;
import com.sckj.constant.MessageConstants;
import com.sckj.enums.OrderEnums.OrderStatusEnums;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.exception.BusinessException;
import com.sckj.model.dto.ProductOrderDTO;
import com.sckj.service.IProductOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        }catch (Exception e){
            logger.error("Error", e);
            throw e;
        }
    }

    /**
     * 描述：根据用户id 查询
     * @param productOrderDTO  订单列表id
     */
    @RequestMapping(value = "/findProductOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findProductOrder(@RequestBody ProductOrderDTO productOrderDTO)throws Exception {
        try{
            ResultData resultData = new ResultData();
            List<ProductOrderDTO> productOrderDTOList = productOrderService.findProductOrder(productOrderDTO);
            resultData.setData(productOrderDTOList);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error", e);
            throw e;
        }
    }

    /**
     * 分页查询
     * @param query
     * @return
     */
    @RequestMapping(value = "/getProductOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData getProductOrder(@RequestBody Query query) throws Exception{
        try{
            ResultData resultData = new ResultData();
            PageHelper.startPage(query.getPageNum(),query.getPageSize());
            Map<String,Object> map = (Map<String, Object>) query.getCondition();
            List<ProductOrderDTO> productOrder = productOrderService.getProductOrder(map);
            productOrderService.packageSonOrders(productOrder);
            PageInfo<ProductOrderDTO> pageInfo = new PageInfo<ProductOrderDTO>(productOrder);
            resultData.setData(pageInfo);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error", e);
            throw e;
        }

    }


    /**
     * 描述:去结算
     * @param productOrderDTO  订单列表DTO
     */
    @RequestMapping(value = "/toAccount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData toAccount(@RequestBody ProductOrderDTO productOrderDTO) throws Exception {
        try {
            ResultData resultData = new ResultData();
            resultData.setData(productOrderService.toAccount(productOrderDTO));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error", e);
            throw e;
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
            throw e;
        }
    }

    /**
    * 描述：更新订单列表
    *
    */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData updateProductOrder(@RequestBody ProductOrderDTO productOrderDTO) throws Exception {
        try {
            ResultData resultData = new ResultData();
            resultData.setData(productOrderService.updateProductOrder(productOrderDTO));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error", e);
            throw e;
        }
    }

    /**
     * 描述：申请退款
     *
     */
    @RequestMapping(value = "/applyRefund", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData applyRefund(@RequestParam String id,@RequestParam String userId) throws Exception {
        try {
            ResultData resultData = new ResultData();
            productOrderService.updateOrderStatus(id,userId, OrderStatusEnums.APPLY_REFUND.toString());
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error", e);
            throw e;
        }
    }

    /**
     * 发货
     * 描述：deliverProduct
     *
     */
    @RequestMapping(value = "/deliverProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData deliverProduct(@RequestParam String id) throws Exception {
        try {
            ResultData resultData = new ResultData();
            productOrderService.deliverProduct(id);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error", e);
            throw e;
        }
    }



}