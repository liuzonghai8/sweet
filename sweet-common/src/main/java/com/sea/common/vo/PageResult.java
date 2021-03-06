package com.sea.common.vo;


import lombok.Data;

import java.util.List;

/**
 * @author bystander
 * @date 2018/9/15
 */
@Data
public class PageResult<T> {

    private Long total;
    private Integer totalPage;
    private List<T> records;

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.records = items;
    }

    public PageResult(Long total, Integer totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.records = items;
    }


}
