package com.yw.bos.web.action;

import com.yw.bos.domain.Staff;
import com.yw.bos.service.IStaffService;
import com.yw.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

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


    //分页
    public String pageQuery(){
        staffService.pageQuery(pageBean);
        this.makeJson(pageBean,new String[]{"currentPage","pageSize","detachedCriteria","decidedzones"});
        return NONE;
    }

    //下拉菜单
    public String listajax(){
        List<Staff> list = staffService.findNotDelete();
        this.makeJson(list,new String[]{"decidedzones"});
        return NONE;
    }

}
