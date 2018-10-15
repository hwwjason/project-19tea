package com.sckj.bak.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sckj.common.Query;
import com.sckj.common.ResultData;
import com.sckj.constant.MessageConstants;
import com.sckj.controller.ContentController;
import com.sckj.enums.ResultStatusEnum;
import com.sckj.exception.BusinessException;
import com.sckj.model.ProductList;
import com.sckj.model.dto.ProductListDTO;
import com.sckj.service.IProductService;
import com.sckj.utils.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * 商品控制器
 */
@RestController
@RequestMapping("/bak/product")
public class BakProductController {

    private static final Logger logger = LoggerFactory.getLogger(ContentController.class);

    @Autowired
    private IProductService productService;

    @RequestMapping(value = "/testUpdateProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultData updateProduct() throws Exception{
        ResultData resultData = new ResultData();
        for (int i=0;i<99;i++){
            ProductList productList = new ProductList();
            productList.setId(UUIDUtils.generate());
            productService.putProductToStorage(productList);
        }
        return resultData;
    }

    @RequestMapping(value = "/putProductToStorage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultData putProductToStorage(HttpServletRequest request) throws Exception {
        ResultData resultData = new ResultData();
        productService.putProductToStorage(request);
        return resultData;
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultData deleteProduct(@RequestParam String[] ids){
        ResultData resultData = new ResultData();
        productService.deleteProduct(ids);
        return resultData;
    }

    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultData updateProduct(@RequestBody ProductList product) throws Exception{
        ResultData resultData = new ResultData();
        productService.updateProduct(product);
        return resultData;
    }

    @RequestMapping(value = "/updateProducts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultData updateProducts(@RequestBody List<ProductList> products) throws Exception{
        ResultData resultData = new ResultData();
        productService.updateProducts(products);
        return resultData;
    }


    @RequestMapping(value = "/getProductList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData getProductList(@RequestBody Query query){
        logger.info("查询商品列表begin===》》》");
        ResultData resultData = new ResultData();
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        Map<String,Object> map = (Map<String, Object>) query.getCondition();
        List<ProductListDTO> sckjUserList = productService.getProductList(map);
        PageInfo<ProductListDTO> pageInfo = new PageInfo<ProductListDTO>(sckjUserList);
        resultData.setData(pageInfo);
        logger.info("查询商品列表end===《《《");
        return resultData;
    }

    @RequestMapping(value = "/getProductById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData getProductById(@RequestParam("id") String id){
        logger.info("通过ID查询商品详情begin===》》》");
        ResultData resultData = new ResultData();
        ProductListDTO productList = productService.getProductDTOById(id);
        resultData.setData(productList);
        logger.info("通过ID查询商品详情end===《《《");
        return resultData;
    }

    @RequestMapping(value = "/getProductByCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData getProductByCode(@RequestParam("code") String code){
        try {
            logger.info("匹配商品");
            ResultData resultData = new ResultData();
            ProductList productList = productService.getProductByCode(code);
            if(productList == null){
                resultData.setStatus(ResultStatusEnum.FAIL.toString());
                resultData.setMessage("商品不存在");
            }
            resultData.setData(productList);
            return resultData;
        }catch (BusinessException e){
            logger.error("匹配商品出错/n", e);
            throw e;
        } catch (Exception e){
            logger.error("匹配商品出错/n", e);
            return new ResultData(null, ResultStatusEnum.FAIL.toString(), MessageConstants.SERVERS_BUSINESS);
        }
    }

    @RequestMapping(value = "/synchStock", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultData synchStock(@RequestParam List<String> codes) throws Exception{
        ResultData resultData = new ResultData();
        List<ProductListDTO>  productListDTOS = productService.synchStock(codes);
        resultData.setData(productListDTOS);
        return resultData;
    }

}
