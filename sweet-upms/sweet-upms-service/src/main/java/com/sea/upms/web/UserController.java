package com.sea.upms.web;

import com.github.pagehelper.PageInfo;
import com.sea.common.vo.ResultBean;
import com.sea.upms.pojo.User;
import com.sea.upms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.tree.VoidDescriptor;

import java.util.List;


@RestController
@RequestMapping("sys/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
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
    public ResponseEntity<PageInfo<User>> queryUserByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key
    ){
        return ResponseEntity.ok(userService.queryUserByPage(page,rows,sortBy,desc,key));
    }

    @PostMapping
    public ResponseEntity<Void> addUser( User user,@RequestParam("rids") List<Long> roleIds){
        log.info("添加的用户： "+user.toString()+"分配的角色id: "+roleIds.toString());
        userService.saveUser(user,roleIds);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser( @PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    /**
     * 更新一条记录
     * @param user
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateUser(User user ){
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("test")
    public String getUser(){
       User user =  userService.exist("登录名");
      // log.info("user " + user.toString());
        log.info("user:");
       return null; //user.toString();
    }


}
