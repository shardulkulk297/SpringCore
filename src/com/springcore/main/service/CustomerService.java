package com.springcore.main.service;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcore.main.dao.CustomerDao;
import com.springcore.main.exception.InvalidIdException;
import com.springcore.main.model.Customer;

@Service
public class CustomerService {
	
	CustomerDao customerDao;
	
	@Autowired
	public CustomerService(CustomerDao customerDao) {		 
		this.customerDao = customerDao;
	}
	
	public boolean insertCustomer(Customer customer) {
		return customerDao.insertCustomer(customer);
	}
	
	public boolean updateCustomer(Customer customer) {
		
		if(customer == null) {
			System.out.println("Null Data");
			return false;
		}
		
		return customerDao.updateCustomer(customer);
	}
	
	public List<Customer> getAllCustomers(){
		return customerDao.getAllCustomers();
	}
	public List<Customer> getAllCustomersV2(){
		List<Map<String, Object>> list = customerDao.getAllCustomersV2();
		List<Customer> customers = new ArrayList<>();
		
		for(Map<String, Object> map: list) {
			Customer customer = new Customer((int)map.get("id"), (String)map.get("name"), (String)map.get("city"));
			customers.add(customer);
		}
		
		return customers;
	}
	
	public Customer getCustomerById(int id) throws InvalidIdException{
		
		if(id == 0 | id < 0) {
			throw new InvalidIdException("Id can't be 0 or negative");
			
		}
		Map<String, Object> customerMap = customerDao.getCustomerById(id);
		Customer customer = new Customer((int)customerMap.get("id"), (String)customerMap.get("name"),(String)customerMap.get("city"));
		return customer;
	}
	
	public boolean deleteCustomer(int id) throws InvalidIdException{

		if(id == 0 | id < 0) {
			throw new InvalidIdException("Id can't be 0 or negative");
			
		}
		
		return customerDao.deleteCustomer(id);
	}
	
	
	

}
