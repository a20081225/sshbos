package com.yw.bos.service;

import com.yw.bos.domain.User;
import com.yw.bos.utils.PageBean;

public interface IUserService {

    User login(User model);

    void editPassword(String id, String password);

    User findUserByUsername(String username);

    void save(User model, String[] roleIds);

    void pageQuery(PageBean pageBean);
}
