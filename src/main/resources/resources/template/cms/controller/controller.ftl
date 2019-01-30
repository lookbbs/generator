package ${packageConfig.controller};

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.honglu.game.common.utils.ReType;
import com.honglu.game.common.utils.result.ResultEntity;
import com.honglu.game.merchant.entity.CurrentUser;
import ${targetEntityPackage}.${targetEntityClassName};
import ${packageConfig.service}.${targetEntityClassName}Service;
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
 * ${data.tableComment}控制器
 * @author auto-code
 * @date ${.now?string("yyyy-MM-dd")}
 */
@Controller
@RequestMapping(value = "/${targetEntityVariableName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ${targetEntityClassName}Controller {

    @Autowired
    private ${targetEntityClassName}Service ${targetEntityVariableName}Service;

    /**
     * 列表页面
     *
     * @param model
     * @return
     */
    @GetMapping
    @RequiresPermissions("${targetEntityVariableName}:page")
    public String index(Model model) {
        return "/${targetEntityVariableName}/${targetEntityVariableName}List";
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
    @RequiresPermissions(value = "${targetEntityVariableName}:page")
    public String list(${targetEntityClassName} record,
                       @RequestParam("page") Integer page,
                       @RequestParam("limit") Integer size) {
        Pageable pageable = new PageRequest(page, size);
        PageInfo<${targetEntityClassName}> pageInfo = ${targetEntityVariableName}Service.selectListByPageable(record, pageable);
        ReType reType = new ReType(pageInfo.getTotal(), pageInfo.getList());
        return JSON.toJSONString(reType);
    }

    /**
     * 新增页面
     *
     * @return
     */
    @RequiresPermissions(value = "${targetEntityVariableName}:add")
    @GetMapping(value = "/add")
    public String add(Model model) {
        return "/${targetEntityVariableName}/${targetEntityVariableName}Add";
    }
    
    
    /**
     * 查看页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions(value = "${targetEntityVariableName}:view")
    @GetMapping(value = "/{id:\\w+}")
    public String detail(@PathVariable("id") ${data.primaryKeys[0].javaType} id, Model model) {
        ${targetEntityClassName} record = ${targetEntityVariableName}Service.selectByPrimaryKey(id);
        model.addAttribute("record", record);
        return "/${targetEntityVariableName}/${targetEntityVariableName}Detail";
    }

    /**
     * 修改页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions(value = "${targetEntityVariableName}:edit")
    @GetMapping(value = "/edit/{id:\\w+}")
    public String edit(@PathVariable("id") ${data.primaryKeys[0].javaType} id, Model model) {
        ${targetEntityClassName} record = ${targetEntityVariableName}Service.selectByPrimaryKey(id);
        model.addAttribute("record", record);
        return "/${targetEntityVariableName}/${targetEntityVariableName}Edit";
    }


    /**
     * 保存数据
     *
     * @param record
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions(value = "${targetEntityVariableName}:save")
    public ResultEntity save(${targetEntityClassName} record) {
        CurrentUser user = CommonUtil.getUser();
        if (null == record.getId()) {
            record.setCreateMan(user.getUsername());
            record.setCreateTime(new Date());
        }
        record.setModifyMan(user.getUsername());
        record.setModifyTime(new Date());
        return ResultEntityUtil.success(${targetEntityVariableName}Service.save(record));
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id:\\w+}")
    @ResponseBody
    @RequiresPermissions(value = "${targetEntityVariableName}:delete")
    public ResultEntity deleteById(@PathVariable("id") ${data.primaryKeys[0].javaType} id) {
        CurrentUser user = CommonUtil.getUser();
        int delete = ${targetEntityVariableName}Service.delete(id, user.getUsername());
        return ResultEntityUtil.success(delete);
    }
}
