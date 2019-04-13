package com.sea.consult.web;

import com.github.pagehelper.PageInfo;
import com.sea.common.vo.ResultDTO;
import com.sea.consult.pojo.ConsultationRecord;
import com.sea.consult.service.ConsultationRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("consult")
@Slf4j
public class ConsultationController {

    @Autowired
    private ConsultationRecordService consultationRecordService;
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
    public ResultDTO<PageInfo<ConsultationRecord>> queryConsultByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key
    ){
        return new ResultDTO<>(this.consultationRecordService.queryConsultByPage(page,rows,sortBy,desc,key));
    }

    /**
     * 添加一条数据
     * @param consultationRecord
     * @return
     */
    @PostMapping
    public ResultDTO<Boolean> addConsult(ConsultationRecord consultationRecord ){
        log.info("post add");
        log.info(consultationRecord.toString());
        consultationRecord.setRecordDate(new Date());
        this.consultationRecordService.addConsult(consultationRecord);
        return new ResultDTO<>(true);
    }

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultDTO<Boolean> deleteConsult(@PathVariable("id") Long id){
        this.consultationRecordService.deleteConsult(id);
        return new ResultDTO<>(true);
    }

    /**
     * 更新一条记录
     * @param consultationRecord
     * @return
     */
    @PutMapping
    public ResultDTO<Boolean> updateConsult(ConsultationRecord consultationRecord){
        log.info("更新的记录为："+consultationRecord.toString());
        log.info(consultationRecord.getId().toString());
        //this.consultationRecordService.updateConsult(cr);
        return new ResultDTO<>(true);
    }

}

