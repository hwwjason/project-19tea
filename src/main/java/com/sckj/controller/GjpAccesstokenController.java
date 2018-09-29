package com.sckj.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sckj.common.Query;
import com.sckj.constant.MessageConstants;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.exception.BusinessException;
import com.sckj.model.dto.CouponDTO;
import com.sckj.service.IGjpAccesstokenService;
import com.sckj.model.GjpAccesstoken;
import com.sckj.model.dto.GjpAccesstokenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import com.sckj.common.ResultData;

import java.util.List;
import java.util.Map;

/**
* 描述：管家婆token控制层
* @author hww
* @date 2018/09/28
*/
@RestController
@RequestMapping("/gjpAccesstoken")
public class GjpAccesstokenController {

    private static final Logger logger = LoggerFactory.getLogger(GjpAccesstokenController.class);

    @Autowired
    private IGjpAccesstokenService gjpAccesstokenService;

    /**
    * 描述：根据Id 查询
    * @param id  管家婆tokenid
    */
    @RequestMapping(value = "/findById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findById(@RequestParam String id)throws Exception {
        try{
            ResultData resultData = new ResultData();
            GjpAccesstokenDTO gjpAccesstokenDTO = gjpAccesstokenService.findDTOById(id);
            resultData.setData(gjpAccesstokenDTO);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 查询失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述:创建或更新管家婆token
    * @param gjpAccesstokenDTO  管家婆tokenDTO
    */
    @RequestMapping(value = "/createOrUpdate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData createOrUpdate(@RequestBody GjpAccesstokenDTO gjpAccesstokenDTO) throws Exception {
        try {
            ResultData resultData = new ResultData();
            resultData.setData(gjpAccesstokenService.createOrUpdateGjpAccesstoken(gjpAccesstokenDTO));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 创建或更新失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述：删除管家婆token
    * @param ids 管家婆tokenids
    */
    @RequestMapping(value = "/deleteByIds", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData deleteByIds(@RequestParam List<String> ids) throws Exception {
        try {
            ResultData resultData = new ResultData();
            gjpAccesstokenService.deleteByIds(ids);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 删除失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }



}