package com.sckj.service;

import com.sckj.common.ResultData;
import com.sckj.dto.UserCartDTO;
import com.sckj.dto.UserCartList;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IUserCartService {

    /**
     * 添加或减少商品
     * @param productId
     * @param userId
     * @param cartType
     * @param count
     * @return
     */
    UserCartList changeGoods(String productId, String userId ,  String cartType,int count);

    /**
     * 删除商品
     * @param productId
     * @param userId
     * @param cartType
     * @return
     */
    UserCartList removeGoods(String productId, String userId ,  String cartType);

    /**
     * 获取购物车列表
     * @param userId
     * @param cartType
     * @return
     */
    UserCartList getUserCart(String userId ,  String cartType);

    /**
     * 清空已经下载的购物车列表
     * @param userId
     * @param cartType
     * @return
     */
    UserCartList emptyOutGoods(String userId ,  String cartType);

    UserCartList updateProduct(  List<UserCartDTO> userCartDTO);

}
