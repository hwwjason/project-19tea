package ${package_name}.repository.mybatis;

import ${package_name}.model.${table_name};
import ${package_name}.model.dto.${table_name}DTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
/**
 * 描述：${table_annotation}DAO 层
 * @author ${author}
 * @date ${date}
 */
public interface ${table_name}DAO {

    ${table_name}DTO findDTOById(@Param("id")String id);

    ${table_name} findById(@Param("id")String id);

    /**
    * 描述：查询${table_annotation}列表以及高级搜索(分页)
    * @param page  分页参数
    * @param ${table_name?uncap_first}DTO  ${table_annotation}DTO
    */
    Page<${table_name}DTO> find${table_name}Page(${table_name}DTO ${table_name?uncap_first}DTO, Pageable page);

    List<${table_name}DTO> get${table_name}List(Map<String,Object> map);

}
