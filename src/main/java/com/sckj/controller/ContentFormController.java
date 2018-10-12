package com.sckj.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sckj.common.Query;
import com.sckj.common.ResultData;
import com.sckj.constant.MessageConstants;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.exception.BusinessException;
import com.sckj.model.ContentForm;
import com.sckj.model.dto.ContentFormDTO;
import com.sckj.service.IContentFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* 描述：组件内容配置控制层
* @author hww
* @date 2018/10/03
*/
@RestController
@RequestMapping("/contentForm")
public class ContentFormController {

    private static final Logger logger = LoggerFactory.getLogger(ContentFormController.class);

    @Autowired
    private IContentFormService contentFormService;

    /**
    * 描述：根据Id 查询
    * @param id  组件内容配置id
    */
    @RequestMapping(value = "/findById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findById(@RequestParam String id)throws Exception {
        try{
            ResultData resultData = new ResultData();
            ContentFormDTO contentFormDTO = contentFormService.findDTOById(id);
            resultData.setData(contentFormDTO);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 查询失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述:创建或更新组件内容配置
    * @param contentFormDTO  组件内容配置DTO
    */
    @RequestMapping(value = "/createOrUpdate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData createOrUpdate(@RequestBody ContentFormDTO contentFormDTO) throws Exception {
        try {
            ResultData resultData = new ResultData();
            resultData.setData(contentFormService.createOrUpdateContentForm(contentFormDTO));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 创建或更新失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
     * 描述：根据 parentid 查询
     * @param parentid
     */
    @RequestMapping(value = "/findByParentid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findByParentid(@RequestParam String parentid,@RequestParam(required = false) String formType)throws Exception {
        try{
            ResultData resultData = new ResultData();
            List<ContentForm> contentForms = new ArrayList<>();
            if (formType!=null){
                contentForms = contentFormService.findByParentidAndFormType(parentid,formType);
            }else{
                contentForms = contentFormService.findByParentid(parentid);
            }
            resultData.setData(contentForms);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 查询失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述：删除组件内容配置
    * @param ids 组件内容配置ids
    */
    @RequestMapping(value = "/deleteByIds", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData deleteByIds(@RequestParam List<String> ids) throws Exception {
        try {
            ResultData resultData = new ResultData();
            contentFormService.deleteByIds(ids);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 删除失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述：分页查询组件内容配置
    * @param
    */
    @RequestMapping(value = "/getContentFormList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData getContentFormList(@RequestBody Query query){
        ResultData resultData = new ResultData();
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        Map<String,Object> map = (Map<String, Object>) query.getCondition();
        List<ContentFormDTO> list = contentFormService.getContentFormList(map);
        PageInfo<ContentFormDTO> pageInfo = new PageInfo<ContentFormDTO>(list);
        resultData.setData(pageInfo);
        return resultData;
    }

}