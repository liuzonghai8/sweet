package com.sea.upms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sea.common.enums.CommonConstant;
import com.sea.common.enums.ExceptionEnum;
import com.sea.common.exception.SweetException;
import com.sea.upms.mapper.MenuMapper;
import com.sea.upms.pojo.Menu;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Slf4j
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public PageInfo<Menu> queryMenuByPage(Integer page, Integer rows,
                                          String sortBy, Boolean desc, String key) {

        PageHelper.startPage(page,rows);//开启分页
        //过滤查询条件
        Example example = new Example(Menu.class);
        if(StringUtils.isNotBlank(key)){
            log.info("查询条件为： " + key.toString());
            example.createCriteria().orLike("name","%"+ key +"%")
                    .orLike("frontPath","%" + key +"%")
                    .orLike("requestUrl" ,"%"+key + "%")
            ;
        }
        //排序条件
        if(StringUtils.isNotBlank(sortBy)){
            log.info(sortBy+" "+(desc? " DESC" : " ASC") + " 排序");
            example.setOrderByClause(sortBy+(desc ? " DESC" : " ASC"));
        }
        List<Menu> menuList = menuMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(menuList)){
            throw new SweetException(ExceptionEnum.MENU_NOT_FOUND);
        }
        return new PageInfo<>(menuList);
    }

    public List<Menu> queryAll() {
       return menuMapper.selectAll();
       // return menuMapper.queryAllMenus();
    }

    public List<Menu> getTree() {
        return null;
    }

    /**
     * 查询有效的menu
     * @return
     */
    public List<Menu> selectList() {
        log.info("SelectList menu");
        Menu menu = new Menu();
        menu.setEnableTag(CommonConstant.STATUS_NORMAL);
        log.info("SelectList menu2");
        //return menuMapper.selectByExample(menu);
        List<Menu> menus = menuMapper.selectAll();
        log.info("查询到是menus："+menus.toString());
        return menus;
    }
}
