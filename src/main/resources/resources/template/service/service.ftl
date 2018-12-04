package ${basePackage}.service;

import com.github.pagehelper.PageInfo;
import ${basePackage}.entity.${entityName};
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * ${tableComment!}接口
 * @author auto-code
 * @date ${.now?string("yyyy-MM-dd")}
 */
public interface ${entityName}Service extends BaseService<${entityName},${primaryKey.javaType}>{

    /**
     * 保存数据
     * @param record ${entityName} ${tableComment!}数据
     * @return
     */
	int save(${entityName} record);

    /**
     * 删除数据
     *
     * @param id ${tableComment!}ID
     * @param username 操作者用户名
     * @return
     */
    int delete(Long id,String username);

    List<${entityName}> selectList(${entityName} record);

    PageInfo<${entityName}> selectListByPageable(${entityName} condition, Pageable pageable);
}