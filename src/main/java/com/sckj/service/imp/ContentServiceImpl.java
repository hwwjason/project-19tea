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
import java.util.HashMap;
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

    @Autowired
    private IContentFormService contentFormService;

    @Override
    public ContentDTO findDTOById(String id) throws Exception {
        ContentDTO contentDTO = contentDAO.findDTOById(id);
        return contentDTO;
    }

    @Override
    public List<Object> findByContentid(String contentid)throws Exception{
        Content content = findById(contentid);
        List<Object> contents = new ArrayList<>();
        String orders = content.getOrders();
        if(orders==null){
            return contents;
        }
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
        String[] orderList =  orders.split(",");
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
                BeanUtils.copyPropertiesWithoutNull(contentLine,contentLineMap.get(s));
                contents.add(contentLine);
            }
            if(contentProductColumnMap.get(s)!=null){
                ContentProductColumnDTO contentProductColumnDTO = new ContentProductColumnDTO();
                BeanUtils.copyPropertiesWithoutNull(contentProductColumnDTO,contentProductColumnMap.get(s));
                contents.add(contentProductColumnDTO);
            }
            if(contentProductSlideMap.get(s)!=null){
                ContentProductSlideDTO ContentProductSlideDTO = new ContentProductSlideDTO();
                BeanUtils.copyPropertiesWithoutNull(ContentProductSlideDTO,contentProductSlideMap.get(s));
                contents.add(ContentProductSlideDTO);
            }
        }
        return contents;
    }

    @Override
    public ContentDTO findByContentidAndIsContainSecond(String contentid,boolean isContainSecondLevel)throws Exception{
        Content content = findById(contentid);
        if(content==null){
            return null;
        }
        ContentDTO contentDTO = new ContentDTO();
        BeanUtils.copyProperties(contentDTO,content);
        String orders = content.getOrders();
        if(orders==null){
            return contentDTO;
        }

        List<ContentForm> contentForms = contentFormService.findByContentid(contentid);
        List<ContentFormDTO> contentFormDTOS = new ArrayList<>();//父节点
        for (ContentForm contentForm : contentForms) {//拷贝
            ContentFormDTO contentFormDTO = new ContentFormDTO();
            BeanUtils.copyProperties(contentFormDTO,contentForm);
            contentFormDTOS.add(contentFormDTO);
        }

        if (isContainSecondLevel){
            List<String> contentFormIds = contentForms.stream().map(e->e.getId()).collect(Collectors.toList());
            List<ContentForm> contentFormChilds = contentFormService.findByParentidIn(contentFormIds);
            Map<String,List<ContentForm>> contentFormChildMap = new HashMap<>();
            for (ContentForm contentFormChild : contentFormChilds) {
                if(contentFormChildMap.get(contentFormChild.getParentid())!=null){
                    List<ContentForm> contentFormList = contentFormChildMap.get(contentFormChild.getParentid());
                    contentFormList.add(contentFormChild);
                }else{
                    List<ContentForm> contentFormList = new ArrayList<>();
                    contentFormList.add(contentFormChild);
                    contentFormChildMap.put(contentFormChild.getParentid(),contentFormList);
                }
            }
            for (ContentFormDTO formDTO : contentFormDTOS) {
                formDTO.setContentForms(contentFormChildMap.get(formDTO.getId()));
            }
        }

        //父节点按 orders 数组排序
        List<ContentFormDTO> contentFormDTOSByOrder = new ArrayList<>();
        Map<String,ContentFormDTO> contentFormDTOMap =  contentFormDTOS.stream().collect(Collectors.toMap(ContentFormDTO::getId,ContentFormDTO->ContentFormDTO));
        String[] orderList =  orders.split(",");
        for (String s : orderList) {
            if(contentFormDTOMap.get(s)!=null){
                contentFormDTOSByOrder.add(contentFormDTOMap.get(s));
            }
        }

        contentDTO.setContentFormDTOS(contentFormDTOSByOrder);
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



