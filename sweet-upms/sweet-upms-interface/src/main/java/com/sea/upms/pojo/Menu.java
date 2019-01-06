package com.sea.upms.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Table;

@Data
@NameStyle(Style.camelhumpAndLowercase)
@Table(name = "sys_menu")
public class Menu {
}
