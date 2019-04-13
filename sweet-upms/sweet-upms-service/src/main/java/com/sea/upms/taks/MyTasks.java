package com.sea.upms.taks;

import com.sea.upms.web.UserController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class MyTasks {
    //测试任务执行 定时任务
   private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

   @Autowired
    UserController userController;
    @Scheduled(fixedRate = 5000)//每隔5秒执行下面的方法
    public void reportCurrentTime() {
        log.info("this message is tasks {}", dateFormat.format(new Date()));
        //userController.queryUserByPage(1,10,"",false,"");
    }
}
