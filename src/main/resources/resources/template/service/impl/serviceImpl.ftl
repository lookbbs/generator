package ${packageConfig.service}.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${basePackage}.dao.BaseMapper;
import ${packageConfig.client}.${targetEntityClassName}Mapper;
import ${packageConfig.model}.${targetEntityClassName};
import ${packageConfig.service}.${targetEntityClassName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author auto-code
 * @date ${.now?string("yyyy-MM-dd")}
 */
@Service
public class ${targetEntityClassName}ServiceImpl extends BaseServiceImpl<${targetEntityClassName}, ${data.primaryKeys[0].javaType}> implements ${targetEntityClassName}Service {

    @Autowired
    private ${targetEntityClassName}Mapper ${targetEntityVariableName}Mapper;

    @Override
    public BaseMapper<${targetEntityClassName}, ${data.primaryKeys[0].javaType}> getMappser() {
        return ${targetEntityVariableName}Mapper;
    }

    @Override
    public int save(${targetEntityClassName} record) {
        if (null == record.getId()) {
            return ${targetEntityVariableName}Mapper.insert(record);
        } else {
            return ${targetEntityVariableName}Mapper.updateByPrimaryKeySelective(record);
        }
    }

    @Override
    public int delete(${data.primaryKeys[0].javaType} id, String username) {
        return ${targetEntityVariableName}Mapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<${targetEntityClassName}> selectList(${targetEntityClassName} record) {
        return ${targetEntityVariableName}Mapper.selectList(record);
    }

    @Override
    public PageInfo<${targetEntityClassName}> selectListByPageable(${targetEntityClassName} condition, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<${targetEntityClassName}> lst = selectList(condition);
        return new PageInfo<>(lst);
    }
}