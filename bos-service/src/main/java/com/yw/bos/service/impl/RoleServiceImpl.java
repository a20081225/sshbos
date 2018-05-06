package com.yw.bos.service.impl;

import com.yw.bos.dao.IRoleDao;
import com.yw.bos.domain.Function;
import com.yw.bos.domain.Role;
import com.yw.bos.service.IRoleService;
import com.yw.bos.utils.PageBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    public void save(Role model, String functionIds) {
        roleDao.save(model);
        model.setFunctions(new HashSet<Function>());
        if (StringUtils.isNotBlank(functionIds)){
            String[] fIds = functionIds.split(",");
            for (String fId : fIds) {
                Function function = new Function(fId);
                Collection<Function> functions = model.getFunctions();
                functions.add(function);
            }
        }
    }

    public void pageQuery(PageBean pageBean) {
        roleDao.pageQuery(pageBean);
    }

    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
