package com.yw.bos.dao.impl;

import com.yw.bos.base.impl.BaseDaoImpl;
import com.yw.bos.dao.IUserDao;
import com.yw.bos.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

    //登录查询
    public User findUserByUandP(String username, String password) {
        String hql = "FROM User u WHERE u.username = ? AND u.password = ?";
        List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username, password);
        if (list!=null && list.size()> 0){
            return list.get(0);
        }
        return null;
    }
}
