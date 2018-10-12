package com.sckj.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sckj.common.Query;
import com.sckj.common.ResultData;
import com.sckj.constant.MessageConstants;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.exception.BusinessException;
import com.sckj.model.ContentProductSlide;
import com.sckj.model.dto.ContentProductSlideDTO;
import com.sckj.service.IContentProductSlideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
* 描述：商品滑动配置控制层
* @author hww
* @date 2018/09/25
*/
@RestController
@RequestMapping("/contentProductSlide")
public class ContentProductSlideController {

    private static final Logger logger = LoggerFactory.getLogger(ContentProductSlideController.class);

    @Autowired
    private IContentProductSlideService contentProductSlideService;

    /**
    * 描述：根据Id 查询
    * @param id  商品滑动配置id
    */
    @RequestMapping(value = "/findById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findById(@RequestParam String id)throws Exception {
        try{
            ResultData resultData = new ResultData();
            ContentProductSlideDTO contentProductSlideDTO = contentProductSlideService.findDTOById(id);
            resultData.setData(contentProductSlideDTO);
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
            List<ContentProductSlide> contentProductSlides = contentProductSlideService.findByParentid(parentid);
            resultData.setData(contentProductSlides);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 查询失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述:创建或更新商品滑动配置
    * @param contentProductSlideDTO  商品滑动配置DTO
    */
    @RequestMapping(value = "/createOrUpdate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData createOrUpdate(@RequestBody ContentProductSlideDTO contentProductSlideDTO) throws Exception {
        try {
            ResultData resultData = new ResultData();
            resultData.setData(contentProductSlideService.createOrUpdateContentProductSlide(contentProductSlideDTO));
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 创建或更新失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述：删除商品滑动配置
    * @param ids 商品滑动配置ids
    */
    @RequestMapping(value = "/deleteByIds", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData deleteByIds(@RequestParam List<String> ids) throws Exception {
        try {
            ResultData resultData = new ResultData();
            contentProductSlideService.deleteByIds(ids);
            return resultData;
        }catch (BusinessException e){
            throw e;
        } catch (Exception e){
            logger.error("Error 删除失败", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    /**
    * 描述：分页查询商品滑动配置
    * @param
    */
    @RequestMapping(value = "/getContentProductSlideList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData getContentProductSlideList(@RequestBody Query query){
        ResultData resultData = new ResultData();
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        Map<String,Object> map = (Map<String, Object>) query.getCondition();
        List<ContentProductSlideDTO> list = contentProductSlideService.getContentProductSlideList(map);
        PageInfo<ContentProductSlideDTO> pageInfo = new PageInfo<ContentProductSlideDTO>(list);
        resultData.setData(pageInfo);
        return resultData;
    }

}