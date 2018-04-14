package com.yw.bos.service;

import com.yw.bos.base.IBaseDao;
import com.yw.bos.domain.Staff;
import com.yw.bos.utils.PageBean;

public interface IStaffService{

    void save(Staff model);

    void pageQuery(PageBean pageBean);

    void deleteBatch(String ids);

    Staff findById(String id);

    void update(Staff staff);
}
