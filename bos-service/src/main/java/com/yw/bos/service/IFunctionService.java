package com.yw.bos.service;

import com.yw.bos.domain.Function;
import com.yw.bos.utils.PageBean;

import java.util.List;

public interface IFunctionService {

    List<Function> findListAll();

    void save(Function model);

    void pageQuery(PageBean pageBean);

    List<Function> findAll();

    List<Function> findFunctionByUserId(String id);

    List<Function> findMenu();
}
