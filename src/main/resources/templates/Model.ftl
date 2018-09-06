package ${package_name}.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.persistence.*;
import java.util.Date;
import java.math.BigDecimal;

/**
* 描述：${table_annotation}模型
* @author ${author}
* @date ${date}
*/
@Entity
@Table(name="${table_name_small}")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class ${table_name} {

    <#if model_column?exists>
        <#list model_column as model>
    /**
    *${model.columnComment!}
    */
    <#if (model.columnType = 'varchar' || model.columnType = 'TEXT'|| model.columnType = 'VARCHAR'|| model.columnType = 'CHAR' )>
    @Column(name = "${model.columnName}",columnDefinition = "VARCHAR")
    private String ${model.changeColumnName?uncap_first};

    </#if>
    <#if model.columnType = 'timestamp' || model.columnType = 'DATETIME' >
    @Column(name = "${model.columnName}",columnDefinition = "TIMESTAMP")
    private Date ${model.changeColumnName?uncap_first};

    </#if>

    <#if model.columnType = 'INT' >
    @Column(name = "${model.columnName}",columnDefinition = "INTEGER")
    private int ${model.changeColumnName?uncap_first};

    </#if>

    <#if model.columnType = 'numeric' || model.columnType = 'DECIMAL' >
    @Column(name = "${model.columnName}",columnDefinition = "DECIMAL")
    private BigDecimal ${model.changeColumnName?uncap_first};

    </#if>
        </#list>
    </#if>

<#if model_column?exists>
<#list model_column as model>
<#if (model.columnType = 'varchar' || model.columnType = 'text' || model.columnType = 'VARCHAR'|| model.columnType = 'CHAR')>
    public String get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(String ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }

</#if>
<#if model.columnType = 'timestamp' || model.columnType = 'DATETIME' >
    public Date get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(Date ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }

</#if>

    <#if model.columnType = 'numeric' || model.columnType = 'DECIMAL' >
    public BigDecimal get${model.changeColumnName}() {
    return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(BigDecimal ${model.changeColumnName?uncap_first}) {
    this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }

    </#if>

    <#if model.columnType = 'INT'>
    public int get${model.changeColumnName}() {
    return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(int ${model.changeColumnName?uncap_first}) {
    this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }

    </#if>
</#list>
</#if>

}
