package com.springcore.main.dao;

import java.util.List;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springcore.main.exception.InvalidIdException;
import com.springcore.main.model.Customer;

@Repository
public class CustomerDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public CustomerDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	public boolean insertCustomer(Customer customer) {
		
		String sql = "INSERT INTO Customer(name, city) VALUES(?, ?)";
		String name = customer.getName();
		String city = customer.getCity();
		int rowsAdded =  jdbcTemplate.update(sql, name, city);
		
		if(rowsAdded > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean updateCustomer(Customer customer) {
		String sql = "UPDATE Customer SET name = ?, city = ? WHERE id = ?";
		String name = customer.getName();
		String city = customer.getCity();
		int id = customer.getId();
		int rowsUpdated =  jdbcTemplate.update(sql, name, city, id);
		
		if(rowsUpdated > 0) {
			return true;
		}
		else {
			throw new InvalidIdException("Invalid ID");
		}
		
	
	}
	
	public List<Customer> getAllCustomers() {
		String sql = "Select * from Customer";
		List<Customer> customers = jdbcTemplate.query(sql, new RowMapper<Customer>() {

			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Customer customer = new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("city"));
				return customer;
			}
			
			
		});
		
		return customers;
	}
	
	public List<Map<String, Object>> getAllCustomersV2(){
		String sql = "Select * from Customer";
		List<Map<String, Object>> customers = jdbcTemplate.queryForList(sql);
		return customers;
		
	}
	
	public Map<String, Object> getCustomerById(int id){
		
		String sql = "Select * from customer WHERE id = ?";
		Map<String, Object> customerById = jdbcTemplate.queryForMap(sql, id);
		
		if(customerById != null) {
			return customerById;
		}
		else {
			throw new InvalidIdException("Customer not found ID INVALID");
		}
		
		
		
	}
	
	public boolean deleteCustomer(int id) {
		String sql = "DELETE FROM Customer WHERE id = ?";
		int rowsDeleted = jdbcTemplate.update(sql, id);
		if(rowsDeleted > 0) {
			return true;
		}
		else {
			throw new InvalidIdException("Customer not found, ID INVALID");
		}
	}

}
