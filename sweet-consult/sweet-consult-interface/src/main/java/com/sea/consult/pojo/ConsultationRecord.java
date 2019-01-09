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
   private Long id;
   private String problemDescription;
   private String processingMethod;
   private String consultDepartment;
   private String brandModel;
   private String systemPlatform;
   private String consultant;
   private String recorder;
   private Date consultDate;
   private Date recordDate;
}


