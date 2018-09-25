package com.sckj.repository.mybatis;

import com.sckj.model.ContentProductSlide;
import com.sckj.model.dto.ContentProductSlideDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
/**
 * 描述：商品滑动配置DAO 层
 * @author hww
 * @date 2018/09/25
 */
public interface ContentProductSlideDAO {

    ContentProductSlideDTO findDTOById(@Param("id")String id);

    ContentProductSlide findById(@Param("id")String id);

    /**
    * 描述：查询商品滑动配置列表以及高级搜索(分页)
    * @param page  分页参数
    * @param contentProductSlideDTO  商品滑动配置DTO
    */
    Page<ContentProductSlideDTO> findContentProductSlidePage(ContentProductSlideDTO contentProductSlideDTO, Pageable page);

    List<ContentProductSlideDTO> getContentProductSlideList(Map<String,Object> map);

}
