package com.ex.model.vo;

import java.util.ArrayList;
import java.util.List;

public class PageInfoVO<T> {

    int page;

    int limit;

    int total;//总页数

    long count;//总的记录数

    List<T> list = new ArrayList<>(); //分页的列表数据

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
