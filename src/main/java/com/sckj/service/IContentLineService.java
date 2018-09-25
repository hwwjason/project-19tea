package com.sckj.service;
import com.sckj.model.ContentLine;
import com.sckj.model.dto.ContentLineDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

/**
* 描述：分割线配置 服务实现层接口
* @author hww
* @date 2018/09/25
*/
public interface IContentLineService  {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    ContentLineDTO findDTOById(String id)throws Exception;

    ContentLine findById(String id)throws Exception;

    ContentLine saveAndFlush(ContentLine contentLine)throws Exception;

    void deleteById(String id)throws Exception;

    ContentLineDTO createOrUpdateContentLine(ContentLineDTO contentLineDTO) throws Exception;


    Page<ContentLineDTO> findContentLinePage(ContentLineDTO contentLineDTO, Pageable page) throws Exception;

    void deleteByIds(List<String> ids)throws Exception;

    List<ContentLineDTO> getContentLineList(Map<String,Object> contentLineListMap);
}
