package com.sckj.repository.mybatis;

import com.sckj.model.ContentForm;
import com.sckj.model.dto.ContentFormDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
/**
 * 描述：组件内容配置DAO 层
 * @author hww
 * @date 2018/10/03
 */
public interface ContentFormDAO {

    ContentFormDTO findDTOById(@Param("id")String id);

    ContentForm findById(@Param("id")String id);

    /**
    * 描述：查询组件内容配置列表以及高级搜索(分页)
    * @param page  分页参数
    * @param contentFormDTO  组件内容配置DTO
    */
    Page<ContentFormDTO> findContentFormPage(ContentFormDTO contentFormDTO, Pageable page);

    List<ContentFormDTO> getContentFormList(Map<String,Object> map);

}
