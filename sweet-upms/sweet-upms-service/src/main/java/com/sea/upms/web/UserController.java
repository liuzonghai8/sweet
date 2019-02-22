package com.sea.upms.web;

import com.github.pagehelper.PageInfo;
import com.sea.upms.pojo.User;
import com.sea.upms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        log.info("runing queryUserByPage()");
        return ResponseEntity.ok(userService.queryUserByPage(page,rows,sortBy,desc,key));
    }

//    @PostMapping
//    public ResponseEntity<Void> addUser( User user,@RequestParam("rids") List<Long> roleIds){
//        log.info("添加的用户： "+user.toString()+"分配的角色id: "+roleIds.toString());
//        userService.saveUser(user,roleIds);
//        return new ResponseEntity(HttpStatus.CREATED);
//    }
   @PostMapping
    public ResponseEntity<Void> addUser( User user){
        log.info("添加的用户： "+user.toString());
        userService.addUser(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * 根据Id获取一个用户
     * @param userid
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> findUser(@PathVariable("id") Long userid){
        return ResponseEntity.ok(userService.findUser(userid));
    }

    /**
     * 根据用户ID删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser( @PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateUser(User user ){
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }
//用户角色对应添加
    @PostMapping("role")
    public ResponseEntity<Void> saveUserRole( @RequestParam("uid") Long userId ,
                                              @RequestParam("rids") List<Long> roleIds){
        log.info("userid: "+ userId+ "  roleids "+ roleIds);
        userService.saveUserRole(userId,roleIds);
        return  new ResponseEntity(HttpStatus.CREATED);
    }
    //删除用户角色关系
    @DeleteMapping("role")
    public ResponseEntity<Void> deleteUserRole( @RequestParam("uid") Long userid,
                                                @RequestParam("rid") Long roleid){
        log.info("删除的userid"+userid+"删除的角色id"+roleid);
        userService.deleteUserRole(userid,roleid);
        return ResponseEntity.ok().build();
    }

//    @RequestMapping("page")
//    public ResponseEntity<PageInfo<User>> queryUserByPage(
//            @RequestParam(value = "page", defaultValue = "1") Integer page,
//            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
//            @RequestParam(value = "sortBy", required = false) String sortBy,
//            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
//            @RequestParam(value = "key", required = false) String key
//    ){
//        log.info("runing queryUserByPage()");
//        return ResponseEntity.ok(userService.queryUserByPage(page,rows,sortBy,desc,key));
//    }


}
