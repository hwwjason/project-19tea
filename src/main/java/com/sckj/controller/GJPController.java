package com.sckj.controller;

import com.sckj.common.ResultData;
import com.sckj.constant.MessageConstants;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.exception.BusinessException;
import com.sckj.model.dto.ProductSonOrderDTO;
import com.sckj.service.IGJPService;
import com.sckj.service.IUserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
