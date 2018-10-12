package com.sckj.service.imp;

import com.sckj.model.ContentBanner;
import com.sckj.model.dto.ContentBannerDTO;
import com.sckj.repository.ContentBannerRepository;
import com.sckj.repository.mybatis.ContentBannerDAO;
import com.sckj.service.IContentBannerService;
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
* 描述：Banner配置 服务实现层
* @author hww
* @date 2018/09/25
*/
@Service
public class ContentBannerServiceImpl implements IContentBannerService {

    private static final Logger logger = LoggerFactory.getLogger(ContentBannerServiceImpl.class);

    @Autowired
    private ContentBannerDAO contentBannerDAO;

    @Autowired
    private ContentBannerRepository contentBannerRepository;

    @Override
    public ContentBannerDTO findDTOById(String id) throws Exception {
        ContentBannerDTO contentBannerDTO = contentBannerDAO.findDTOById(id);
        return contentBannerDTO;
    }

    @Override
    public  List<ContentBanner> findByParentid(String parentid)throws Exception{
        return contentBannerRepository.findByParentid(parentid);
    }

    @Override
    public  List<ContentBanner> findByContentid(String contentid)throws Exception{
        return contentBannerRepository.findByContentid(contentid);
    }

    @Override
    public ContentBanner findById(String id) throws Exception {
        ContentBanner contentBanner = contentBannerDAO.findById(id);
        return contentBanner;
    }

    @Override
    public ContentBanner saveAndFlush(ContentBanner contentBanner)throws Exception{
        return contentBannerRepository.saveAndFlush(contentBanner);
    }

    @Override
    public void deleteById(String id) throws Exception {
        contentBannerRepository.deleteById(id);
    }

    @Override
    public ContentBannerDTO createOrUpdateContentBanner(ContentBannerDTO contentBannerDTO) throws Exception {
        ContentBanner contentBanner = new ContentBanner();
        String id = contentBannerDTO.getId();
        if(StringUtils.isEmpty(id)){
            BeanUtils.copyProperties(contentBanner,contentBannerDTO);
            contentBanner.setCreateTime(DateTimeUtils.getCurrentDate());
            contentBanner.setId(UUIDUtils.generate());
        }else{
            contentBanner = contentBannerDAO.findById(id);
            contentBanner.setUpdateTime(DateTimeUtils.getCurrentDate());
            BeanUtils.copyPropertiesWithoutNull(contentBanner,contentBannerDTO);
        }

        contentBanner = contentBannerRepository.saveAndFlush(contentBanner);
        return this.findDTOById(contentBanner.getId());
    }


    /**
    * 描述：查询列表(分页)
    * @param contentBannerDTO 实体DTO
    * @param page  分页参数
    */
    @Override
    public Page<ContentBannerDTO> findContentBannerPage(ContentBannerDTO contentBannerDTO, Pageable page) throws Exception{
        return contentBannerDAO.findContentBannerPage(contentBannerDTO,page);
    }

    @Override
    @Transactional
    public void deleteByIds(List<String> ids) throws Exception {
        contentBannerRepository.deleteByIdIn(ids);
    }

    @Override
    public List<ContentBannerDTO> getContentBannerList(Map<String,Object> map) {
        List<ContentBannerDTO> lists = contentBannerDAO.getContentBannerList(map);
        return lists;
    }

}



