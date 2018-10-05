package com.sckj.service.imp;
import com.sckj.model.ContentAdvertisement;
import com.sckj.repository.ContentAdvertisementRepository;
import com.sckj.service.IContentAdvertisementService;
import com.sckj.repository.mybatis.ContentAdvertisementDAO;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.StringUtils;
import com.sckj.utils.UUIDUtils;
import com.sckj.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sckj.model.dto.ContentAdvertisementDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 描述：广告长图配置 服务实现层
* @author hww
* @date 2018/09/25
*/
@Service
public class ContentAdvertisementServiceImpl implements IContentAdvertisementService {

    private static final Logger logger = LoggerFactory.getLogger(ContentAdvertisementServiceImpl.class);

    @Autowired
    private ContentAdvertisementDAO contentAdvertisementDAO;

    @Autowired
    private ContentAdvertisementRepository contentAdvertisementRepository;

    @Override
    public ContentAdvertisementDTO findDTOById(String id) throws Exception {
        ContentAdvertisementDTO contentAdvertisementDTO = contentAdvertisementDAO.findDTOById(id);
        return contentAdvertisementDTO;
    }

    @Override
    public  List<ContentAdvertisement> findByParentid(String parentid)throws Exception{
        return contentAdvertisementRepository.findByParentid(parentid);
    }

    @Override
    public  List<ContentAdvertisement> findByParentidIn(List<String> parentids)throws Exception{
        return contentAdvertisementRepository.findByParentidIn(parentids);
    }

    @Override
    public  List<ContentAdvertisement> findByContentid(String contentid)throws Exception{
        return contentAdvertisementRepository.findByContentid(contentid);
    }


    @Override
    public ContentAdvertisement findById(String id) throws Exception {
        ContentAdvertisement contentAdvertisement = contentAdvertisementDAO.findById(id);
        return contentAdvertisement;
    }

    @Override
    public ContentAdvertisement saveAndFlush(ContentAdvertisement contentAdvertisement)throws Exception{
        return contentAdvertisementRepository.saveAndFlush(contentAdvertisement);
    }

    @Override
    public void deleteById(String id) throws Exception {
        contentAdvertisementRepository.deleteById(id);
    }

    @Override
    public ContentAdvertisementDTO createOrUpdateContentAdvertisement(ContentAdvertisementDTO contentAdvertisementDTO) throws Exception {
        ContentAdvertisement contentAdvertisement = new ContentAdvertisement();
        String id = contentAdvertisementDTO.getId();
        if(StringUtils.isEmpty(id)){
            BeanUtils.copyProperties(contentAdvertisement,contentAdvertisementDTO);
            contentAdvertisement.setCreateTime(DateTimeUtils.getCurrentDate());
            contentAdvertisement.setId(UUIDUtils.generate());
        }else{
            contentAdvertisement = contentAdvertisementDAO.findById(id);
            BeanUtils.copyPropertiesWithoutNull(contentAdvertisement,contentAdvertisementDTO);
            contentAdvertisement.setUpdateTime(DateTimeUtils.getCurrentDate());
        }

        contentAdvertisement = contentAdvertisementRepository.saveAndFlush(contentAdvertisement);
        return this.findDTOById(contentAdvertisement.getId());
    }


    /**
    * 描述：查询列表(分页)
    * @param contentAdvertisementDTO 实体DTO
    * @param page  分页参数
    */
    @Override
    public Page<ContentAdvertisementDTO> findContentAdvertisementPage(ContentAdvertisementDTO contentAdvertisementDTO, Pageable page) throws Exception{
        return contentAdvertisementDAO.findContentAdvertisementPage(contentAdvertisementDTO,page);
    }

    @Override
    @Transactional
    public void deleteByIds(List<String> ids) throws Exception {
        contentAdvertisementRepository.deleteByIdIn(ids);
    }

    @Override
    public List<ContentAdvertisementDTO> getContentAdvertisementList(Map<String,Object> map) {
        List<ContentAdvertisementDTO> lists = contentAdvertisementDAO.getContentAdvertisementList(map);
        return lists;
    }

}



