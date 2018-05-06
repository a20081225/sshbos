package com.yw.bos.service.impl;

import com.yw.bos.dao.IUserDao;
import com.yw.bos.domain.Role;
import com.yw.bos.domain.User;
import com.yw.bos.service.IUserService;
import com.yw.bos.utils.MD5Utils;
import com.yw.bos.utils.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;
    public User login(User user) {
        //md5加密
        String password = MD5Utils.md5(user.getPassword());
        return userDao.findUserByUandP(user.getUsername(),password);
    }

    public void editPassword(String id, String password) {
        password = MD5Utils.md5(password);
        userDao.excuteUpdate("user.editpassword",password,id);
    }

    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    public void save(User model, String[] roleIds) {
        String password = model.getPassword();
        password =MD5Utils.md5(password);
        model.setPassword(password);
        userDao.save(model);
        model.setRoles(new HashSet<Role>());
        if (roleIds !=null && roleIds.length>0){
            for (String roleId : roleIds) {
                Role role = new Role(roleId);
                model.getRoles().add(role);
            }
        }
    }

    public void pageQuery(PageBean pageBean) {
        userDao.pageQuery(pageBean);
    }
}
