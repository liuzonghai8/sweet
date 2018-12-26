package com.sea.consult.service;

import com.sea.consult.mapper.ConsultationRecordMapper;
import com.sea.consult.pojo.ConsultationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultationRecordService {


    @Autowired
   private ConsultationRecordMapper consultationRecordMapper;

    public ConsultationRecord queryConsult(){
        ConsultationRecord cr = new ConsultationRecord();
        cr.setId(1L);
        return this.consultationRecordMapper.selectOne(cr);
    }


}
