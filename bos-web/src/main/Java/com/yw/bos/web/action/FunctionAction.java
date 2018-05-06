package com.yw.bos.web.action;

import com.yw.bos.domain.Function;
import com.yw.bos.service.IFunctionService;
import com.yw.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * 权限管理
 */
@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function>{

    @Autowired
    private IFunctionService functionService;

    //权限选择下拉菜单
    public String listajax(){
        List<Function> list = functionService.findListAll();
        this.makeJson(list,new String[]{"parentFunction","roles"});
        return NONE;
    }

    public String add(){
        functionService.save(model);
        return LIST;
    }

    public String pageQuery(){
        String page = model.getPage();
        pageBean.setCurrentPage(Integer.valueOf(page));
        functionService.pageQuery(pageBean);
        this.makeJson(pageBean,new String[]{"parentFunction","children","roles"});
        return NONE;
    }

    //加载菜单
    public String findMenu(){
        List<Function> list = functionService.findMenu();
        this.makeJson(list,new String[]{"parentFunction","roles","children"});
        return NONE;
    }
}
