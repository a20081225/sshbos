package com.yw.bos.dao.impl;

import com.yw.bos.base.impl.BaseDaoImpl;
import com.yw.bos.dao.ISubareaDao;
import com.yw.bos.domain.Subarea;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubareaDaoImpl extends BaseDaoImpl<Subarea> implements ISubareaDao {
    public List<Object> findSubaresByProvince() {
        String hql = "SELECT r.province,COUNT(*) FROM Subarea s LEFT OUTER JOIN s.region r GROUP BY r.province";
        List<Object> list = (List<Object>) this.getHibernateTemplate().find(hql);
        return list;
    }
}
