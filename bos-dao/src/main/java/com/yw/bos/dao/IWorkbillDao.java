package com.yw.bos.dao;

import com.yw.bos.base.IBaseDao;
import com.yw.bos.domain.Workbill;

import java.util.List;

public interface IWorkbillDao extends IBaseDao<Workbill>{

    List<Workbill> findNewWorkbills();
}
