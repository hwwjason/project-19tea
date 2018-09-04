package com.sckj.service.imp;

import com.sckj.common.ResultData;
import com.sckj.dao.ProductListMapper;
import com.sckj.dto.ProductListDTO;
import com.sckj.jpa.ProductListJpa;
import com.sckj.pojo.ProductList;
import com.sckj.service.IProductService;
import com.sckj.utils.*;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImp implements IProductService{

    @Autowired
    private ProductListJpa productListJpa;

    @Autowired
    private ProductListMapper productListMapper;

    @Override
    public void putProductToStorage(ProductList productList) {
        productList.setId(UUIDUtils.generate());
        productList.setAddtime(DateTimeUtils.getCurrentDate());
        productList.setUpdatetime(DateTimeUtils.getCurrentDate());
//        productListJpa.saveAndFlush(productList);
        productListMapper.insertSelective(productList);
    }
    @Override
    public void putProductToStorage(HttpServletRequest request) throws Exception {


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
                if("stock".equals(key)){//INTEGER
                    object = Integer.valueOf((String) obj);
                }else if("price".equals(key)||"originalPrice".equals(key)){//BigDecimal
                    object = new BigDecimal((String) obj);
                }else{
                    object = obj;
                }
            }
            hashMap.put(key,object);
        }
        ProductList productList = (ProductList) JsonUtils.mapToObject(hashMap,ProductList.class);

        productList.setImg(imgFilePath);
        productList.setId(UUIDUtils.generate());
        productList.setAddtime(DateTimeUtils.getCurrentDate());
        productList.setUpdatetime(DateTimeUtils.getCurrentDate());
        productListMapper.insertSelective(productList);
    }

    @Override
    public void deleteProduct(String[] ids) {
//        for (String id : productIds) {
//            productListJpa.deleteById(id);
//        }
        productListMapper.deleteProductByIds(ids);
    }

    @Override
    public void updateProduct(ProductList productList) throws Exception {
        if(productList.getId()!=null){
            ProductList  sorceProduct = productListJpa.getOne(productList.getId());
            Field[] field1 = productList.getClass().getDeclaredFields(); //获取实体类的所有属性，返回Field数组
            ProductList temp = new ProductList();
            Field[] fieldTemp = temp.getClass().getDeclaredFields();
            Field[] field = ArrayUtils.addAll(field1,fieldTemp);

            for(int j=0 ; j<field.length ; j++) { //遍历所有属性
                String name = StringUtils.toUpperCaseFirstOne(field[j].getName()); //获取属性的名字
                if("Id".equals(name)){
                    continue;
                }
                String type = field[j].getGenericType().toString(); //获取属性的类型
                if(type.equals("class java.lang.String")){ //如果type是类类型，则前面包含"class "，后面跟类名
                    Method getMethod = productList.getClass().getMethod("get"+name);
                    String value = (String) getMethod.invoke(productList);
                    if(value!=null){
                        Method setMethod = sorceProduct.getClass().getMethod("set"+name, new Class[] {String.class});
                        setMethod.invoke(sorceProduct,new Object[] {value});
                    }
                }else if(type.equals("class java.lang.Integer")){
                    Method getMethod = productList.getClass().getMethod("get"+name);
                    Integer value = (Integer) getMethod.invoke(productList);
                    if(value != null){
                        Method setMethod = sorceProduct.getClass().getMethod("set"+name, new Class[] {Integer.class});
                        setMethod.invoke(sorceProduct,new Object[] {value});
                    }
                }else if(type.equals("class java.lang.Short")){

                }else if(type.equals("class java.lang.Double")){

                }else if(type.equals("class java.lang.Boolean")){

                }else if(type.equals("class java.util.Date")){

                }
            }
            //跟新对象
            productListJpa.saveAndFlush(sorceProduct);
        }

    }

    @Override
    public List<ProductListDTO> getProductList(Map<String,Object> map) {
        List<ProductListDTO> productLists = productListMapper.getProductList(map);
//        for (ProductListDTO productList : productLists) {
//            productList.setAddTimeStr(null);
//            productList.setUpdateTimeStr(null);
//        }
        return productLists;

    }
}
