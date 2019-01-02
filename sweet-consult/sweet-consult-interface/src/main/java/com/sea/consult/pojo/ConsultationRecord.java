package com.sea.consult.pojo;



import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Data
@NameStyle(Style.camelhumpAndLowercase)
@Table(name = "consultation_record")
public class ConsultationRecord {

   @Id
   @KeySql(useGeneratedKeys = true)
   public Long id;
   public String problemDescription;
   public String processingMethod;
   public String consultDepartment;
   public String brandModel;
   public String systemPlatform;
   public String consultant;
   public String recorder;
   public Date consultDate;
   public Date recordDate;
}


