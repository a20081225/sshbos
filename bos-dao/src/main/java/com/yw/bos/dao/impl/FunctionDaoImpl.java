package com.yw.bos.dao.impl;

import com.yw.bos.base.impl.BaseDaoImpl;
import com.yw.bos.dao.IFunctionDao;
import com.yw.bos.domain.Function;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {

    public List<Function> findListAll() {
        String hql = "FROM Function f where f.parentFunction is null";
        List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
        return list;
    }

    public List<Function> findFunctionByUserId(String id) {
        String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles r LEFT OUTER JOIN r.users u where u.id = ?";
        List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql,id);
        return list;
    }


    public List<Function> findAllMenu() {
        String hql = "FROM Function f WHERE f.generatemenu = '1' ORDER BY f.zindex ASC";
        List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
        return list;
    }

    public List<Function> findMenuByUserId(String id) {
        String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles r"
                    +" LEFT OUTER JOIN r.users u WHERE u.id = ? AND f.generatemenu = '1'"
                    +" ORDER BY f.zindex ASC";
        List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql,id);
        return list;
    }
}
