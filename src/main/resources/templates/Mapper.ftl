<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${package_name}.repository.mybatis.${table_name}DAO">

    <resultMap id="${table_name}ResultMap" type="${package_name}.model.${table_name}">
    <#if model_column?exists>
    <#list model_column as model>
    <#if (model.columnType = 'varchar' || model.columnType = 'VARCHAR'|| model.columnType = 'CHAR' )>
        <result column="${model.columnName}" jdbcType="VARCHAR" property="${model.changeColumnName?uncap_first}" />
    </#if>
    <#if ( model.columnType = 'CHAR' )>
        <result column="${model.columnName}" jdbcType="CHAR" property="${model.changeColumnName?uncap_first}" />
    </#if>
    <#if (model.columnType = 'TEXT' || model.columnType = 'text')>
        <result column="${model.columnName}" jdbcType="LONGVARCHAR" property="${model.changeColumnName?uncap_first}" />
    </#if>
    <#if model.columnType = 'timestamp' || model.columnType = 'DATETIME' >
        <result column="${model.columnName}" jdbcType="TIMESTAMP" property="${model.changeColumnName?uncap_first}" />
    </#if>
    <#if model.columnType = 'INT' >
        <result column="${model.columnName}" jdbcType="INTEGER" property="${model.changeColumnName?uncap_first}" />
    </#if>
    <#if model.columnType = 'numeric' || model.columnType = 'DECIMAL' >
        <result column="${model.columnName}" jdbcType="DECIMAL" property="${model.changeColumnName?uncap_first}" />
    </#if>
    </#list>
    </#if>
    </resultMap>

    <resultMap id="${table_name}DTOResultMap" extends="${table_name}ResultMap" type="${package_name}.model.dto.${table_name}DTO"></resultMap>

    <sql id="findDtoSql">
        select * from (
        select * from  ${table_name_small} temp
        ) t
    </sql>

    <select id="findDTOById" parameterType="String" resultMap="${table_name}DTOResultMap">
        <include refid="findDtoSql"></include>
        <where>
            and t.id = ${r'#{id}'}
        </where>
    </select>

    <select id="findById" parameterType="String" resultMap="${table_name}ResultMap">
        <include refid="findDtoSql"></include>
        <where>
            and t.id = ${r'#{id}'}
        </where>
    </select>

    <select id="find${table_name}Page" parameterType="${package_name}.model.dto.${table_name}DTO" resultMap="${table_name}DTOResultMap">
        <include refid="findDtoSql" />
        <where>

        </where>
    </select>

    <select id="get${table_name}List" parameterType="java.util.Map" resultMap="${table_name}DTOResultMap">
        select * from ${table_name_small} as temp
        <where>

        </where>
        ORDER BY temp.create_time DESC
    </select>

</mapper>