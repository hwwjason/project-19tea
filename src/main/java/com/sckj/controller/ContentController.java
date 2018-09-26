package com.sckj.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sckj.common.Query;
import com.sckj.constant.MessageConstants;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.exception.BusinessException;
import com.sckj.model.dto.CouponDTO;
import com.sckj.service.IContentService;
import com.sckj.model.Content;
import com.sckj.model.dto.ContentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import com.sckj.common.ResultData;

import java.util.List;
import java.util.Map;

/**
* 描述：内容管理控制层
* @author hww
* @date 2018/09/25
*/
@RestController
@RequestMapping("/content")
public class ContentController {

    private static final Logger logger = LoggerFactory.getLogger(ContentController.class);

    @Autowired
    private IContentService contentService;

    /**
    * 描述：根据Id 查询
    * @param id  内容管理id
    */
    @RequestMapping(value = "/findById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findById(@RequestParam String id)throws Exception {
        try{
            ResultData resultData = new ResultData();
            ContentDTO contentDTO = contentService.findDTOById(id);
            resultData.setData(contentDTO);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 查询失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
     * 描述：根据parentd 查询
     * @param contentid
     */
    @RequestMapping(value = "/findByContentid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findByContentid(@RequestParam String contentid)throws Exception {
        try{
            ResultData resultData = new ResultData();
            List<Object> contents = contentService.findByContentid(contentid);
            resultData.setData(contents);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 查询失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述:创建或更新内容管理
    * @param contentDTO  内容管理DTO
    */
    @RequestMapping(value = "/createOrUpdate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData createOrUpdate(@RequestBody ContentDTO contentDTO) throws Exception {
        try {
            ResultData resultData = new ResultData();
            resultData.setData(contentService.createOrUpdateContent(contentDTO));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 创建失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }


    /**
    * 描述：删除内容管理
    * @param ids 内容管理ids
    */
    @RequestMapping(value = "/deleteByIds", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData deleteByIds(@RequestParam List<String> ids) throws Exception {
        try {
            ResultData resultData = new ResultData();
            contentService.deleteByIds(ids);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 删除失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述：分页查询内容管理
    * @param
    */
    @RequestMapping(value = "/getContentList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData getContentList(@RequestBody Query query){
        ResultData resultData = new ResultData();
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        Map<String,Object> map = (Map<String, Object>) query.getCondition();
        List<ContentDTO> list = contentService.getContentList(map);
        PageInfo<ContentDTO> pageInfo = new PageInfo<ContentDTO>(list);
        resultData.setData(pageInfo);
        return resultData;
    }

}