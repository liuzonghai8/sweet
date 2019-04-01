package com.sea.upms.web;

import com.sea.common.vo.PageResult;
import com.sea.common.vo.ResultDTO;
import com.sea.upms.dto.UserDTO;
import com.sea.upms.pojo.User;
import com.sea.upms.service.UserService;
import com.sea.upms.vo.UserVo;
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
    public ResultDTO<PageResult<UserVo>> queryUserByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key
    ){
        log.info("page"+page+"rows"+rows+"sortBy"+sortBy+"desc"+desc+"key"+key);
        return new ResultDTO<>(userService.queryUserByPage(page,rows,sortBy,desc,key));
    }

//    @PostMapping
//    public ResponseEntity<Void> addUser( User user,@RequestParam("rids") List<Long> roleIds){
//        log.info("添加的用户： "+user.toString()+"分配的角色id: "+roleIds.toString());
//        userService.saveUser(user,roleIds);
//        return new ResponseEntity(HttpStatus.CREATED);
//    }

    /**
     * 添加用户
     * @param userDTO
     * @return
     */
   @PostMapping
    public ResultDTO<Boolean> addUser( UserDTO userDTO){
        return new ResultDTO<Boolean>(userService.addUser(userDTO));
    }

    /**
     * 根据Id获取一个用户
     * @param userid
     * @return
     */
  //  @GetMapping("/{id}")
    public ResultDTO<User> findUser(@PathVariable("id") Long userid){
        return new ResultDTO<>(userService.findUser(userid));
    }
    /**
     * 根据Id获取一个用户所有信息
     * @param userid
     * @return
     */
    @GetMapping("/{id}")
    public ResultDTO<UserVo> findUserAllInfo(@PathVariable("id") Long userid){
        return new ResultDTO<>(userService.findUserAllInfo(userid));
    }

    /**
     * 根据用户ID删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultDTO<Boolean> deleteUser(@PathVariable("id") Long id){
        return new ResultDTO<Boolean>( userService.deleteUser(id));
    }

    /**
     * 更新用户
     * @param userDTO
     * @return
     */
    @PutMapping
    public ResultDTO<Boolean> updateUser(UserDTO userDTO ){
        return new ResultDTO<Boolean>( userService.updateUser(userDTO));
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

    /**
     * 根据用户名登陆
     * @param userName
     * @param password
     * @return
     */
    @PostMapping("login")
    public ResultDTO<User> login(@RequestParam("username") String userName,
                                 @RequestParam("password") String password){
        return new ResultDTO<>(userService.findUserByUser(userName,password));
    }

    @GetMapping("info")
    public String getUserInfo(){
        return "{roles: ['admin','edit']}";
    }

    @PostMapping("logout")
    public String logOut(){
        //设置用户的token为空
        return "seccuss";
    }

    @GetMapping("test")
    public String test(){
        return "test API success!";
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
