package com.sckj.repository;
import com.sckj.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
* 描述：内容管理 Repository接口
* @author hww
* @date 2018/09/25
*/
public interface ContentRepository extends JpaRepository<Content, String> {

    void deleteByIdIn(List<String> ids);

    List<Content> findByIdIn(List<String> ids);

}
