package com.sckj.service;
import com.sckj.model.ContentBanner;
import com.sckj.model.dto.ContentBannerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

/**
* 描述：Banner配置 服务实现层接口
* @author hww
* @date 2018/09/25
*/
public interface IContentBannerService  {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    ContentBannerDTO findDTOById(String id)throws Exception;

    List<ContentBanner> findByParentid(String parentid)throws Exception;

    List<ContentBanner> findByContentid(String contentid)throws Exception;

    ContentBanner findById(String id)throws Exception;

    ContentBanner saveAndFlush(ContentBanner contentBanner)throws Exception;

    void deleteById(String id)throws Exception;

    ContentBannerDTO createOrUpdateContentBanner(ContentBannerDTO contentBannerDTO) throws Exception;


    Page<ContentBannerDTO> findContentBannerPage(ContentBannerDTO contentBannerDTO, Pageable page) throws Exception;

    void deleteByIds(List<String> ids)throws Exception;

    List<ContentBannerDTO> getContentBannerList(Map<String,Object> contentBannerListMap);
}
