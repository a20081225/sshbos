package com.yw.bos.dao;

import com.yw.bos.base.IBaseDao;
import com.yw.bos.domain.Subarea;

import java.util.List;

public interface ISubareaDao extends IBaseDao<Subarea>{

    List<Object> findSubaresByProvince();
}
