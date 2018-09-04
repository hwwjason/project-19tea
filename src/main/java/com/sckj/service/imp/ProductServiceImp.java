package com.sckj.service.imp;

import com.sckj.enums.ProductShelvesEnum;
import com.sckj.utils.BeanUtils;
import com.sckj.common.ResultData;
import com.sckj.dao.ProductListMapper;
import com.sckj.dto.ProductListDTO;
import com.sckj.jpa.ProductListJpa;
import com.sckj.pojo.ProductList;
import com.sckj.service.IProductService;
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

@Service
public class ProductServiceImp implements IProductService{

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductListJpa productListJpa;

    @Autowired
    private ProductListMapper productListMapper;

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
        if(product.getId()!=null){//修改
            updateProduct(product);
        }else{//新增
            product.setId(UUIDUtils.generate());
            product.setAddtime(DateTimeUtils.getCurrentDate());
            product.setUpdatetime(DateTimeUtils.getCurrentDate());
            productListJpa.saveAndFlush(product);
        }
    }

    private ProductList createProductList(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //上传图片
        Map map =multipartRequest.getFileMap();
        MultipartFile multipartFile = (MultipartFile) map.get("img");
        ResultData resultDataFile = FileUtils.uploadImage(multipartFile,request);
        String imgFilePath = resultDataFile.getPath();

        //上传其他属性
        Map requestMap = multipartRequest.getParameterMap();
        Map<String,Object> hashMap = new HashMap<>();
        Iterator iterator = requestMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            Object value = entry.getValue();
            Object object = new Object();
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
            if(StringUtils.isEmpty(productList.getIsshelves())){
                productList.setIsshelves(ProductShelvesEnum.PutShelf.toString());
            }
        }
        return productLists;
    }
}
