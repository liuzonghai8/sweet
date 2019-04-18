package api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import com.sea.upms.pojo.User;

/**
 * @author bystander
 * @date 2018/10/1
 */
public interface UserApi {

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return sys/user/
     */
    @PostMapping("query")
     User queryUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    );
    @GetMapping("test")
     String test();
}
