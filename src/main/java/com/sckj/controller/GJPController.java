package com.sckj.controller;

import com.sckj.common.ResultData;
import com.sckj.constant.MessageConstants;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.exception.BusinessException;
import com.sckj.model.ProductOrder;
import com.sckj.model.ProductSonOrder;
import com.sckj.service.IGJPService;
import com.sckj.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = {"/gjp"})
public class GJPController {
 
    @Autowired
    private IGJPService gjpService;
 
    @RequestMapping(value = {"/getProductinfo"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.POST)
    public ResultData getProductinfo(){

        try{
            ResultData resultData = new ResultData();
            String str =   gjpService.getProductinfo();
            resultData.setData(str);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    @RequestMapping(value = {"/getProductinfoByCode"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.POST)
    public ResultData getProductinfoByCode(String code){
        try{
            ResultData resultData = new ResultData();
            String str =   gjpService.getProductinfoByCode(code);
            resultData.setData(str);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
     * 查询商品库存信息
     * @param code
     * @return
     */
    @RequestMapping(value = {"/querysaleqty"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.POST)
    public ResultData querysaleqty(String code){

        try{
            ResultData resultData = new ResultData();
            String str =   gjpService.querysaleqty(code);
            resultData.setData(str);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
     * 批量查询商品库存信息
     * @param codes
     * @return
     */
    @RequestMapping(value = {"/batchquerysaleqty"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.POST)
    public ResultData batchquerysaleqty(@RequestParam List<String> codes){
        try{
            ResultData resultData = new ResultData();
            String str =   gjpService.batchquerysaleqty(codes);
            resultData.setData(str);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    @RequestMapping(value = {"/uploadproducts"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.POST)
    public ResultData uploadproducts(String code){

        try{
            ResultData resultData = new ResultData();
            String str =   gjpService.uploadproducts(code);
            resultData.setData(str);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    @RequestMapping(value = {"/queryktypelist"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.POST)
    public ResultData queryktypelist(){
        try{
            ResultData resultData = new ResultData();
            List<Object> str =   gjpService.queryktypelist();
            resultData.setData(str);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
     * 查询销售订单
     * @return
     */
    @RequestMapping(value = {"/querysaleorder"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.POST)
    public ResultData querysaleorder(){
        try{
            ResultData resultData = new ResultData();
            List<Object> str =   gjpService.querysaleorder();
            resultData.setData(str);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
     * 上载交易
     * @return
     */
    @RequestMapping(value = {"/addsaleorder"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.POST)
    public ResultData addsaleorder(){
        try{
            ResultData resultData = new ResultData();
            List<Object> str =   gjpService.addsaleorder();
            resultData.setData(str);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
     * 上载交易
     * @return
     */
    @RequestMapping(value = {"/uploadsaleorders"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.POST)
    public ResultData uploadsaleorders(){
        try{
            ResultData resultData = new ResultData();
            ProductOrder productOrder = new ProductOrder();
            productOrder.setId(UUIDUtils.generate());
            productOrder.setProductPrice(new BigDecimal(99));
            productOrder.setTotalPrice(new BigDecimal(990));
            productOrder.setCouponPrice(new BigDecimal(10));
            productOrder.setAcceptName("黄伟伟");
            productOrder.setAcceptTel("13609503607");
            productOrder.setProvince("福建");
            productOrder.setCity("福州");
            productOrder.setArea("鼓楼区");
            productOrder.setAddress("软件大道软件园B区12号楼");

            List<ProductSonOrder> productSonOrders = new ArrayList<>();
            ProductSonOrder sonOrder = new ProductSonOrder();
            sonOrder.setProductcode("9999");
            sonOrder.setTitle("标题标题");
            sonOrder.setProductPrice(new BigDecimal(90));
            sonOrder.setBuynum(10);
            productSonOrders.add(sonOrder);

            String str =   gjpService.uploadsaleorders(productOrder,productSonOrders,null);
            resultData.setData(str);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }
}
