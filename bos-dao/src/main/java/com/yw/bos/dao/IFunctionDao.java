package com.yw.bos.dao;

import com.yw.bos.base.IBaseDao;
import com.yw.bos.domain.Function;

import java.util.List;

public interface IFunctionDao extends IBaseDao<Function>{
    List<Function> findListAll() ;

    List<Function> findFunctionByUserId(String id);


    List<Function> findAllMenu();

    List<Function> findMenuByUserId(String id);
}
