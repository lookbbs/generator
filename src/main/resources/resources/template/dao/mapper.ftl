package ${basePackage}.dao;

import ${basePackage}.entity.${entityName};
import ${basePackage}.entity.${entityName}Example;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * ${tableComment!}
 * @author auto-code
 * @date ${.now?string("yyyy-MM-dd")}
 */
@Mapper
public interface ${entityName}Mapper extends BaseMapper<${entityName},${primaryKey.javaType}> {

    List<${entityName}> selectList(${entityName} record);
}