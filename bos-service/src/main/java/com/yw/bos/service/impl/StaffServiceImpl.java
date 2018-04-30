package com.yw.bos.service.impl;

import com.yw.bos.dao.IStaffDao;
import com.yw.bos.domain.Staff;
import com.yw.bos.service.IStaffService;
import com.yw.bos.utils.PageBean;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {

    @Autowired
    private IStaffDao staffDao;

    public void save(Staff model) {
        staffDao.save(model);
    }

    public void pageQuery(PageBean pageBean) {
        staffDao.pageQuery(pageBean);
    }

    public void deleteBatch(String ids) {
        if (StringUtils.isNotBlank(ids)){
            String[] staffIds = ids.split(",");
            for (String id : staffIds) {
                staffDao.excuteUpdate("staff.delete",id);
            }
        }
    }

    public Staff findById(String id) {
        return staffDao.findById(id);
    }

    public void update(Staff staff) {
        staffDao.update(staff);
    }

    //查询未删除的取派员
    public List<Staff> findNotDelete() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
        detachedCriteria.add(Restrictions.eq("deltag","0"));
        return staffDao.findByCriteria(detachedCriteria);
    }
}
