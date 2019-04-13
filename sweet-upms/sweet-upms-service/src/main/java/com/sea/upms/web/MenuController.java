package com.sea.upms.web;

import com.github.pagehelper.PageInfo;
import com.sea.common.vo.ResultBean;
import com.sea.upms.common.util.TreeUtil;
import com.sea.upms.dto.MenuTree;
import com.sea.upms.pojo.Menu;
import com.sea.upms.service.MenuService;
import com.sun.javafx.logging.PulseLogger;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sys/menu")
@Slf4j
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     *
     * 响应分页查询
     * @param page 页数
     * @param rows 每页大小
     * @param sortBy 排序字段
     * @param desc 是否降序
     * @param key 搜索条件
     * @return
     */
    @RequestMapping("page")
    public ResultBean<PageInfo<Menu>> queryMenuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key
    ){
        return  new ResultBean<>(menuService.queryMenuByPage(page,rows,sortBy,desc,key));
    }

    /**
     * 获取菜单树 所有menu
     * @return
     */
    @GetMapping("tree")
    public ResultBean<List<MenuTree>>  getTree(){
        return new ResultBean<>(TreeUtil.builTree(menuService.selectList(),-1));
    }

    /**
     * 查询所有
     * @return
     */
    @GetMapping("all")
    public List<Menu> qureyAll(){
        return menuService.queryAll();
    }

    /**
     * 根据Id查询单个menu
     * @param menuId
     * @return
     */
    @GetMapping("/{id}")
    public ResultBean<Menu> queryMenu(@PathVariable("id") Integer menuId){
        return new ResultBean<>(menuService.queryMenu(menuId));
    }

    /**
     * 添加一个菜单
     * @param menu
     */
    @PostMapping
    public  void addMenu(Menu menu){
        log.info(menu.toString());
        menuService.addMenu(menu);
    }

    /**
     * 更换一个菜单
     * @param menu
     */
   @PutMapping
    public void updateMenu(Menu menu){
        log.info(menu.toString());
        menuService.updateMenu(menu);
   }

   @DeleteMapping("/{id}")
    public  void deleteMenu(@PathVariable("id") Integer id){
       menuService.deleteMenu(id);
   }

}
