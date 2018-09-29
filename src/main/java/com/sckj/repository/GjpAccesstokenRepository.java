package com.sckj.repository;
import com.sckj.model.GjpAccesstoken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
* 描述：管家婆token Repository接口
* @author hww
* @date 2018/09/28
*/
public interface GjpAccesstokenRepository extends JpaRepository<GjpAccesstoken, String> {

    void deleteByIdIn(List<String> ids);

    List<GjpAccesstoken> findByIdIn(List<String> ids);

}
