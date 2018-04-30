package com.yw.bos.web.action;

import com.yw.bos.crm.Customer;
import com.yw.bos.crm.ICustomerService;
import com.yw.bos.domain.Decidedzone;
import com.yw.bos.service.IDecidedzoneService;
import com.yw.bos.service.impl.DecidedzoneServiceImpl;
import com.yw.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * 定区管理
 */

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone>{

    @Autowired
    private IDecidedzoneService decidedzoneService;

    private String[] subareaid;

    public void setSubareaid(String[] subareaid) {
        this.subareaid = subareaid;
    }

    //添加
    public String add(){
        decidedzoneService.save(model,subareaid);
        return LIST;
    }

    //分页
    public String pageQuery(){
        decidedzoneService.pageQuery(pageBean);
        this.makeJson(pageBean,new String[]{"currentPage","pageSize","detachedCriteria","subareas","decidedzones"});
        return NONE;
    }

    //crm代理
    @Autowired
    private ICustomerService proxy;

//    ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
//            new String[] {"applicationContext.xml"});
//
//    ICustomerService customerService = (ICustomerService) classPathXmlApplicationContext.getBean("crmClient");
    //未关联的
    public String findNotAssociation(){
        List<Customer> list = proxy.findNotAssociation();
        this.makeJson(list,new String[]{});
        return NONE;
    }
    //已关联的
    public String findHasAssociation(){
        String id = model.getId();
        List<Customer> list = proxy.findHasAssociation(id);
        this.makeJson(list,new String[]{});
        return NONE;
    }

    private List<Integer> customerIds;

    public List<Integer> getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(List<Integer> customerIds) {
        this.customerIds = customerIds;
    }

    //关联客户
    public String assign(){
        proxy.assigncustomerstodecidedzone(model.getId(),customerIds);
        return LIST;
    }
}
