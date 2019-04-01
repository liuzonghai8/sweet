package com.sea.upms.web;

import com.github.pagehelper.PageInfo;
import com.sea.common.vo.PageResult;
import com.sea.common.vo.ResultDTO;
import com.sea.upms.pojo.Role;
import com.sea.upms.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public ResultDTO<PageResult<Role>> queryRoleByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key
    ){
        return new ResultDTO<>(roleService.queryRoleByPage(page,rows,sortBy,desc,key));
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @PostMapping
    public ResultDTO<Boolean> addRole(Role role ){
        return new ResultDTO<>(roleService.addRole(role));
    }

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultDTO<Boolean> deleteRole(@PathVariable("id") Long id){

        return new ResultDTO<Boolean>( roleService.deleteRole(id));
    }

    /**
     * 更新一条记录
     * @param role
     * @return
     */
    @PutMapping
    public ResultDTO<Boolean> updateRole(Role role ){
        return new ResultDTO<>(roleService.updateRole(role));
    }

@GetMapping("/all")
    public ResponseEntity<List<Role>> queryRole(){
        return ResponseEntity.ok(roleService.queryRole());
}

    /**
     * 通过用户Id，查询该用户拥有的角色
     * @param id
     * @return
     */
    @GetMapping("user/{id}")
public  ResponseEntity<List<Role>> getRoleByUuserId(@PathVariable("id") Long id){
        log.info(" RoleConntroller .getRoleByUuserId页面传过来的用户id为：" + id);
       return ResponseEntity.ok(roleService.getRoleByUserId(id));
}

    /**
     * 根据Id获取一个角色
     * @param roleId
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Role> findUser(@PathVariable("id") Long roleId){
        return ResponseEntity.ok(roleService.findUser(roleId));
    }
}
