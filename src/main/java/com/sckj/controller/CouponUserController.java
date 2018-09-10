package com.sckj.controller;

import com.sckj.constant.MessageConstants;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.exception.BusinessException;
import com.sckj.service.ICouponUserService;
import com.sckj.model.CouponUser;
import com.sckj.model.dto.CouponUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import com.sckj.common.ResultData;

/**
* 描述：优惠券用户表控制层
* @author hww
* @date 2018/09/10
*/
@RestController
@RequestMapping("/bak/couponUser")
public class CouponUserController {

    private static final Logger logger = LoggerFactory.getLogger(CouponUserController.class);

    @Autowired
    private ICouponUserService couponUserService;

    /**
    * 描述：根据Id 查询
    * @param id  优惠券用户表id
    */
    @RequestMapping(value = "/findById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findById(@RequestParam String id)throws Exception {
        try{
            ResultData resultData = new ResultData();
            CouponUserDTO couponUserDTO = couponUserService.findDTOById(id);
            resultData.setData(couponUserDTO);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 查询失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述:创建优惠券用户表
    * @param couponUserDTO  优惠券用户表DTO
    */
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData create(@RequestBody CouponUserDTO couponUserDTO) throws Exception {
        try {
            ResultData resultData = new ResultData();
            resultData.setData(couponUserService.createCouponUser(couponUserDTO));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 创建失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述：删除优惠券用户表
    * @param id 优惠券用户表id
    */
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData deleteById(@RequestParam String id) throws Exception {
        try {
            ResultData resultData = new ResultData();
            couponUserService.deleteById(id);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 删除失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述：更新优惠券用户表
    *
    */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData updateCouponUser(@RequestBody CouponUserDTO couponUserDTO) throws Exception {
        try {
            ResultData resultData = new ResultData();
            resultData.setData(couponUserService.updateCouponUser(couponUserDTO));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 更新失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

}