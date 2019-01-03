package com.sea.common.beans;


import lombok.Data;

import java.util.List;

/**
 * @author bystander
 * @date 2018/9/15
 */
@Data
public class PageResult2<T> {

    private Long total;
    private Integer totalPage;
    private List<T> items;

    public PageResult2(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult2(Long total, Integer totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }


}
