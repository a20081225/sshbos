package com.yw.bos.dao.impl;

import com.yw.bos.base.impl.BaseDaoImpl;
import com.yw.bos.dao.IRegionDao;
import com.yw.bos.domain.Region;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {

    //下拉菜单
    public List<Region> findListByQ(String q) {
        String hql = "FROM Region r WHERE r.shortcode LIKE ? OR r.citycode LIKE ? OR r.province LIKE ? OR r.city LIKE ? OR r.district LIKE ?";
        List<Region> list = (List<Region>) this.getHibernateTemplate().find(hql, "%" + q + "%", "%" + q + "%", "%" + q + "%", "%" + q + "%", "%" + q + "%");
        return list;
    }
}
