package com.yw.bos.utils;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 分页属性
 */
public class PageBean {

    //当前页数
 	private Integer currentPage;
 	//总记录数
    private Integer total;
 	//每页显示条数
    private Integer pageSize;
 	//分页列表数据
    private List rows;
    //查询条件
    private DetachedCriteria detachedCriteria;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }

    public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
    }
}
