package com.sckj.repository.mybatis;

import com.sckj.model.ContentLine;
import com.sckj.model.dto.ContentLineDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
/**
 * 描述：分割线配置DAO 层
 * @author hww
 * @date 2018/09/25
 */
public interface ContentLineDAO {

    ContentLineDTO findDTOById(@Param("id")String id);

    ContentLine findById(@Param("id")String id);

    /**
    * 描述：查询分割线配置列表以及高级搜索(分页)
    * @param page  分页参数
    * @param contentLineDTO  分割线配置DTO
    */
    Page<ContentLineDTO> findContentLinePage(ContentLineDTO contentLineDTO, Pageable page);

    List<ContentLineDTO> getContentLineList(Map<String,Object> map);

}
