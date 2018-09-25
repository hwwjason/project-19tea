package com.sckj.service.imp;
import com.sckj.model.ContentProductSlide;
import com.sckj.repository.ContentProductSlideRepository;
import com.sckj.service.IContentProductSlideService;
import com.sckj.repository.mybatis.ContentProductSlideDAO;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.StringUtils;
import com.sckj.utils.UUIDUtils;
import com.sckj.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sckj.model.dto.ContentProductSlideDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 描述：商品滑动配置 服务实现层
* @author hww
* @date 2018/09/25
*/
@Service
public class ContentProductSlideServiceImpl implements IContentProductSlideService {

    private static final Logger logger = LoggerFactory.getLogger(ContentProductSlideServiceImpl.class);

    @Autowired
    private ContentProductSlideDAO contentProductSlideDAO;

    @Autowired
    private ContentProductSlideRepository contentProductSlideRepository;

    @Override
    public ContentProductSlideDTO findDTOById(String id) throws Exception {
        ContentProductSlideDTO contentProductSlideDTO = contentProductSlideDAO.findDTOById(id);
        return contentProductSlideDTO;
    }

    @Override
    public ContentProductSlide findById(String id) throws Exception {
        ContentProductSlide contentProductSlide = contentProductSlideDAO.findById(id);
        return contentProductSlide;
    }

    @Override
    public ContentProductSlide saveAndFlush(ContentProductSlide contentProductSlide)throws Exception{
        return contentProductSlideRepository.saveAndFlush(contentProductSlide);
    }

    @Override
    public void deleteById(String id) throws Exception {
        contentProductSlideRepository.deleteById(id);
    }

    @Override
    public ContentProductSlideDTO createOrUpdateContentProductSlide(ContentProductSlideDTO contentProductSlideDTO) throws Exception {
        ContentProductSlide contentProductSlide = new ContentProductSlide();
        String id = contentProductSlideDTO.getId();
        if(StringUtils.isEmpty(id)){
            BeanUtils.copyProperties(contentProductSlide,contentProductSlideDTO);
            contentProductSlide.setCreateTime(DateTimeUtils.getCurrentDate());
            contentProductSlide.setId(UUIDUtils.generate());
        }else{
            contentProductSlide = contentProductSlideDAO.findById(id);
            contentProductSlide.setUpdateTime(DateTimeUtils.getCurrentDate());
            BeanUtils.copyPropertiesWithoutNull(contentProductSlide,contentProductSlideDTO);
        }

        contentProductSlide = contentProductSlideRepository.saveAndFlush(contentProductSlide);
        return this.findDTOById(contentProductSlide.getId());
    }


    /**
    * 描述：查询列表(分页)
    * @param contentProductSlideDTO 实体DTO
    * @param page  分页参数
    */
    @Override
    public Page<ContentProductSlideDTO> findContentProductSlidePage(ContentProductSlideDTO contentProductSlideDTO, Pageable page) throws Exception{
        return contentProductSlideDAO.findContentProductSlidePage(contentProductSlideDTO,page);
    }

    @Override
    @Transactional
    public void deleteByIds(List<String> ids) throws Exception {
        contentProductSlideRepository.deleteByIdIn(ids);
    }

    @Override
    public List<ContentProductSlideDTO> getContentProductSlideList(Map<String,Object> map) {
        List<ContentProductSlideDTO> lists = contentProductSlideDAO.getContentProductSlideList(map);
        return lists;
    }

}



