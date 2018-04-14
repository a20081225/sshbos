package com.yw.bos.web.action;

import com.yw.bos.domain.Staff;
import com.yw.bos.service.IStaffService;
import com.yw.bos.utils.PageBean;
import com.yw.bos.web.action.base.BaseAction;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 取派员管理
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff>{

    @Autowired
    private IStaffService staffService;

    //添加
    public String add(){
        staffService.save(model);
        return LIST;
    }

    //修改
    public String edit(){
        Staff staff = staffService.findById(model.getId());
        staff.setName(model.getName());
        staff.setTelephone(model.getTelephone());
        staff.setHaspda(model.getHaspda());
        staff.setStation(model.getStation());
        staff.setStandard(model.getStandard());
        staffService.update(staff);
        return LIST;
    }

    private String ids;
    //批量删除
    public String deleteBatch(){
        staffService.deleteBatch(ids);
        return LIST;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    private int page;
    private int rows;
    //分页
    public String pageQuery() throws IOException {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
        pageBean.setDetachedCriteria(detachedCriteria);
        staffService.pageQuery(pageBean);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"currentPage","pageSize","detachedCriteria"});
        String json = JSONObject.fromObject(pageBean,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
