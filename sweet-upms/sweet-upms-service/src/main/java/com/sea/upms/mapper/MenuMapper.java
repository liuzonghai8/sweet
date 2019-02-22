package com.sea.upms.mapper;


import com.sea.upms.pojo.Menu;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MenuMapper extends Mapper<Menu> {
    @Select("select * from sys_menu")
    List<Menu> queryAllMenus();
}

