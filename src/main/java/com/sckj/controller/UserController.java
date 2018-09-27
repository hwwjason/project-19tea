package com.sckj.controller;

import com.sckj.common.ResultData;
import com.sckj.constant.MessageConstants;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.exception.BusinessException;
import com.sckj.model.dto.PersonalInformationDTO;
import com.sckj.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
 
@RestController
@RequestMapping(value = {"/user"})
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(ContentLineController.class);
 
    @Autowired
    private IUserService userService;
 
    @RequestMapping(value = {"/findAll"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    public List getAllUsers(){
       List list =  userService.findAllUser();
       return list;
    }

    /**
     * 描述：根据Id 查询
     * @param userId  用户地址id
     */
    @RequestMapping(value = "/getPersonalInformation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData getPersonalInformation(@RequestParam String userId,@RequestParam String cartType)throws Exception {
        try{
            ResultData resultData = new ResultData();
            PersonalInformationDTO personalInformation = userService.getPersonalInformation(userId,cartType);
            resultData.setData(personalInformation);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 查询失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }
}
