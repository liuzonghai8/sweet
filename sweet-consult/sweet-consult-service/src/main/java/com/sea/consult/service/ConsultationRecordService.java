package com.sea.consult.service;

import com.github.pagehelper.PageHelper;
import com.sea.consult.mapper.ConsultationRecordMapper;
import com.sea.consult.pojo.ConsultationRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Slf4j
public class ConsultationRecordService {


    @Autowired
   private ConsultationRecordMapper consultationRecordMapper;

    public ConsultationRecord queryConsult(){
        ConsultationRecord cr = new ConsultationRecord();
        cr.setId(1L);
        return this.consultationRecordMapper.selectOne(cr);
    }


    public List<ConsultationRecord> queryAllConsult() {
        return this.consultationRecordMapper.selectAll();
    }

    //分页查询
    public List<ConsultationRecord> queryConsultByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        //开启分页
        PageHelper.startPage(page, rows);
        //过滤    .orLike("consultDepartment", "%" + key + "%")
        Example example = new Example(ConsultationRecord.class);
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().orLike("problemDescription", "%" + key + "%")
                    .orLike("processingMethod", "%" + key + "%")
            .orLike("consultant","%"+key+"%")
            .orLike("recorder","%"+key+"%")
                    ;
        }
//        .orLike("processingMethod", "%" + key + "%")
//                .orLike("Consultant", "%" + key + "%")
//                .orLike("recorder", "%" + key + "%")
//        if (StringUtils.isNotBlank(sortBy)) {
//            String sortByClause = sortBy + (desc ? " DESC" : " ASC");
//            example.setOrderByClause(sortByClause);
//        }
        List<ConsultationRecord> consultationRecords = this.consultationRecordMapper.selectByExample(example);

        return consultationRecords;
    }

    public void addConsult(ConsultationRecord consultationRecord) {
        log.info("添加的consult为："+ consultationRecord);
        this.consultationRecordMapper.insert(consultationRecord);
    }

    public void deleteConsult(Long id) {
        log.info("删除咨询记录Id= "+ id);
        this.consultationRecordMapper.deleteByPrimaryKey(id);
    }

    public void updateConsult( ConsultationRecord cr) {
        log.info("修改后的CR为： "+ cr);
        this.consultationRecordMapper.updateByPrimaryKey(cr);
    }
}
