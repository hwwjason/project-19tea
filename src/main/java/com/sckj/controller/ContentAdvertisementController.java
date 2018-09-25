package com.sckj.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sckj.common.Query;
import com.sckj.constant.MessageConstants;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.exception.BusinessException;
import com.sckj.model.dto.CouponDTO;
import com.sckj.service.IContentAdvertisementService;
import com.sckj.model.ContentAdvertisement;
import com.sckj.model.dto.ContentAdvertisementDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import com.sckj.common.ResultData;

import java.util.List;
import java.util.Map;

/**
* 描述：广告长图配置控制层
* @author hww
* @date 2018/09/25
*/
@RestController
@RequestMapping("/contentAdvertisement")
public class ContentAdvertisementController {

    private static final Logger logger = LoggerFactory.getLogger(ContentAdvertisementController.class);

    @Autowired
    private IContentAdvertisementService contentAdvertisementService;

    /**
    * 描述：根据Id 查询
    * @param id  广告长图配置id
    */
    @RequestMapping(value = "/findById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findById(@RequestParam String id)throws Exception {
        try{
            ResultData resultData = new ResultData();
            ContentAdvertisementDTO contentAdvertisementDTO = contentAdvertisementService.findDTOById(id);
            resultData.setData(contentAdvertisementDTO);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 查询失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述:创建或更新广告长图配置
    * @param contentAdvertisementDTO  广告长图配置DTO
    */
    @RequestMapping(value = "/createOrUpdate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData createOrUpdate(@RequestBody ContentAdvertisementDTO contentAdvertisementDTO) throws Exception {
        try {
            ResultData resultData = new ResultData();
            resultData.setData(contentAdvertisementService.createOrUpdateContentAdvertisement(contentAdvertisementDTO));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 创建或更新失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述：删除广告长图配置
    * @param ids 广告长图配置ids
    */
    @RequestMapping(value = "/deleteByIds", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData deleteByIds(@RequestParam List<String> ids) throws Exception {
        try {
            ResultData resultData = new ResultData();
            contentAdvertisementService.deleteByIds(ids);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 删除失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述：分页查询广告长图配置
    * @param
    */
    @RequestMapping(value = "/getContentAdvertisementList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData getContentAdvertisementList(@RequestBody Query query){
        ResultData resultData = new ResultData();
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        Map<String,Object> map = (Map<String, Object>) query.getCondition();
        List<ContentAdvertisementDTO> list = contentAdvertisementService.getContentAdvertisementList(map);
        PageInfo<ContentAdvertisementDTO> pageInfo = new PageInfo<ContentAdvertisementDTO>(list);
        resultData.setData(pageInfo);
        return resultData;
    }

}