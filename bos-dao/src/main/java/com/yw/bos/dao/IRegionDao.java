package com.yw.bos.dao;

import com.yw.bos.base.IBaseDao;
import com.yw.bos.domain.Region;

import java.util.List;

public interface IRegionDao extends IBaseDao<Region> {

    List<Region> findListByQ(String q);
}
