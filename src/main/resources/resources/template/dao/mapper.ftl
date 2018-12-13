package ${basePackage}.dao;

import ${basePackage}.entity.${entityName};
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ${tableComment!}
 * @author auto-code
 * @date ${.now?string("yyyy-MM-dd")}
 */
@Mapper
public interface ${entityName}Mapper extends BaseMapper<${entityName},${primaryKey.javaType}> {

    List<${entityName}> selectList(${entityName} record);
}