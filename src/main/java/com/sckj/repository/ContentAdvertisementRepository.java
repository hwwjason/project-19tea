package com.sckj.repository;
import com.sckj.model.ContentAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
* 描述：广告长图配置 Repository接口
* @author hww
* @date 2018/09/25
*/
public interface ContentAdvertisementRepository extends JpaRepository<ContentAdvertisement, String> {

    void deleteByIdIn(List<String> ids);

    List<ContentAdvertisement> findByIdIn(List<String> ids);

}
