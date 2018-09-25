package com.sckj.repository;
import com.sckj.model.ContentProductColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
* 描述：商品通栏配置 Repository接口
* @author hww
* @date 2018/09/25
*/
public interface ContentProductColumnRepository extends JpaRepository<ContentProductColumn, String> {

    void deleteByIdIn(List<String> ids);

    List<ContentProductColumn> findByIdIn(List<String> ids);

}
