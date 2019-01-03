package com.sea.consult.web;

import com.github.pagehelper.PageInfo;
import com.sea.common.vo.ResultBean;
import com.sea.consult.pojo.ConsultationRecord;
import com.sea.consult.service.ConsultationRecordService;
import lombok.extern.slf4j.Slf4j;
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
     * 响应分页查询
     * @param page 页数
     * @param rows 每页大小
     * @param sortBy 排序字段
     * @param desc 是否降序
     * @param key 搜索条件
     * @return
     */
    @RequestMapping("page")
    public ResultBean<PageInfo<ConsultationRecord>> queryConsultByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key
    ){
        return new ResultBean<>(this.consultationRecordService.queryConsultByPage(page,rows,sortBy,desc,key));
    }

    /**
     * 添加一条数据
     * @param consultationRecord
     * @return
     */
    @PostMapping
    public ResultBean<Boolean> addConsult( ConsultationRecord consultationRecord ){
        log.info("post add");
        log.info(consultationRecord.toString());
        this.consultationRecordService.addConsult(consultationRecord);
        return new ResultBean<>(true);
    }

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultBean<Boolean> deleteConsult( @PathVariable("id") Long id){
        this.consultationRecordService.deleteConsult(id);
        return new ResultBean<>(true);
    }

    /**
     * 更新一条记录
     * @param cr
     * @return
     */
    @PutMapping
    public ResultBean<Boolean> updateConsult(ConsultationRecord cr){
        this.consultationRecordService.updateConsult(cr);
        return new ResultBean<>(true);
    }

}

