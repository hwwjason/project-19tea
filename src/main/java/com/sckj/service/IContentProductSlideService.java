package com.sckj.service;
import com.sckj.model.ContentProductSlide;
import com.sckj.model.dto.ContentProductSlideDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

/**
* 描述：商品滑动配置 服务实现层接口
* @author hww
* @date 2018/09/25
*/
public interface IContentProductSlideService  {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    ContentProductSlideDTO findDTOById(String id)throws Exception;

    ContentProductSlide findById(String id)throws Exception;

    ContentProductSlide saveAndFlush(ContentProductSlide contentProductSlide)throws Exception;

    void deleteById(String id)throws Exception;

    ContentProductSlideDTO createOrUpdateContentProductSlide(ContentProductSlideDTO contentProductSlideDTO) throws Exception;


    Page<ContentProductSlideDTO> findContentProductSlidePage(ContentProductSlideDTO contentProductSlideDTO, Pageable page) throws Exception;

    void deleteByIds(List<String> ids)throws Exception;

    List<ContentProductSlideDTO> getContentProductSlideList(Map<String,Object> contentProductSlideListMap);
}
