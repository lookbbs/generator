package ${basePackage}.web.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.honglu.game.common.utils.ReType;
import com.honglu.game.common.utils.result.ResultEntity;
import com.honglu.game.merchant.entity.CurrentUser;
import ${basePackage}.entity.${entityName};
import ${basePackage}.service.${entityName}Service;
import com.honglu.game.merchant.util.CommonUtil;
import com.honglu.game.merchant.util.ResultEntityUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * auto-code工具 自动生成
 */
 * @author auto-code
 * @date ${.now?string("yyyy-MM-dd")}
 */
@Controller
@RequestMapping(value = "/${variableName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ${entityName}Controller {

    @Autowired
    private ${entityName}Service ${variableName}Service;

    /**
     * 列表页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/index")
    @RequiresPermissions("${variableName}:page")
    public String index(Model model) {
        return "/${variableName}/${variableName}List";
    }

    /**
     * 分页查询
     *
     * @param record
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/list")
    @ResponseBody
    @RequiresPermissions(value = "${variableName}:page")
    public String list(${entityName} record,
                       @RequestParam("page") Integer page,
                       @RequestParam("limit") Integer size) {
        Pageable pageable = new PageRequest(page, size);
        PageInfo<${entityName}> pageInfo = ${variableName}Service.selectListByPageable(record, pageable);
        ReType reType = new ReType(pageInfo.getTotal(), pageInfo.getList());
        return JSON.toJSONString(reType);
    }

    /**
     * 新增页面
     *
     * @return
     */
    @RequiresPermissions(value = "${variableName}:add")
    @GetMapping(value = "/add")
    public String add(Model model) {
        return "/${variableName}/${variableName}Add";
    }
    
    
    /**
     * 查看页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions(value = "${variableName}:view")
    @GetMapping(value = "/{id:\\w+}")
    public String detail(@PathVariable("id") Long id, Model model) {
        ${entityName} record = ${variableName}Service.selectByPrimaryKey(id);
        model.addAttribute("record", record);
        return "/${variableName}/${variableName}Detail";
    }

    /**
     * 修改页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions(value = "${variableName}:edit")
    @GetMapping(value = "/edit/{id:\\w+}")
    public String edit(@PathVariable("id") ${primaryKey.javaType} id, Model model) {
        ${entityName} record = ${variableName}Service.selectByPrimaryKey(id);
        model.addAttribute("record", record);
        return "/${variableName}/${variableName}Edit";
    }


    /**
     * 保存数据
     *
     * @param record
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions(value = "${variableName}:save")
    public ResultEntity save(${entityName} record) {
        CurrentUser user = CommonUtil.getUser();
        if (null == record.getId()) {
            record.setCreateMan(user.getUsername());
            record.setCreateTime(new Date());
        }
        record.setModifyMan(user.getUsername());
        record.setModifyTime(new Date());
        return ResultEntityUtil.success(${variableName}Service.save(record));
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id:\\w+}")
    @ResponseBody
    @RequiresPermissions(value = "${variableName}:delete")
    public ResultEntity deleteById(@PathVariable("id") ${primaryKey.javaType} id) {
        CurrentUser user = CommonUtil.getUser();
        int delete = ${variableName}Service.delete(id, user.getUsername());
        return ResultEntityUtil.success(delete);
    }
}
