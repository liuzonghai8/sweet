package com.sea.upms.common.util;

import com.sea.upms.dto.MenuTree;
import com.sea.upms.dto.TreeNode;
import com.sea.upms.pojo.Menu;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TreeUtil {

    /**
     * 两层循环实现建树
     *
     * @param treeNodes 传入的树节点列表
     * @return
     */
    public static <T extends TreeNode> List<T> bulid(List<T> treeNodes, Object root) {
        log.info("Tree - build");

        List<T> trees = new ArrayList<T>();
        for (T treeNode : treeNodes) {
            log.info("treeNode-parenid =="+treeNode.getParentId());
            if (root.equals(treeNode.getParentId())) {
                //log.info("treeNode-parenid == root"+treeNode.toString());
                trees.add(treeNode);
            }
            for (T it : treeNodes) {
                if (it.getParentId() == treeNode.getId()) {
                    //log.info("it.getParentId() == treeNode.getId()"+it.toString());
                    if (treeNode.getChildren() == null) {
                        //log.info("treeNode.getChildren()"+treeNode.getChildren().toString());
                        treeNode.setChildren(new ArrayList<TreeNode>());
                    }
                    treeNode.add(it);
                    //log.info("treeNode  "+ treeNode.toString());
                }
            }
        }
        log.info("buid  -trees = "+ trees);
        return trees;
    }

    /**
     * 构建菜单树
     * @param menus
     * @param root
     * @return
     */
    public static List<MenuTree> builTree(List<Menu> menus, int root){
        log.info("TreeUtil -builTree");
        List<MenuTree> trees = new ArrayList<MenuTree>();
        MenuTree node;
        for (Menu menu : menus) {
            //log.info("parentId:  "+ menu.getParentId());
            node = new MenuTree();
            node.setId(menu.getId());
            node.setParentId(menu.getParentId());
            node.setName(menu.getName());
            node.setEnableTag(menu.getEnableTag());
            node.setSort(menu.getSort());
            node.setLabel(menu.getName());
            node.setComponent(menu.getComponent());
            node.setPath(menu.getFrontPath());
            node.setIcon(menu.getIcon());
           // log.info("当前node："+node.toString());
            trees.add(node);
        }
        log.info("trees is :"+trees.toString());
        return TreeUtil.bulid(trees,root);
    }
}
