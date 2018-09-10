package com.sckj.controller;

import com.sckj.constant.MessageConstants;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.exception.BusinessException;
import com.sckj.service.IUserAddressService;
import com.sckj.model.UserAddress;
import com.sckj.model.dto.UserAddressDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import com.sckj.common.ResultData;

/**
* 描述：用户地址控制层
* @author hww
* @date 2018/09/10
*/
@RestController
@RequestMapping("/bak/userAddress")
public class UserAddressController {

    private static final Logger logger = LoggerFactory.getLogger(UserAddressController.class);

    @Autowired
    private IUserAddressService userAddressService;

    /**
    * 描述：根据Id 查询
    * @param id  用户地址id
    */
    @RequestMapping(value = "/findById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findById(@RequestParam String id)throws Exception {
        try{
            ResultData resultData = new ResultData();
            UserAddressDTO userAddressDTO = userAddressService.findDTOById(id);
            resultData.setData(userAddressDTO);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 查询失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述:创建用户地址
    * @param userAddressDTO  用户地址DTO
    */
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData create(@RequestBody UserAddressDTO userAddressDTO) throws Exception {
        try {
            ResultData resultData = new ResultData();
            resultData.setData(userAddressService.createUserAddress(userAddressDTO));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 创建失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述：删除用户地址
    * @param id 用户地址id
    */
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData deleteById(@RequestParam String id) throws Exception {
        try {
            ResultData resultData = new ResultData();
            userAddressService.deleteById(id);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 删除失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述：更新用户地址
    *
    */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData updateUserAddress(@RequestBody UserAddressDTO userAddressDTO) throws Exception {
        try {
            ResultData resultData = new ResultData();
            resultData.setData(userAddressService.updateUserAddress(userAddressDTO));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 更新失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

}