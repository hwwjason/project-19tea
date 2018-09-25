package com.sckj.service.imp;
import com.sckj.model.ContentLine;
import com.sckj.repository.ContentLineRepository;
import com.sckj.service.IContentLineService;
import com.sckj.repository.mybatis.ContentLineDAO;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.StringUtils;
import com.sckj.utils.UUIDUtils;
import com.sckj.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sckj.model.dto.ContentLineDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 描述：分割线配置 服务实现层
* @author hww
* @date 2018/09/25
*/
@Service
public class ContentLineServiceImpl implements IContentLineService {

    private static final Logger logger = LoggerFactory.getLogger(ContentLineServiceImpl.class);

    @Autowired
    private ContentLineDAO contentLineDAO;

    @Autowired
    private ContentLineRepository contentLineRepository;

    @Override
    public ContentLineDTO findDTOById(String id) throws Exception {
        ContentLineDTO contentLineDTO = contentLineDAO.findDTOById(id);
        return contentLineDTO;
    }

    @Override
    public ContentLine findById(String id) throws Exception {
        ContentLine contentLine = contentLineDAO.findById(id);
        return contentLine;
    }

    @Override
    public ContentLine saveAndFlush(ContentLine contentLine)throws Exception{
        return contentLineRepository.saveAndFlush(contentLine);
    }

    @Override
    public void deleteById(String id) throws Exception {
        contentLineRepository.deleteById(id);
    }

    @Override
    public ContentLineDTO createOrUpdateContentLine(ContentLineDTO contentLineDTO) throws Exception {
        ContentLine contentLine = new ContentLine();
        String id = contentLineDTO.getId();
        if(StringUtils.isEmpty(id)){
            BeanUtils.copyProperties(contentLine,contentLineDTO);
            contentLine.setCreateTime(DateTimeUtils.getCurrentDate());
            contentLine.setId(UUIDUtils.generate());
        }else{
            contentLine = contentLineDAO.findById(id);
            contentLine.setUpdateTime(DateTimeUtils.getCurrentDate());
            BeanUtils.copyPropertiesWithoutNull(contentLine,contentLineDTO);
        }

        contentLine = contentLineRepository.saveAndFlush(contentLine);
        return this.findDTOById(contentLine.getId());
    }


    /**
    * 描述：查询列表(分页)
    * @param contentLineDTO 实体DTO
    * @param page  分页参数
    */
    @Override
    public Page<ContentLineDTO> findContentLinePage(ContentLineDTO contentLineDTO, Pageable page) throws Exception{
        return contentLineDAO.findContentLinePage(contentLineDTO,page);
    }

    @Override
    @Transactional
    public void deleteByIds(List<String> ids) throws Exception {
        contentLineRepository.deleteByIdIn(ids);
    }

    @Override
    public List<ContentLineDTO> getContentLineList(Map<String,Object> map) {
        List<ContentLineDTO> lists = contentLineDAO.getContentLineList(map);
        return lists;
    }

}



