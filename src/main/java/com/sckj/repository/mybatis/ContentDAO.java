package com.sckj.repository.mybatis;

import com.sckj.model.Content;
import com.sckj.model.dto.ContentDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 描述：内容管理DAO 层
 * @author hww
 * @date 2018/09/25
 */
public interface ContentDAO {

    ContentDTO findDTOById(@Param("id")String id);

    Content findById(@Param("id")String id);

    /**
    * 描述：查询内容管理列表以及高级搜索(分页)
    * @param page  分页参数
    * @param contentDTO  内容管理DTO
    */
    Page<ContentDTO> findContentPage(ContentDTO contentDTO, Pageable page);

    List<ContentDTO> getContentList(Map<String,Object> map);

}
