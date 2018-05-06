package com.yw.bos.service;

import com.yw.bos.domain.Role;
import com.yw.bos.utils.PageBean;

import java.util.List;

public interface IRoleService {

    void save(Role model, String functionIds);

    void pageQuery(PageBean pageBean);

    List<Role> findAll();
}
