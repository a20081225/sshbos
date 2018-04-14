package com.yw.bos.dao;

import com.yw.bos.base.IBaseDao;
import com.yw.bos.domain.User;

public interface IUserDao extends IBaseDao<User>{

    User findUserByUandP(String username, String password);
}
