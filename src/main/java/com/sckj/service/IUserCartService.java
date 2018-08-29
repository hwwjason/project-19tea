package com.sckj.service;

import com.sckj.common.ResultData;
import com.sckj.dto.UserCartList;
import org.springframework.web.bind.annotation.RequestParam;

public interface IUserCartService {
    UserCartList addGoods(String productId, String userId ,  String cartType);
}
