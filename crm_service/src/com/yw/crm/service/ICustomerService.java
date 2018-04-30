package com.yw.crm.service;

import com.yw.crm.domain.Customer;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface ICustomerService {
	public List<Customer> findAll();

	//查询已经关联的
	public List<Customer> findHasAssociation(String decidedzoneId);

	//查询未关联的
	public List<Customer> findNotAssociation();

	//关联客户
	public void assigncustomerstodecidedzone(String decidedzoneId,Integer[] customerIds);

	//根据手机号查询客户
	public Customer findCustomerByTelephone(String telephone);

	//根据客户地址查询定区
	public String findDecidedzoneIdByAddress(String address);
}
