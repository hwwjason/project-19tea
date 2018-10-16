package com.sckj.service.imp;

import com.sckj.enums.FormTypeEnum;
import com.sckj.exception.BusinessException;
import com.sckj.model.ContentForm;
import com.sckj.model.ProductList;
import com.sckj.model.dto.ContentFormDTO;
import com.sckj.repository.ContentFormRepository;
import com.sckj.repository.mybatis.ContentFormDAO;
import com.sckj.service.IContentFormService;
import com.sckj.service.IProductService;
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

    @Autowired
    private IProductService productService;

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
        if(contentFormDTO.getFormType()==null){
            throw new BusinessException("控件类型未指定");
        }
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

        if(contentFormDTO.getProductCode()!=null){
            ProductList productList = productService.getProductByCode(contentFormDTO.getProductCode());
            if(productList == null){
                throw new BusinessException("商品Code不存在，请校修正后保存");
            }
        }

        //第一层级 广告成都 可能为 商品，需要设置商品id
        if("1".equals(contentForm.getLevel()) && (FormTypeEnum.CONTENT_ADVER.toString().equals(contentForm.getFormType()) && "1".equals(contentForm.getJumpType()))){
            ProductList productList = productService.getProductByCode(contentForm.getProductCode());
            if(productList != null){
                contentForm.setProductid(productList.getId());
                if(contentForm.getImgUrl()==null){
                    contentForm.setImgUrl(productList.getImg());
                }
            }
        }

        //第二层级
        if("2".equals(contentForm.getLevel()) && (
                FormTypeEnum.CONTENT_PRODUCT_CLOUMN.toString().equals(contentForm.getFormType())
                || FormTypeEnum.CONTENT_PRODUCT_SLIDE.toString().equals(contentForm.getFormType())
                || (FormTypeEnum.CONTENT_BANNER.toString().equals(contentForm.getFormType()) && "1".equals(contentForm.getJumpType()))
                )){
            ProductList productList = productService.getProductByCode(contentForm.getProductCode());
            if(productList != null){
                contentForm.setImgUrl(productList.getImg());
                contentForm.setProductid(productList.getId());
            }
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



