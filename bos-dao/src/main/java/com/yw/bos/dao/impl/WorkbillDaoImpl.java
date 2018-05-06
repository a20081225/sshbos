package com.yw.bos.dao.impl;

import com.yw.bos.base.impl.BaseDaoImpl;
import com.yw.bos.dao.IWorkbillDao;
import com.yw.bos.domain.Workbill;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkbillDaoImpl extends BaseDaoImpl<Workbill> implements IWorkbillDao {

    public List<Workbill> findNewWorkbills()
    {
        String hql = "FROM Workbill w WHERE w.type = '新单'";
        List<Workbill> list = (List<Workbill>) this.getHibernateTemplate().find(hql);
        return list;
    }
}
