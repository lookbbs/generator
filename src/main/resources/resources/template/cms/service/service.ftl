package ${packageConfig.service};

import com.github.pagehelper.PageInfo;
import ${packageConfig.model}.${targetEntityClassName};
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * ${tableComment!}接口
 * @author auto-code
 * @date ${.now?string("yyyy-MM-dd")}
 */
public interface ${targetEntityClassName}Service extends BaseService<${targetEntityClassName},${data.primaryKeys[0].javaType}>{

    /**
     * 保存数据
     * @param record ${targetEntityClassName} ${tableComment!}数据
     * @return
     */
	int save(${targetEntityClassName} record);

    /**
     * 删除数据
     *
     * @param id ${tableComment!}ID
     * @param username 操作者用户名
     * @return
     */
    int delete(${data.primaryKeys[0].javaType} id,String username);

    List<${targetEntityClassName}> selectList(${targetEntityClassName} record);

    PageInfo<${targetEntityClassName}> selectListByPageable(${targetEntityClassName} condition, Pageable pageable);
}