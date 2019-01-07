package com.sea.upms.web;

import com.github.pagehelper.PageInfo;
import com.sea.common.vo.ResultBean;
import com.sea.upms.pojo.Role;
import com.sea.upms.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.java2d.d3d.D3DDrawImage;


@RestController
@RequestMapping("sys/role")
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;
    /**
     *
     * 响应分页查询
     * @param page 页数
     * @param rows 每页大小
     * @param sortBy 排序字段
     * @param desc 是否降序
     * @param key 搜索条件
     * @return
     */
    @RequestMapping("page")
    public ResultBean<PageInfo<Role>> queryUserByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key
    ){
        return new ResultBean<>(roleService.queryRoleByPage(page,rows,sortBy,desc,key));
    }

    @PostMapping
    public ResultBean<Boolean> addRole(Role role ){
        log.info("post add");
        log.info(role.toString());
        roleService.addRole(role);
        return new ResultBean<>(true);
    }

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultBean<Boolean> deleteRole( @PathVariable("id") Long id){
        roleService.deleteRole(id);
        return new ResultBean<>(true);
    }

    /**
     * 更新一条记录
     * @param role
     * @return
     */
    @PutMapping
    public ResultBean<Boolean> updateRole(Role role ){
        log.info("更新的记录为："+role.toString());
        log.info(role.getId().toString());
        roleService.updateRole(role);
        return new ResultBean<>(true);
    }

//    @RequestMapping("test")
//    public String getUser(){
//       User user =  userService.exist("登录名");
//      // log.info("user " + user.toString());
//        log.info("user:");
//       return null; //user.toString();
//    }


}
