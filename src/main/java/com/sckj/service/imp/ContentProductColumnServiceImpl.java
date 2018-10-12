package com.sckj.service.imp;

import com.sckj.model.ContentProductColumn;
import com.sckj.model.dto.ContentProductColumnDTO;
import com.sckj.repository.ContentProductColumnRepository;
import com.sckj.repository.mybatis.ContentProductColumnDAO;
import com.sckj.service.IContentProductColumnService;
import com.sckj.utils.BeanUtils;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.StringUtils;
import com.sckj.utils.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
* 描述：商品通栏配置 服务实现层
* @author hww
* @date 2018/09/25
*/
@Service
public class ContentProductColumnServiceImpl implements IContentProductColumnService {

    private static final Logger logger = LoggerFactory.getLogger(ContentProductColumnServiceImpl.class);

    @Autowired
    private ContentProductColumnDAO contentProductColumnDAO;

    @Autowired
    private ContentProductColumnRepository contentProductColumnRepository;

    @Override
    public ContentProductColumnDTO findDTOById(String id) throws Exception {
        ContentProductColumnDTO contentProductColumnDTO = contentProductColumnDAO.findDTOById(id);
        return contentProductColumnDTO;
    }

    @Override
    public  List<ContentProductColumn> findByContentid(String contentid)throws Exception{
        return contentProductColumnRepository.findByContentid(contentid);
    }

    @Override
    public  List<ContentProductColumn> findByParentid(String parentid)throws Exception{
        return contentProductColumnRepository.findByParentid(parentid);
    }

    @Override
    public ContentProductColumn findById(String id) throws Exception {
        ContentProductColumn contentProductColumn = contentProductColumnDAO.findById(id);
        return contentProductColumn;
    }

    @Override
    public ContentProductColumn saveAndFlush(ContentProductColumn contentProductColumn)throws Exception{
        return contentProductColumnRepository.saveAndFlush(contentProductColumn);
    }

    @Override
    public void deleteById(String id) throws Exception {
        contentProductColumnRepository.deleteById(id);
    }

    @Override
    public ContentProductColumnDTO createOrUpdateContentProductColumn(ContentProductColumnDTO contentProductColumnDTO) throws Exception {
        ContentProductColumn contentProductColumn = new ContentProductColumn();
        String id = contentProductColumnDTO.getId();
        if(StringUtils.isEmpty(id)){
            BeanUtils.copyProperties(contentProductColumn,contentProductColumnDTO);
            contentProductColumn.setCreateTime(DateTimeUtils.getCurrentDate());
            contentProductColumn.setId(UUIDUtils.generate());
        }else{
            contentProductColumn = contentProductColumnDAO.findById(id);
            contentProductColumn.setUpdateTime(DateTimeUtils.getCurrentDate());
            BeanUtils.copyPropertiesWithoutNull(contentProductColumn,contentProductColumnDTO);
        }

        contentProductColumn = contentProductColumnRepository.saveAndFlush(contentProductColumn);
        return this.findDTOById(contentProductColumn.getId());
    }


    /**
    * 描述：查询列表(分页)
    * @param contentProductColumnDTO 实体DTO
    * @param page  分页参数
    */
    @Override
    public Page<ContentProductColumnDTO> findContentProductColumnPage(ContentProductColumnDTO contentProductColumnDTO, Pageable page) throws Exception{
        return contentProductColumnDAO.findContentProductColumnPage(contentProductColumnDTO,page);
    }

    @Override
    @Transactional
    public void deleteByIds(List<String> ids) throws Exception {
        contentProductColumnRepository.deleteByIdIn(ids);
    }

    @Override
    public List<ContentProductColumnDTO> getContentProductColumnList(Map<String,Object> map) {
        List<ContentProductColumnDTO> lists = contentProductColumnDAO.getContentProductColumnList(map);
        return lists;
    }

}



