package com.sckj.service;
import com.sckj.model.ContentAdvertisement;
import com.sckj.model.dto.ContentAdvertisementDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

/**
* 描述：广告长图配置 服务实现层接口
* @author hww
* @date 2018/09/25
*/
public interface IContentAdvertisementService  {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    ContentAdvertisementDTO findDTOById(String id)throws Exception;

    List<ContentAdvertisement> findByParentid(String parentid)throws Exception;

    List<ContentAdvertisement> findByParentidIn(List<String> parentid)throws Exception;

    List<ContentAdvertisement> findByContentid(String contentid)throws Exception;

    ContentAdvertisement findById(String id)throws Exception;

    ContentAdvertisement saveAndFlush(ContentAdvertisement contentAdvertisement)throws Exception;

    void deleteById(String id)throws Exception;

    ContentAdvertisementDTO createOrUpdateContentAdvertisement(ContentAdvertisementDTO contentAdvertisementDTO) throws Exception;


    Page<ContentAdvertisementDTO> findContentAdvertisementPage(ContentAdvertisementDTO contentAdvertisementDTO, Pageable page) throws Exception;

    void deleteByIds(List<String> ids)throws Exception;

    List<ContentAdvertisementDTO> getContentAdvertisementList(Map<String,Object> contentAdvertisementListMap);
}
