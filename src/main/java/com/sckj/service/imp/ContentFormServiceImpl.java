package com.sckj.service.imp;
import com.sckj.model.ContentForm;
import com.sckj.repository.ContentFormRepository;
import com.sckj.service.IContentFormService;
import com.sckj.repository.mybatis.ContentFormDAO;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.StringUtils;
import com.sckj.utils.UUIDUtils;
import com.sckj.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sckj.model.dto.ContentFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 描述：组件内容配置 服务实现层
* @author hww
* @date 2018/10/03
*/
@Service
public class ContentFormServiceImpl implements IContentFormService {

    private static final Logger logger = LoggerFactory.getLogger(ContentFormServiceImpl.class);

    @Autowired
    private ContentFormDAO contentFormDAO;

    @Autowired
    private ContentFormRepository contentFormRepository;

    @Override
    public ContentFormDTO findDTOById(String id) throws Exception {
        ContentFormDTO contentFormDTO = contentFormDAO.findDTOById(id);
        return contentFormDTO;
    }

    @Override
    public ContentForm findById(String id) throws Exception {
        ContentForm contentForm = contentFormDAO.findById(id);
        return contentForm;
    }

    @Override
    public List<ContentForm> findByParentid(String parentid) throws Exception {
        return contentFormRepository.findByParentid(parentid);
    }

    @Override
    public List<ContentForm> findByParentidAndFormType(String parentid, String formType) throws Exception {
        return contentFormRepository.findByParentidAndFormType(parentid,formType);
    }

    @Override
    public List<ContentForm> findByParentidIn(List<String> parentid) throws Exception {
        return contentFormRepository.findByParentidIn(parentid);
    }

    @Override
    public List<ContentForm> findByParentidInAndFormType(List<String> parentid, String formType) throws Exception {
        return contentFormRepository.findByParentidInAndFormType(parentid,formType);
    }

    @Override
    public List<ContentForm> findByContentid(String contentid) throws Exception {
        return contentFormRepository.findByContentid(contentid);
    }

    @Override
    public List<ContentForm> findByContentidAndFormType(String contentid, String formType) throws Exception {
        return contentFormRepository.findByContentidAndFormType(contentid,formType);
    }


    @Override
    public ContentForm saveAndFlush(ContentForm contentForm)throws Exception{
        return contentFormRepository.saveAndFlush(contentForm);
    }

    @Override
    public void deleteById(String id) throws Exception {
        contentFormRepository.deleteById(id);
    }

    @Override
    public ContentFormDTO createOrUpdateContentForm(ContentFormDTO contentFormDTO) throws Exception {
        ContentForm contentForm = new ContentForm();
        String id = contentFormDTO.getId();
        if(StringUtils.isEmpty(id)){
            BeanUtils.copyProperties(contentForm,contentFormDTO);
            contentForm.setCreateTime(DateTimeUtils.getCurrentDate());
            contentForm.setId(UUIDUtils.generate());
        }else{
            contentForm = contentFormDAO.findById(id);
            BeanUtils.copyPropertiesWithoutNull(contentForm,contentFormDTO);
            contentForm.setUpdateTime(DateTimeUtils.getCurrentDate());
        }

        contentForm = contentFormRepository.saveAndFlush(contentForm);
        return this.findDTOById(contentForm.getId());
    }


    /**
    * 描述：查询列表(分页)
    * @param contentFormDTO 实体DTO
    * @param page  分页参数
    */
    @Override
    public Page<ContentFormDTO> findContentFormPage(ContentFormDTO contentFormDTO, Pageable page) throws Exception{
        return contentFormDAO.findContentFormPage(contentFormDTO,page);
    }

    @Override
    @Transactional
    public void deleteByIds(List<String> ids) throws Exception {
        contentFormRepository.deleteByIdIn(ids);
    }

    @Override
    public List<ContentFormDTO> getContentFormList(Map<String,Object> map) {
        List<ContentFormDTO> lists = contentFormDAO.getContentFormList(map);
        return lists;
    }

}



