package com.yw.bos.service.impl;

import com.yw.bos.dao.IFunctionDao;
import com.yw.bos.domain.Function;
import com.yw.bos.domain.User;
import com.yw.bos.service.IFunctionService;
import com.yw.bos.utils.BOSUtils;
import com.yw.bos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {

    @Autowired
    private IFunctionDao functionDao;

    public List<Function> findListAll() {
        return functionDao.findListAll();
    }

    public void save(Function model) {
        Function parentFunction = model.getParentFunction();
        if (parentFunction != null && parentFunction.getId().equals("")) {
            model.setParentFunction(null);
        }
        functionDao.save(model);
    }

    public void pageQuery(PageBean pageBean) {
        functionDao.pageQuery(pageBean);
    }

    public List<Function> findAll() {
        return functionDao.findAll();
    }

    public List<Function> findFunctionByUserId(String id) {
        return functionDao.findFunctionByUserId(id);
    }

    public List<Function> findMenu() {
        User user = BOSUtils.getLoginUser();
        List<Function> list;
        if (user.getUsername().equals("admin")){
            list = functionDao.findAllMenu();
        }else {
            list = functionDao.findMenuByUserId(user.getId());
        }
        return list;
    }
}
