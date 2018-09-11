package ${package_name}.service.impl;
import ${package_name}.model.${table_name};
import ${package_name}.repository.${table_name}Repository;
import ${package_name}.service.I${table_name}Service;
import ${package_name}.repository.mybatis.${table_name}DAO;
import com.sckj.utils.DateTimeUtils;
import com.sckj.utils.UUIDUtils;
import com.sckj.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${package_name}.model.dto.${table_name}DTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.transaction.Transactional;
import java.util.List;

/**
* 描述：${table_annotation} 服务实现层
* @author ${author}
* @date ${date}
*/
@Service
public class ${table_name}ServiceImpl implements I${table_name}Service {

    @Autowired
    private ${table_name}DAO ${table_name?uncap_first}DAO;

    @Autowired
    private ${table_name}Repository ${table_name?uncap_first}Repository;

    @Override
    public ${table_name}DTO findDTOById(String id) throws Exception {
        ${table_name}DTO ${table_name?uncap_first}DTO = ${table_name?uncap_first}DAO.findDTOById(id);
        return ${table_name?uncap_first}DTO;
    }

    @Override
    public void deleteById(String id) throws Exception {
        ${table_name?uncap_first}Repository.deleteById(id);
    }

    @Override
    public ${table_name}DTO create${table_name}(${table_name}DTO ${table_name?uncap_first}DTO) throws Exception {
        ${table_name} ${table_name?uncap_first} = new ${table_name}();
        BeanUtils.copyProperties(${table_name?uncap_first},${table_name?uncap_first}DTO);
        //${table_name?uncap_first}.setStatus(StatusEnum.ENABLE.toString());
        ${table_name?uncap_first}.setCreatetime(DateTimeUtils.getCurrentDate());
        ${table_name?uncap_first}.setId(UUIDUtils.generate());
        ${table_name?uncap_first} = ${table_name?uncap_first}Repository.saveAndFlush(${table_name?uncap_first});
        return this.findDTOById(${table_name?uncap_first}.getId());
    }

    @Override
    public ${table_name}DTO update${table_name}(${table_name}DTO ${table_name?uncap_first}DTO)throws Exception {
        ${table_name} ${table_name?uncap_first} = new ${table_name}();
        BeanUtils.copyPropertiesWithoutNull(${table_name?uncap_first},${table_name?uncap_first}DTO);
        ${table_name?uncap_first} = ${table_name?uncap_first}Repository.saveAndFlush(${table_name?uncap_first});
        return this.findDTOById(${table_name?uncap_first}.getId());
    }

    /**
    * 描述：查询列表(分页)
    * @param ${table_name?uncap_first}DTO 实体DTO
    * @param page  分页参数
    */
    @Override
    public Page<${table_name}DTO> find${table_name}Page(${table_name}DTO ${table_name?uncap_first}DTO, Pageable page) throws Exception{
        return ${table_name?uncap_first}DAO.find${table_name}Page(${table_name?uncap_first}DTO,page);
    }

    @Override
    @Transactional
    public void deleteByIds(List<String> ids) throws Exception {
        ${table_name?uncap_first}Repository.deleteByIdIn(ids);
    }

    @Override
    public List<${table_name}DTO> get${table_name}List(Map<String,Object> map) {
        List<${table_name}DTO> lists = ${table_name?uncap_first}DAO.get${table_name}List(map);
        return lists;
    }

}



