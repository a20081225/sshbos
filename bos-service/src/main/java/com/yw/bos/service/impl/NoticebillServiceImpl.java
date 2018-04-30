package com.yw.bos.service.impl;

import com.yw.bos.crm.ICustomerService;
import com.yw.bos.dao.IDecidedzoneDao;
import com.yw.bos.dao.INoticebillDao;
import com.yw.bos.dao.IWorkbillDao;
import com.yw.bos.domain.*;
import com.yw.bos.service.INoticebillService;
import com.yw.bos.utils.BOSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@Transactional
public class NoticebillServiceImpl implements INoticebillService {

    @Autowired
    private INoticebillDao noticebillDao;
    @Autowired
    private IDecidedzoneDao decidedzoneDao;
    @Autowired
    private IWorkbillDao workbillDao;

    @Autowired
    private ICustomerService customerService;
    
    public void save(Noticebill model) {
        User user = BOSUtils.getLoginUser();
        model.setUser(user);
        noticebillDao.save(model);

        //查询定区id
        String pickaddress = model.getPickaddress();
        String decidedzoneId = customerService.findDecidedzoneIdByAddress(pickaddress);
        //自动分单
        if (decidedzoneId !=null){
            Decidedzone decidedzone = decidedzoneDao.findById(decidedzoneId);
            Staff staff = decidedzone.getStaff();
            model.setStaff(staff);
            model.setOrdertype(Noticebill.ORDERTYPE_AUTO);
            //创建工单
            Workbill workbill = new Workbill();
            workbill.setAttachbilltimes(0);
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
            workbill.setNoticebill(model);
            workbill.setPickstate(Workbill.PICKSTATE_NO);
            workbill.setRemark(model.getRemark());
            workbill.setStaff(staff);
            workbill.setType(Workbill.TYPE_1);
            workbillDao.save(workbill);


        }else {
            model.setOrdertype(Noticebill.ORDERTYPE_MAN);
        }
    }
}
