package com.sckj.repository.mybatis;

import com.sckj.model.ContentAdvertisement;
import com.sckj.model.dto.ContentAdvertisementDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
/**
 * 描述：广告长图配置DAO 层
 * @author hww
 * @date 2018/09/25
 */
public interface ContentAdvertisementDAO {

    ContentAdvertisementDTO findDTOById(@Param("id")String id);

    ContentAdvertisement findById(@Param("id")String id);

    /**
    * 描述：查询广告长图配置列表以及高级搜索(分页)
    * @param page  分页参数
    * @param contentAdvertisementDTO  广告长图配置DTO
    */
    Page<ContentAdvertisementDTO> findContentAdvertisementPage(ContentAdvertisementDTO contentAdvertisementDTO, Pageable page);

    List<ContentAdvertisementDTO> getContentAdvertisementList(Map<String,Object> map);

}
