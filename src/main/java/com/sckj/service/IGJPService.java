package com.sckj.service;

import com.sckj.model.ProductList;
import com.sckj.model.ProductOrder;
import com.sckj.model.ProductSonOrder;
import com.sckj.model.UserList;
import com.sckj.model.dto.ProductListDTO;
import net.sf.json.JSONObject;

import java.util.List;

public interface IGJPService {
    /**
     * ERP商品基本资料获取，可通过商品id进行筛选条件。
     * @return
     * @throws Exception
     */
    String getProductinfo()  throws Exception;

    /**
     * ERP商品基本资料获取，可通过商品id进行筛选条件。
     * @param code
     * @return
     * @throws Exception
     */
    String getProductinfoByCode(String code) throws Exception;


    /**
     * 获取ERP商品基本资料的库存信息
     * @param code
     * @return
     * @throws Exception
     */
    String querysaleqty(String code) throws Exception;

    /**
     * 批量查询商品基本资料的库存信息
     * @param code
     * @return
     * @throws Exception
     */
    String batchquerysaleqty(List<String> code) throws Exception;


    /**
     * 上载商品信息
     * @param uploadproducts
     * @return
     * @throws Exception
     */
    String uploadproducts(String uploadproducts) throws Exception;

    String uploadproducts(ProductList product) throws Exception;

    /**
     * 系统仓库
     * @return
     * @throws Exception
     */
    List<Object> queryktypelist() throws Exception;

    /**
     * 获取系统仓库id
     * @return
     * @throws Exception
     */
    List<Long> getKtypeids() throws Exception;

    /**
     * 查询交易列表信息
     * @return
     * @throws Exception
     */
    List<Object> querysaleorder() throws Exception;


    /**
     * 订单批量上载/更新接口（批量）
     * @return
     * @throws Exception
     */
    String uploadsaleorders(ProductOrder productOrder, List<ProductSonOrder> productSonOrders, UserList userList) throws Exception;

    /**
     * 上载交易
     * @return
     * @throws Exception
     */
    List<Object> addsaleorder() throws Exception;
}
