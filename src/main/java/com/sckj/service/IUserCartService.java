package com.sckj.service;

import com.sckj.model.dto.UserCartDTO;
import com.sckj.model.dto.UserCartList;

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
     * @param ids
     * @param userId
     * @param cartType
     * @return
     */
    UserCartList removeGoods(List<String> ids, String userId ,  String cartType);

    public void deleteByIds( List<String> ids);

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

    /**
     * 修改购物车列表的状态，比如是否选中
     * @param userCartDTO
     * @return
     */
    UserCartList updateProduct(  List<UserCartDTO> userCartDTO);

}
