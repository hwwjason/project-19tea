package com.sckj.service.imp;

import com.sckj.enums.ProductShelvesEnum;
import com.sckj.exception.BusinessException;
import com.sckj.model.ProductList;
import com.sckj.model.dto.ProductListDTO;
import com.sckj.model.model.UploadDownloadModel;
import com.sckj.repository.ProductListJpa;
import com.sckj.repository.mybatis.ProductListMapper;
import com.sckj.service.IGJPService;
import com.sckj.service.IProductService;
import com.sckj.service.IUploadDownloadService;
import com.sckj.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements IProductService{

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductListJpa productListJpa;

    @Autowired
    private ProductListMapper productListMapper;

    @Autowired
    private IGJPService gjpService;

    @Autowired
    private IUploadDownloadService uploadDownloadService;

    @Override
    public void putProductToStorage(ProductList productList) {
        productList.setId(UUIDUtils.generate());
        productList.setAddtime(DateTimeUtils.getCurrentDate());
        productList.setUpdatetime(DateTimeUtils.getCurrentDate());
        productListMapper.insertSelective(productList);
    }
    @Override
    public void putProductToStorage(HttpServletRequest request) throws Exception {
        ProductList product = createProductList(request);
        if(StringUtils.isNotEmpty(product.getId())){//修改
            updateProduct(product);
        }else{//新增
            //校验不能重复
             List<ProductList>  productLists = productListJpa.findByCode(product.getCode());
             if(productLists!=null && productLists.size()>0){
                 throw  new BusinessException("商品编码重复");
             }
            product.setId(UUIDUtils.generate());
            product.setAddtime(DateTimeUtils.getCurrentDate());
            product.setUpdatetime(DateTimeUtils.getCurrentDate());
            if(product.getStock()==null){
                product.setStock(0);
            }
            productListJpa.saveAndFlush(product);

            //上传商品到管家婆
            gjpService.uploadproducts(product);
        }
    }

    private ProductList createProductList(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //上传封面图片
        Map map =multipartRequest.getFileMap();
        MultipartFile multipartFile = (MultipartFile) map.get("img");
        UploadDownloadModel uploadDownloadModel =  uploadDownloadService.uploadImage(multipartFile,request);
        String imgFilePath = uploadDownloadModel == null?null:uploadDownloadModel.getUrl();

        //上传其他属性
        Map requestMap = multipartRequest.getParameterMap();
        if(!requestMap.containsKey("slideImg[]")){
            requestMap.put("slideImg[]",null);
        }
        Map<String,Object> hashMap = new HashMap<>();
        Iterator iterator = requestMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            Object value = entry.getValue();
            Object object = new Object();
             if ("slideImg[]".equals(key)){
                 if(value == null){
                     hashMap.put("slideImg",null);
                 }else{
                     String objstr = "";
                     Object[] objArr = (Object[])value;
                     for (Object o : objArr) {
                         if(StringUtils.isEmpty(String.valueOf(objstr))){
                             objstr = (String)o;
                         }else{
                             objstr = objstr +"," +  (String)o;
                         }
                     }
                     hashMap.put("slideImg",objstr);
                 }
                 continue;
            }
            for (Object obj : (Object[])value) {
                if(obj==null) continue;
                if("stock".equals(key)){//INTEGER
                    String str = (String)obj;
                    if(StringUtils.isNotEmpty(str)){
                        object = Integer.valueOf((String) obj);
                    }else {
                        object = Integer.valueOf("0");
                    }
                }else if("price".equals(key)||"originalPrice".equals(key)){//BigDecimal
                    String str = (String)obj;
                    if(StringUtils.isNotEmpty(str)){
                        object = new BigDecimal(str);
                    }else {
                        object = new BigDecimal(0);;
                    }
                }else{
                    object = obj;
                }
            }
            hashMap.put(key,object);
        }
        ProductList productList = (ProductList) JsonUtils.mapToObject(hashMap,ProductList.class);
        productList.setImg(imgFilePath);
        productList.setSellNum(0);
        if(productList.getStock()==null){
            productList.setSellNum(0);
        }
        return productList;
    }

    @Override
    public void deleteProduct(String[] ids) {
        productListMapper.deleteProductByIds(ids);
    }

    @Override
    public void updateProduct(ProductList product) throws Exception {
        if(product.getId()!=null){//修改
            ProductList  sourceProduct = productListMapper.getOne(product.getId());
            if(sourceProduct==null){

                return;
            }
            product.setId(null);
            BeanUtils.copyPropertiesWithoutNull(sourceProduct,product);
            sourceProduct.setUpdatetime(DateTimeUtils.getCurrentDate());
            //更新对象
            productListJpa.saveAndFlush(sourceProduct);
        }
    }

    @Override
    public void updateProducts(List<ProductList> products) throws Exception {
        for (ProductList product : products) {
            updateProduct(product);
        }
    }

    @Override
    public List<ProductListDTO> getProductList(Map<String,Object> map) {
        List<ProductListDTO> productLists = productListMapper.getProductList(map);
        for (ProductListDTO productList : productLists) {
            setDtoProperties(productList);
        }
        return productLists;
    }

    private void setDtoProperties(ProductListDTO productList ){
        if(StringUtils.isEmpty(productList.getIsshelves())){
            productList.setIsshelves(ProductShelvesEnum.PutShelf.toString());
        }
        String slideImg = productList.getSlideImg();
        List<String> slideImgs = new ArrayList<>();
        if(StringUtils.isNotEmpty(slideImg)){
            String[] slideImgStrs =slideImg.split(",");
            for (String img : slideImgStrs) {
                slideImgs.add(img);
            }
        }else if(productList.getImg()==null){
            productList.setImg("");
        }
//        String introdude = productList.getIntroduce();
//        if (StringUtils.isNotEmpty(introdude)){
//            productList.setIntroduce(HtmlUtils.replaceHtmlImgSrc(introdude));//(String str, String tag, String tagAttrib, String startTag, String endTag) {
//        }
        productList.setSlideImgs(slideImgs);
    }

    public ProductList getProductById(String id){
        return productListMapper.getOne(id);
    }

    public ProductList getProductByCode(String code){
        List<ProductList> productLists =  productListJpa.findByCode(code);
        if(productLists!=null && productLists.size()>0){
            return productLists.get(0);
        }
        return null;
    }

    public ProductListDTO getProductDTOById(String id){
        ProductList productList = productListMapper.getOne(id);
        ProductListDTO productListDTO = new ProductListDTO();
        BeanUtils.copyProperties(productListDTO,productList);
        setDtoProperties(productListDTO);
        return productListDTO;
    }

    public List<ProductListDTO> synchStock (List<String> codes) throws Exception{
        List<ProductList> productLists =productListJpa.findByCodeIn(codes);
        Map<String,ProductList> productListMap = productLists.stream().collect(Collectors.toMap(ProductList::getCode,ProductList->ProductList));
        for (String code : codes) {
            String str =  gjpService.querysaleqty(code);
            Map<String,Object> map = JsonUtils.parseJSON2Map(str);
            Object response = map.get("response");
            Map<String,Object> responseMap= JsonUtils.parseJSON2Map(response.toString());
            String  saleqty= responseMap.get("saleqty").toString();//可销售库存数量
            if(productListMap.get(code)!=null){
                ProductList  productList = productListMap.get(code);
                saleqty = saleqty.split("\\.")[0];
                productList.setStock(Integer.parseInt(saleqty));
            }
        }
        productListJpa.saveAll(productLists);
        List<ProductListDTO> productListDTOS = new ArrayList<>();
        for (ProductList productList : productLists) {
            ProductListDTO productListDTO = new ProductListDTO();
            BeanUtils.copyProperties(productListDTO,productList);
            productListDTOS.add(productListDTO);
        }
        return productListDTOS;
     }
}
