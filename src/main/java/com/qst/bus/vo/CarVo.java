package com.qst.bus.vo;

import com.qst.bus.domain.Car;

public class CarVo extends Car {
    private Integer page;
    private Integer limit;

    /*接受多个角色id*/
    private String ids[];

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    /*分页参数*/
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
