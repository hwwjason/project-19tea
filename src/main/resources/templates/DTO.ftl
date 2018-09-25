package ${package_name}.model.dto;

import ${package_name}.model.${table_name};

import java.util.List;

/**
* 描述：${table_annotation}DTO
* @author ${author}
* @date ${date}
*/
public class ${table_name}DTO extends ${table_name}{
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
