package com.sckj.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sckj.common.Query;
import com.sckj.constant.MessageConstants;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.exception.BusinessException;
import com.sckj.model.dto.CouponDTO;
import com.sckj.service.IContentBannerService;
import com.sckj.model.ContentBanner;
import com.sckj.model.dto.ContentBannerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import com.sckj.common.ResultData;

import java.util.List;
import java.util.Map;

/**
* 描述：Banner配置控制层
* @author hww
* @date 2018/09/25
*/
@RestController
@RequestMapping("/contentBanner")
public class ContentBannerController {

    private static final Logger logger = LoggerFactory.getLogger(ContentBannerController.class);

    @Autowired
    private IContentBannerService contentBannerService;

    /**
    * 描述：根据Id 查询
    * @param id  Banner配置id
    */
    @RequestMapping(value = "/findById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findById(@RequestParam String id)throws Exception {
        try{
            ResultData resultData = new ResultData();
            ContentBannerDTO contentBannerDTO = contentBannerService.findDTOById(id);
            resultData.setData(contentBannerDTO);
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
     * @param parentid
     */
    @RequestMapping(value = "/findByParentid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findByParentid(@RequestParam String parentid)throws Exception {
        try{
            ResultData resultData = new ResultData();
            List<ContentBanner> contentBanners = contentBannerService.findByParentid(parentid);
            resultData.setData(contentBanners);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 查询失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述:创建或更新Banner配置
    * @param contentBannerDTO  Banner配置DTO
    */
    @RequestMapping(value = "/createOrUpdate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData createOrUpdate(@RequestBody ContentBannerDTO contentBannerDTO) throws Exception {
        try {
            ResultData resultData = new ResultData();
            resultData.setData(contentBannerService.createOrUpdateContentBanner(contentBannerDTO));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 创建或更新失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述：删除Banner配置
    * @param ids Banner配置ids
    */
    @RequestMapping(value = "/deleteByIds", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData deleteByIds(@RequestParam List<String> ids) throws Exception {
        try {
            ResultData resultData = new ResultData();
            contentBannerService.deleteByIds(ids);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 删除失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述：分页查询Banner配置
    * @param
    */
    @RequestMapping(value = "/getContentBannerList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData getContentBannerList(@RequestBody Query query){
        ResultData resultData = new ResultData();
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        Map<String,Object> map = (Map<String, Object>) query.getCondition();
        List<ContentBannerDTO> list = contentBannerService.getContentBannerList(map);
        PageInfo<ContentBannerDTO> pageInfo = new PageInfo<ContentBannerDTO>(list);
        resultData.setData(pageInfo);
        return resultData;
    }

}