package com.sckj.repository;
import com.sckj.model.ContentBanner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
* 描述：Banner配置 Repository接口
* @author hww
* @date 2018/09/25
*/
public interface ContentBannerRepository extends JpaRepository<ContentBanner, String> {

    void deleteByIdIn(List<String> ids);

    List<ContentBanner> findByIdIn(List<String> ids);

}
