package com.sea.consult.pojo;



import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.util.Date;

@Data
public class ConsultationRecord {

   @Id
   @KeySql(useGeneratedKeys = true)
   public Long id;
   public String problemDescription;
   public String processingMethod;
   public String consultDepartment;
   public String brandModel;
   public String systemPlatform;
   public String Consultant;
   public String recorder;
   public Date consultDate;
   public Date recordDate;
}
