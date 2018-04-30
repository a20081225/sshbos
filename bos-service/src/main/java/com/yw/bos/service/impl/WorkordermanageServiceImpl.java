package com.yw.bos.service.impl;

import com.yw.bos.dao.IWorkordermanageDao;
import com.yw.bos.domain.Workordermanage;
import com.yw.bos.service.IWorkordermanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WorkordermanageServiceImpl implements IWorkordermanageService {

    @Autowired
    private IWorkordermanageDao workordermanageDao;

    public void save(Workordermanage model) {
        workordermanageDao.save(model);
    }
}
