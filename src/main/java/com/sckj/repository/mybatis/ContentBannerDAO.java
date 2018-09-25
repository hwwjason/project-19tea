package com.sckj.repository.mybatis;

import com.sckj.model.ContentBanner;
import com.sckj.model.dto.ContentBannerDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
/**
 * 描述：Banner配置DAO 层
 * @author hww
 * @date 2018/09/25
 */
public interface ContentBannerDAO {

    ContentBannerDTO findDTOById(@Param("id")String id);

    ContentBanner findById(@Param("id")String id);

    /**
    * 描述：查询Banner配置列表以及高级搜索(分页)
    * @param page  分页参数
    * @param contentBannerDTO  Banner配置DTO
    */
    Page<ContentBannerDTO> findContentBannerPage(ContentBannerDTO contentBannerDTO, Pageable page);

    List<ContentBannerDTO> getContentBannerList(Map<String,Object> map);

}
