package com.yw.bos.service.impl;

import com.yw.bos.dao.IDecidedzoneDao;
import com.yw.bos.dao.ISubareaDao;
import com.yw.bos.domain.Decidedzone;
import com.yw.bos.domain.Subarea;
import com.yw.bos.service.IDecidedzoneService;
import com.yw.bos.service.ISubareaService;
import com.yw.bos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService {

    @Autowired
    private IDecidedzoneDao decidedzoneDao;
    @Autowired
    private ISubareaDao subareaDao;

    public void save(Decidedzone model, String[] subareaid) {
        decidedzoneDao.save(model);
        for (String id : subareaid) {
            Subarea subarea = subareaDao.findById(id);
//            model.getSubareas().add(subarea);
            subarea.setDecidedzone(model);
        }
    }

    public void pageQuery(PageBean pageBean) {
        subareaDao.pageQuery(pageBean);
    }
}
