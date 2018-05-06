package com.yw.bos.service;

import com.yw.bos.domain.Subarea;
import com.yw.bos.utils.PageBean;

import java.awt.*;
import java.util.List;


public interface ISubareaService {

    void save(Subarea model);

    void pageQuery(PageBean pageBean);

    List<Subarea> findAll();

    List<Subarea> findNotAssociation();

    List<Subarea> findByDecidedzoneId(String decidedzoneId);

    List<Object> findSubaresByProvince();
}
