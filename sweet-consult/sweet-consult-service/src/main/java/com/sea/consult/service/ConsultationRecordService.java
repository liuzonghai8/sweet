package com.sea.consult.service;

import com.github.pagehelper.PageHelper;
import com.sea.consult.mapper.ConsultationRecordMapper;
import com.sea.consult.pojo.ConsultationRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
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
            example.createCriteria().orLike("problemDescription", "%" + key + "%").orLike("processingMethod", "%" + key + "%")
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
        this.consultationRecordMapper.insert(consultationRecord);
    }
}
