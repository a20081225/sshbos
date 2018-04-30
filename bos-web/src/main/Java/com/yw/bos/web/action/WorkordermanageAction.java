package com.yw.bos.web.action;

import com.yw.bos.domain.Workordermanage;
import com.yw.bos.service.IWorkordermanageService;
import com.yw.bos.web.action.base.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * 工作单管理
 */
@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage> {

    @Autowired
    private IWorkordermanageService workordermanageService;

    public String add() throws IOException {
        String f = "1";
        try{
            workordermanageService.save(model);
        }catch(Exception e){
            e.printStackTrace();
            f = "0";
        }
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(f);
        return NONE;
    }
}