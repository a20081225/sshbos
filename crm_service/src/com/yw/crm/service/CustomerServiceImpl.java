package com.yw.crm.service;
import com.yw.crm.domain.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Transactional
public class CustomerServiceImpl implements ICustomerService {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Customer> findAll() {
		String sql = "select * from t_customer";
		List<Customer> list = jdbcTemplate.query(sql, new RowMapper<Customer>(){
			public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
				int id = rs.getInt("id");//根据字段名称从结果集中获取对应的值
				String name = rs.getString("name");
				String station = rs.getString("station");
				String telephone = rs.getString("telephone");
				String address = rs.getString("address");
				String decidedzone_id = rs.getString("decidedzone_id");
				return new Customer(id, name, station, telephone, address, decidedzone_id);
			}
		});
		return list;
	}

    public List<Customer> findHasAssociation(String decidedzoneId) {
		String sql = "SELECT * FROM t_customer WHERE decidedzone_id = ?";
		List<Customer> list = jdbcTemplate.query(sql, new RowMapper<Customer>(){
			public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
				int id = rs.getInt("id");//根据字段名称从结果集中获取对应的值
				String name = rs.getString("name");
				String station = rs.getString("station");
				String telephone = rs.getString("telephone");
				String address = rs.getString("address");
				String decidedzone_id = rs.getString("decidedzone_id");
				return new Customer(id, name, station, telephone, address, decidedzone_id);
			}
		},decidedzoneId);
		return list;
    }

	public List<Customer> findNotAssociation() {
		String sql = "SELECT * FROM t_customer WHERE decidedzone_id IS NULL";
		List<Customer> list = jdbcTemplate.query(sql, new RowMapper<Customer>(){
			public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
				int id = rs.getInt("id");//根据字段名称从结果集中获取对应的值
				String name = rs.getString("name");
				String station = rs.getString("station");
				String telephone = rs.getString("telephone");
				String address = rs.getString("address");
				String decidedzone_id = rs.getString("decidedzone_id");
				return new Customer(id, name, station, telephone, address, decidedzone_id);
			}
		});
		return list;
	}

	public void assigncustomerstodecidedzone(String decidedzoneId, Integer[] customerIds) {
		String sql = "UPDATE t_customer SET  decidedzone_id = NULL WHERE decidedzone_id = ?";
		jdbcTemplate.update(sql,decidedzoneId);
		sql = "UPDATE t_customer SET  decidedzone_id = ? WHERE id = ?";
		for (Integer customerId : customerIds) {
			jdbcTemplate.update(sql,decidedzoneId,customerId);
		}
	}

	public Customer findCustomerByTelephone(String telephone) {
		String sql = "SELECT * FROM t_customer WHERE telephone = ?";
		List<Customer> list = jdbcTemplate.query(sql, new RowMapper<Customer>(){
			public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
				int id = rs.getInt("id");//根据字段名称从结果集中获取对应的值
				String name = rs.getString("name");
				String station = rs.getString("station");
				String telephone = rs.getString("telephone");
				String address = rs.getString("address");
				String decidedzone_id = rs.getString("decidedzone_id");
				return new Customer(id, name, station, telephone, address, decidedzone_id);
			}
		},telephone);
		if (list != null && list.size()>0){
			return list.get(0);
		}else {
			return null;
		}

	}

	public String findDecidedzoneIdByAddress(String address) {
		String sql = "SELECT decidedzone_id FROM t_customer WHERE address = ?";
		String decidedzoneId = jdbcTemplate.queryForObject(sql, String.class, address);
		return decidedzoneId;
	}


}
