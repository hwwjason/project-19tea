package com.sckj.repository;
import com.sckj.model.ContentForm;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
* 描述：组件内容配置 Repository接口
* @author hww
* @date 2018/10/03
*/
public interface ContentFormRepository extends JpaRepository<ContentForm, String> {

    void deleteByIdIn(List<String> ids);

    List<ContentForm> findByIdIn(List<String> ids);

    List<ContentForm> findByParentid(String parentid);

    List<ContentForm> findByParentidAndFormType(String parentid,String formType);

    List<ContentForm> findByParentidIn(List<String> parentids);

    List<ContentForm> findByParentidInAndFormType(List<String> parentids,String formType);

    List<ContentForm> findByContentid(String contentid);

    List<ContentForm> findByContentidAndFormType(String contentid,String formType);

}
