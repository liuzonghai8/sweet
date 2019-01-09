package com.sea.consult.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sea.consult.mapper.ConsultationRecordMapper;
import com.sea.consult.pojo.ConsultationRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;


@Service
@Slf4j
public class ConsultationRecordService {


    @Autowired
   private ConsultationRecordMapper consultationRecordMapper;

    //分页查询 List<ConsultationRecord>
    public PageInfo<ConsultationRecord> queryConsultByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        //开启分页
        PageHelper.startPage(page, rows);
        //过滤    .orLike("consultDepartment", "%" + key + "%")
        Example example = new Example(ConsultationRecord.class);
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().orLike("problemDescription", "%" + key + "%")
                    .orLike("processingMethod", "%" + key + "%")
                     .orLike("consultant","%"+key+"%")
                    .orLike("recorder","%"+key+"%")
                    .orLike("brandModel","%"+key+"%")
                    .orLike("consultDepartment","%"+key+"%")
                    .orLike("systemPlatform","%"+key+"%")
                    ;
        }
        log.info("查询条件为： " + example);
        if (StringUtils.isNotBlank(sortBy)) {
            String sortByClause = sortBy + (desc ? " DESC" : " ASC");
            log.info("排序条件为：" + sortByClause);
            example.setOrderByClause(sortByClause);
       }
        return new PageInfo<>(this.consultationRecordMapper.selectByExample(example));
    }

    //添加一条记录
    public void addConsult(ConsultationRecord consultationRecord) {
        log.info("添加的consult为："+ consultationRecord);
        this.consultationRecordMapper.insert(consultationRecord);
    }

    //根据ID删除一条记录
    public void deleteConsult(Long id) {
        log.info("删除咨询记录Id= "+ id);
        this.consultationRecordMapper.deleteByPrimaryKey(id);
    }

    //更新一条记录
    public void updateConsult( ConsultationRecord cr) {
        log.info("修改后的CR为： "+ cr);
        this.consultationRecordMapper.updateByPrimaryKey(cr);
    }
}
