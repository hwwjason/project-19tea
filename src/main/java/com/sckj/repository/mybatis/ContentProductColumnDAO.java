package com.sckj.repository.mybatis;

import com.sckj.model.ContentProductColumn;
import com.sckj.model.dto.ContentProductColumnDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
/**
 * 描述：商品通栏配置DAO 层
 * @author hww
 * @date 2018/09/25
 */
public interface ContentProductColumnDAO {

    ContentProductColumnDTO findDTOById(@Param("id")String id);

    ContentProductColumn findById(@Param("id")String id);

    /**
    * 描述：查询商品通栏配置列表以及高级搜索(分页)
    * @param page  分页参数
    * @param contentProductColumnDTO  商品通栏配置DTO
    */
    Page<ContentProductColumnDTO> findContentProductColumnPage(ContentProductColumnDTO contentProductColumnDTO, Pageable page);

    List<ContentProductColumnDTO> getContentProductColumnList(Map<String,Object> map);

}
