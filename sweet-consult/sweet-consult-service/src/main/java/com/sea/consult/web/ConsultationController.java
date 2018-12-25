package com.sea.consult.web;

import com.sea.consult.pojo.ConsultationRecord;
import com.sea.consult.service.ConsultationRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consult")
@Slf4j
public class ConsultationController {

    @Autowired
    private ConsultationRecordService consultationRecordService;

    @GetMapping("/")
    public String index(){
        return  "/index";
    }

    @GetMapping("test")
    public  String test(){
        log.info("test 访问成功");
        return "name=1,dd=1";

    }

    @GetMapping("one")
    public ConsultationRecord queryConsult(){
     return this.consultationRecordService.queryConsult();
    }

}
