package com.sckj.service;
import com.sckj.model.ContentForm;
import com.sckj.model.dto.ContentFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

/**
* 描述：组件内容配置 服务实现层接口
* @author hww
* @date 2018/10/03
*/
public interface IContentFormService  {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    ContentFormDTO findDTOById(String id)throws Exception;

    ContentForm findById(String id)throws Exception;

    List<ContentForm> findByParentid(String parentid)throws Exception;

    List<ContentForm> findByParentidAndFormType(String parentid,String formType)throws Exception;

    List<ContentForm> findByParentidIn(List<String> parentid)throws Exception;

    List<ContentForm> findByParentidInAndFormType(List<String> parentid,String formType)throws Exception;

    List<ContentForm> findByContentid(String contentid)throws Exception;

    List<ContentForm> findByContentidAndFormType(String contentid,String formType)throws Exception;

    ContentForm saveAndFlush(ContentForm contentForm)throws Exception;

    void deleteById(String id)throws Exception;

    ContentFormDTO createOrUpdateContentForm(ContentFormDTO contentFormDTO) throws Exception;


    Page<ContentFormDTO> findContentFormPage(ContentFormDTO contentFormDTO, Pageable page) throws Exception;

    void deleteByIds(List<String> ids)throws Exception;

    List<ContentFormDTO> getContentFormList(Map<String,Object> contentFormListMap);
}
