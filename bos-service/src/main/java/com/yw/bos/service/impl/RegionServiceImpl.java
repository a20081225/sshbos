package com.yw.bos.service.impl;

import com.yw.bos.dao.IRegionDao;
import com.yw.bos.domain.Region;
import com.yw.bos.service.IRegionService;
import com.yw.bos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegionServiceImpl implements IRegionService {

    @Autowired
    private IRegionDao regionDao;

    //批量保存
    public void saveBatch(List<Region> regionList) {
        for (Region region : regionList) {
            regionDao.saveOrUpdate(region);
        }
    }

    public void pageQuery(PageBean pageBean) {
        regionDao.pageQuery(pageBean);
    }

    public void save(Region model) {
        regionDao.save(model);
    }

    public List<Region> findAll() {
        return regionDao.findAll();
    }

    public List<Region> findListByQ(String q) {
        return regionDao.findListByQ(q);
    }
}
