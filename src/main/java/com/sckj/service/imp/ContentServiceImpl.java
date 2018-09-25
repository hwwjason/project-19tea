package com.sckj.service.imp;
import com.sckj.model.Content;
import com.sckj.repository.ContentRepository;
import com.sckj.service.IContentService;
import com.sckj.repository.mybatis.ContentDAO;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.StringUtils;
import com.sckj.utils.UUIDUtils;
import com.sckj.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sckj.model.dto.ContentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 描述：内容管理 服务实现层
* @author hww
* @date 2018/09/25
*/
@Service
public class ContentServiceImpl implements IContentService {

    private static final Logger logger = LoggerFactory.getLogger(ContentServiceImpl.class);

    @Autowired
    private ContentDAO contentDAO;

    @Autowired
    private ContentRepository contentRepository;

    @Override
    public ContentDTO findDTOById(String id) throws Exception {
        ContentDTO contentDTO = contentDAO.findDTOById(id);
        return contentDTO;
    }

    @Override
    public Content findById(String id) throws Exception {
        Content content = contentDAO.findById(id);
        return content;
    }

    @Override
    public Content saveAndFlush(Content content)throws Exception{
        return contentRepository.saveAndFlush(content);
    }

    @Override
    public void deleteById(String id) throws Exception {
        contentRepository.deleteById(id);
    }

    @Override
    public ContentDTO createOrUpdateContent(ContentDTO contentDTO) throws Exception {
        Content content = new Content();
        String id = contentDTO.getId();
        if(StringUtils.isEmpty(id)){
            BeanUtils.copyProperties(content,contentDTO);
            content.setCreateTime(DateTimeUtils.getCurrentDate());
            content.setId(UUIDUtils.generate());
        }else{
            content =  contentDAO.findById(id);
            content.setUpdateTime(DateTimeUtils.getCurrentDate());
            BeanUtils.copyPropertiesWithoutNull(content,contentDTO);
        }

        content = contentRepository.saveAndFlush(content);
        return this.findDTOById(content.getId());
    }


    /**
    * 描述：查询列表(分页)
    * @param contentDTO 实体DTO
    * @param page  分页参数
    */
    @Override
    public Page<ContentDTO> findContentPage(ContentDTO contentDTO, Pageable page) throws Exception{
        return contentDAO.findContentPage(contentDTO,page);
    }

    @Override
    @Transactional
    public void deleteByIds(List<String> ids) throws Exception {
        contentRepository.deleteByIdIn(ids);
    }

    @Override
    public List<ContentDTO> getContentList(Map<String,Object> map) {
        List<ContentDTO> lists = contentDAO.getContentList(map);
        return lists;
    }

}



