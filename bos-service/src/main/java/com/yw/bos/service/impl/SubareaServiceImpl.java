package com.yw.bos.service.impl;

import com.yw.bos.dao.ISubareaDao;
import com.yw.bos.domain.Subarea;
import com.yw.bos.service.ISubareaService;
import com.yw.bos.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;

@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {

    @Autowired
    private ISubareaDao subareaDao;

    public void save(Subarea model) {
        subareaDao.save(model);
    }

    public void pageQuery(PageBean pageBean) {
        subareaDao.pageQuery(pageBean);
    }

    public List<Subarea> findAll() {
        return subareaDao.findAll();
    }

    public List<Subarea> findNotAssociation() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
        detachedCriteria.add(Restrictions.isNull("decidedzone"));
        return subareaDao.findByCriteria(detachedCriteria);
    }

    public List<Subarea> findByDecidedzoneId(String decidedzoneId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
        detachedCriteria.add(Restrictions.eq("decidedzone.id",decidedzoneId));
        return subareaDao.findByCriteria(detachedCriteria);
    }
}
