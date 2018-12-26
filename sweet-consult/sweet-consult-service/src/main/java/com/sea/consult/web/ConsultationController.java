package com.sea.consult.web;

import com.sea.consult.pojo.ConsultationRecord;
import com.sea.consult.service.ConsultationRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("consult")
@Slf4j
public class ConsultationController {

    @Autowired
    private ConsultationRecordService consultationRecordService;

    @GetMapping("all")
    public List<ConsultationRecord> queryAllConsult(){
        return this.consultationRecordService.queryAllConsult();
    }
    @GetMapping("one")
    public ConsultationRecord queryConsult(){
     return this.consultationRecordService.queryConsult();
    }

    /**
     *
     * @param page 页数
     * @param rows 每页大小
     * @param sortBy 排序字段
     * @param desc 是否降序
     * @param key 搜索条件
     * @return
     */
    @RequestMapping("page")
    public List<ConsultationRecord> queryConsultByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key
    ){
        return this.consultationRecordService.queryConsultByPage(page,rows,sortBy,desc,key);
    }

    @PostMapping
    public void addConsult( ConsultationRecord cr){
        log.info("post add");
        log.info(cr.toString());
        this.consultationRecordService.addConsult(cr);
    }

}

