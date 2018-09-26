package com.sckj.repository;
import com.sckj.model.ContentLine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
* 描述：分割线配置 Repository接口
* @author hww
* @date 2018/09/25
*/
public interface ContentLineRepository extends JpaRepository<ContentLine, String> {

    void deleteByIdIn(List<String> ids);

    List<ContentLine> findByIdIn(List<String> ids);

    List<ContentLine> findByContentid(String contentid);
}
