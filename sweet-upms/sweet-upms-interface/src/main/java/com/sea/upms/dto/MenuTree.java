package com.sea.upms.dto;

import lombok.Data;

@Data
public class MenuTree extends TreeNode{
    private String name; //名称
    private Integer sort; //排序
    private String enableTag; // 是否使用标志
    private String label;

//    public MenuTree(){}
//
//    public MenuTree(int id,String name, int parentId) {
//        this.id = id;
//        this.parentId = parentId;
//        this.name = name;
//        this.label = name;
//    }


}
