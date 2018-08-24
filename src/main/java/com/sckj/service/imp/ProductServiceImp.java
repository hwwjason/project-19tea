package com.sckj.service.imp;

import com.sckj.dao.ProductListMapper;
import com.sckj.dto.ProductListDTO;
import com.sckj.jpa.ProductListJpa;
import com.sckj.pojo.ProductList;
import com.sckj.pojo.ProductListWithBLOBs;
import com.sckj.service.IProductService;
import com.sckj.service.IShoppingService;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.StringUtils;
import com.sckj.utils.UUIDUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImp implements IProductService{

    @Autowired
    private ProductListJpa productListJpa;

    @Autowired
    private ProductListMapper productListMapper;

    @Override
    public void putProductToStorage(ProductListWithBLOBs productList) {
        productList.setId(UUIDUtils.generate());
//        productList.setAddtime(DateTimeUtils.getCurrentDate());
//        productList.setUpdatetime(DateTimeUtils.getCurrentDate());
        productListJpa.saveAndFlush(productList);
    }

    @Override
    public void deleteProduct(String[] ids) {
//        for (String id : productIds) {
//            productListJpa.deleteById(id);
//        }
        productListMapper.deleteProductByIds(ids);
    }

    @Override
    public void updateProduct(ProductListWithBLOBs productList) throws Exception {
        if(productList.getId()!=null){
            ProductListWithBLOBs  sorceProduct = productListJpa.getOne(productList.getId());
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
