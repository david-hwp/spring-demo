package com.hwp.project.modules.sys.controller;

import com.hwp.project.common.utils.Constant;
import com.hwp.project.common.utils.R;
import com.hwp.project.modules.sys.entity.SysDeptEntity;
import com.hwp.project.modules.sys.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


/**
 * 部门管理
 *
 * @author david-hwp
 * @email wolaiwod@gmail.com
 * @date 2017-06-20 15:23:47
 */
@RestController
@RequestMapping("/sys/dept")
@Api(tags = "部门管理")
public class SysDeptController extends AbstractController {
    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:dept:list")
    @ApiOperation(value = "部门列表", response = List.class)
    public List<SysDeptEntity> list() {
        List<SysDeptEntity> deptList = sysDeptService.queryList(new HashMap<String, Object>());

        return deptList;
    }

    /**
     * 选择部门(添加、修改菜单)
     */
    @GetMapping("/select")
    @RequiresPermissions("sys:dept:select")
    @ApiOperation(value = "选择部门(添加、修改菜单)", response = R.class)
    public R select() {
        List<SysDeptEntity> deptList = sysDeptService.queryList(new HashMap<String, Object>());

        //添加一级部门
        if (getUserId() == Constant.SUPER_ADMIN) {
            SysDeptEntity root = new SysDeptEntity();
            root.setDeptId(0L);
            root.setName("一级部门");
            root.setParentId(-1L);
            root.setOpen(true);
            deptList.add(root);
        }

        return R.ok().put("deptList", deptList);
    }

    /**
     * 上级部门Id(管理员则为0)
     */
    @GetMapping("/info")
    @RequiresPermissions("sys:dept:list")
    @ApiOperation(value = "上级部门Id(管理员则为0)", response = R.class)
    public R info() {
        long deptId = 0;
        if (getUserId() != Constant.SUPER_ADMIN) {
            List<SysDeptEntity> deptList = sysDeptService.queryList(new HashMap<String, Object>());
            Long parentId = null;
            for (SysDeptEntity sysDeptEntity : deptList) {
                if (parentId == null) {
                    parentId = sysDeptEntity.getParentId();
                    continue;
                }

                if (parentId > sysDeptEntity.getParentId().longValue()) {
                    parentId = sysDeptEntity.getParentId();
                }
            }
            deptId = parentId;
        }

        return R.ok().put("deptId", deptId);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{deptId}")
    @RequiresPermissions("sys:dept:info")
    @ApiOperation(value = "根据Id获得部门信息", response = R.class)
    public R info(@PathVariable("deptId") Long deptId) {
        SysDeptEntity dept = sysDeptService.selectById(deptId);

        return R.ok().put("dept", dept);
    }

    /**
     * 保存部门
     */
    @PostMapping("/save")
    @RequiresPermissions("sys:dept:save")
    @ApiOperation("保存部门")
    public R save(@RequestBody SysDeptEntity dept) {
        sysDeptService.insert(dept);

        return R.ok();
    }

    /**
     * 修改部门
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:dept:update")
    @ApiOperation("修改部门")
    public R update(@RequestBody SysDeptEntity dept) {
        sysDeptService.updateById(dept);

        return R.ok();
    }

    /**
     * 删除部门
     */
    @PostMapping("/delete")
    @RequiresPermissions("sys:dept:delete")
    @ApiOperation("删除部门")
    public R delete(long deptId) {
        //判断是否有子部门
        List<Long> deptList = sysDeptService.queryDetpIdList(deptId);
        if (deptList.size() > 0) {
            return R.error("请先删除子部门");
        }

        sysDeptService.deleteById(deptId);

        return R.ok();
    }

}
