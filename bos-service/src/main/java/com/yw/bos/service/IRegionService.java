package com.yw.bos.service;

import com.yw.bos.base.IBaseDao;
import com.yw.bos.domain.Region;
import com.yw.bos.utils.PageBean;

import java.util.List;

public interface IRegionService{

    void saveBatch(List<Region> regionList);

    void pageQuery(PageBean pageBean);

    void save(Region model);

    List<Region> findAll();

    List<Region> findListByQ(String q);
}
