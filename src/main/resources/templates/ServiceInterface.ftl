package ${package_name}.service;
import ${package_name}.model.${table_name};
import ${package_name}.model.dto.${table_name}DTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
* 描述：${table_annotation} 服务实现层接口
* @author ${author}
* @date ${date}
*/
public interface I${table_name}Service  {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    ${table_name}DTO findDTOById(String id)throws Exception;

    ${table_name} findById(String id)throws Exception;

    ${table_name} saveAndFlush(${table_name} ${table_name?uncap_first})throws Exception;

    void deleteById(String id)throws Exception;

    ${table_name}DTO createOrUpdate${table_name}(${table_name}DTO ${table_name?uncap_first}DTO) throws Exception;

    <#--${table_name}DTO update${table_name}(${table_name}DTO ${table_name?uncap_first}DTO) throws Exception;-->

    Page<${table_name}DTO> find${table_name}Page(${table_name}DTO ${table_name?uncap_first}DTO, Pageable page) throws Exception;

    void deleteByIds(List<String> ids)throws Exception;

    List<${table_name}DTO> get${table_name}List(Map<String,Object> ${table_name?uncap_first}ListMap);
}
