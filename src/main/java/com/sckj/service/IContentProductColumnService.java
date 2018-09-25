package com.sckj.service;
import com.sckj.model.ContentProductColumn;
import com.sckj.model.dto.ContentProductColumnDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

/**
* 描述：商品通栏配置 服务实现层接口
* @author hww
* @date 2018/09/25
*/
public interface IContentProductColumnService  {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    ContentProductColumnDTO findDTOById(String id)throws Exception;

    ContentProductColumn findById(String id)throws Exception;

    ContentProductColumn saveAndFlush(ContentProductColumn contentProductColumn)throws Exception;

    void deleteById(String id)throws Exception;

    ContentProductColumnDTO createOrUpdateContentProductColumn(ContentProductColumnDTO contentProductColumnDTO) throws Exception;


    Page<ContentProductColumnDTO> findContentProductColumnPage(ContentProductColumnDTO contentProductColumnDTO, Pageable page) throws Exception;

    void deleteByIds(List<String> ids)throws Exception;

    List<ContentProductColumnDTO> getContentProductColumnList(Map<String,Object> contentProductColumnListMap);
}
