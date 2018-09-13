package ${package_name}.repository;
import ${package_name}.model.${table_name};
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
* 描述：${table_annotation} Repository接口
* @author ${author}
* @date ${date}
*/
public interface ${table_name}Repository extends JpaRepository<${table_name}, String> {

    void deleteByIdIn(List<String> ids);

    List<${table_name}> findByIdIn(List<String> ids);

}
