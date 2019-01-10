package ${basePackage}.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${basePackage}.dao.BaseMapper;
import ${basePackage}.dao.${entityName}Mapper;
import ${basePackage}.entity.${entityName};
import ${basePackage}.service.${entityName}Service;
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
public class ${entityName}ServiceImpl extends BaseServiceImpl<${entityName}, ${primaryKey.javaType}> implements ${entityName}Service {

    @Autowired
    private ${entityName}Mapper ${variableName}Mapper;

    @Override
    public BaseMapper<${entityName}, ${primaryKey.javaType}> getMappser() {
        return ${variableName}Mapper;
    }

    @Override
    public int save(${entityName} record) {
        if (null == record.getId()) {
            return ${variableName}Mapper.insert(record);
        } else {
            return ${variableName}Mapper.updateByPrimaryKeySelective(record);
        }
    }

    @Override
    public int delete(${primaryKey.javaType} id, String username) {
        return ${variableName}Mapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<${entityName}> selectList(${entityName} record) {
        return ${variableName}Mapper.selectList(record);
    }

    @Override
    public PageInfo<${entityName}> selectListByPageable(${entityName} condition, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<${entityName}> lst = selectList(condition);
        return new PageInfo<>(lst);
    }
}