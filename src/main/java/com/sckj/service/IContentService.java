package com.sckj.service;
import com.sckj.model.Content;
import com.sckj.model.dto.ContentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

/**
* 描述：内容管理 服务实现层接口
* @author hww
* @date 2018/09/25
*/
public interface IContentService  {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    ContentDTO findDTOById(String id)throws Exception;

    List<Object> findByContentid(String contentid)throws Exception;

    Content findById(String id)throws Exception;

    Content saveAndFlush(Content content)throws Exception;

    void deleteById(String id)throws Exception;

    ContentDTO createOrUpdateContent(ContentDTO contentDTO) throws Exception;


    Page<ContentDTO> findContentPage(ContentDTO contentDTO, Pageable page) throws Exception;

    void deleteByIds(List<String> ids)throws Exception;

    List<ContentDTO> getContentList(Map<String,Object> contentListMap);
}
