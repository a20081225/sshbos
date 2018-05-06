package com.yw.bos.web.action;

import com.yw.bos.domain.Role;
import com.yw.bos.service.IRoleService;
import com.yw.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * 角色管理
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{

    @Autowired
    private IRoleService roleService;

    private String functionIds;

    public String getFunctionIds() {
        return functionIds;
    }

    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }

    //保存关联权限
    public String add(){
        roleService.save(model,functionIds);
        return LIST;
    }

    public String pageQuery(){
        roleService.pageQuery(pageBean);
        this.makeJson(pageBean,new String[]{"functions","users"});
        return NONE;
    }

    public String listajax(){
        List<Role> list = roleService.findAll();
        this.makeJson(list,new String[]{"functions","users"});
        return NONE;
    }
}
