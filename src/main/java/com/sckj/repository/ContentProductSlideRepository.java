package com.sckj.repository;
import com.sckj.model.ContentProductSlide;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
* 描述：商品滑动配置 Repository接口
* @author hww
* @date 2018/09/25
*/
public interface ContentProductSlideRepository extends JpaRepository<ContentProductSlide, String> {

    void deleteByIdIn(List<String> ids);

    List<ContentProductSlide> findByIdIn(List<String> ids);

}
