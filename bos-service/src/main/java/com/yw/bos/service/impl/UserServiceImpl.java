package com.yw.bos.service.impl;

import com.yw.bos.dao.IUserDao;
import com.yw.bos.domain.User;
import com.yw.bos.service.IUserService;
import com.yw.bos.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
