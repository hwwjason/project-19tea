package com.sckj.service.imp;
import com.sckj.model.*;
import com.sckj.model.dto.*;
import com.sckj.repository.ContentRepository;
import com.sckj.service.*;
import com.sckj.repository.mybatis.ContentDAO;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.StringUtils;
import com.sckj.utils.UUIDUtils;
import com.sckj.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.management.AttributeList;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private IContentAdvertisementService contentAdvertisementService;

    @Autowired
    private IContentBannerService contentBannerService;

    @Autowired
    private IContentLineService contentLineService;

    @Autowired
    private IContentProductColumnService contentProductColumnService;

    @Autowired
    private IContentProductSlideService contentProductSlideService;

    @Override
    public ContentDTO findDTOById(String id) throws Exception {
        ContentDTO contentDTO = contentDAO.findDTOById(id);
        return contentDTO;
    }

    @Override
    public List<Object> findByContentid(String contentid)throws Exception{
        Content content = findById(contentid);
        String orders = content.getOrders();
        String[] orderList =  orders.split(",");
        List<Object> contents = new ArrayList<>();

        List<ContentAdvertisement> contentAdvertisement = contentAdvertisementService.findByContentid(contentid);
        Map<String,ContentAdvertisement> contentAdvertisementMap = contentAdvertisement.stream().collect(Collectors.toMap(ContentAdvertisement::getId, ContentAdvertisement->ContentAdvertisement));

        List<ContentBanner> contentBanners = contentBannerService.findByContentid(contentid);
        Map<String,ContentBanner> contentBannerMap = contentBanners.stream().collect(Collectors.toMap(ContentBanner::getId, ContentBanner->ContentBanner));

        List<ContentLine> contentLines = contentLineService.findByContentid(contentid);
        Map<String,ContentLine>  contentLineMap= contentLines.stream().collect(Collectors.toMap(ContentLine::getId, ContentLine->ContentLine));


        List<ContentProductColumn> contentProductColumns = contentProductColumnService.findByContentid(contentid);
        Map<String,ContentProductColumn> contentProductColumnMap = contentProductColumns.stream().collect(Collectors.toMap(ContentProductColumn::getId, ContentProductColumn->ContentProductColumn));

        List<ContentProductSlide> contentProductSlides = contentProductSlideService.findByContentid(contentid);
        Map<String,ContentProductSlide> contentProductSlideMap = contentProductSlides.stream().collect(Collectors.toMap(ContentProductSlide::getId, ContentProductSlide->ContentProductSlide));
        for (String s : orderList) {
            if(contentAdvertisementMap.get(s)!=null){
                ContentAdvertisementDTO contentAdvertisementDTO = new ContentAdvertisementDTO();
                BeanUtils.copyPropertiesWithoutNull(contentAdvertisementDTO,contentAdvertisementMap.get(s));
                contents.add(contentAdvertisementDTO);
            }
            if(contentBannerMap.get(s)!=null){
                ContentBannerDTO contentBannerDTO = new ContentBannerDTO();
                BeanUtils.copyPropertiesWithoutNull(contentBannerDTO,contentBannerMap.get(s));
                contents.add(contentBannerDTO);
            }
            if(contentLineMap.get(s)!=null){
                ContentLineDTO contentLine = new ContentLineDTO();
                BeanUtils.copyPropertiesWithoutNull(contentLine,contentBannerMap.get(s));
                contents.add(contentLine);
            }
            if(contentProductColumnMap.get(s)!=null){
                ContentProductColumnDTO contentProductColumnDTO = new ContentProductColumnDTO();
                BeanUtils.copyPropertiesWithoutNull(contentProductColumnDTO,contentBannerMap.get(s));
                contents.add(contentProductColumnDTO);
            }
            if(contentProductSlideMap.get(s)!=null){
                ContentProductSlideDTO ContentProductSlideDTO = new ContentProductSlideDTO();
                BeanUtils.copyPropertiesWithoutNull(ContentProductSlideDTO,contentBannerMap.get(s));
                contents.add(ContentProductSlideDTO);
            }
        }
        return contents;
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



