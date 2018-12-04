<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.dao.${entityName}Mapper">

    <sql id="Where_Clause">
        <where>
            <#if columns??>
                <#list columns as col>
                  <#if col.javaType=='String'>
            <if test="${col.fieldName} != null and ${col.fieldName} != ''">
                and INSTR(${col.columnName},${r"#{"}${col.fieldName},jdbcType=VARCHAR}) > 0
            </if>
                  <#elseif col.javaType == 'Date'>
            <if test="${col.fieldName} != null">
                and to_days(${col.columnName}) = to_days(${r"#{"}${col.fieldName},jdbcType=TIMESTAMP})
            </if>
                  <#elseif col.javaType == 'Integer'>
            <if test="${col.fieldName} != null">
                and ${col.columnName} = ${r"#{"}${col.fieldName},jdbcType=INTEGER}
            </if>
                  <#else>
            <if test="${col.columnName} != null">
                and ${col.columnName} = ${r"#{"}${col.columnName}}
            </if>
                  </#if>
                </#list>
            </#if>
        </where>
    </sql>

    <select id="selectList" parameterType="${basePackage}.entity.${entityName}" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ${tableName}
        <include refid="Where_Clause"/>
        order by id desc
    </select>
</mapper>