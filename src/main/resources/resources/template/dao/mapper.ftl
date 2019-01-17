package ${basePackage}.dao;

import ${packageConfig.model}.${targetEntityClassName};
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ${tableComment!}
 * @author auto-code
 * @date ${.now?string("yyyy-MM-dd")}
 */
@Mapper
public interface ${targetEntityClassName}Mapper extends BaseMapper<${targetEntityClassName},${data.primaryKeys[0].javaType}> {

    List<${targetEntityClassName}> selectList(${targetEntityClassName} record);
}