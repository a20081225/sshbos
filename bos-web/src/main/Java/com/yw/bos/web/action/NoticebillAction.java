package com.yw.bos.web.action;

import com.yw.bos.crm.Customer;
import com.yw.bos.crm.ICustomerService;
import com.yw.bos.domain.Noticebill;
import com.yw.bos.service.INoticebillService;
import com.yw.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 业务通知单管理
 */
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill>{

    //crm代理
    @Autowired
    private ICustomerService proxy;

    //根据手机号回显客户信息
    public String findCustomerByTelephone(){
        Customer customer = proxy.findCustomerByTelephone(model.getTelephone());
        this.makeJson(customer,new String[]{});
        return NONE;
    }

    @Autowired
    private INoticebillService noticebillService;
    //添加表单
    public String add(){
        noticebillService.save(model);
        return LIST;
    }
}
