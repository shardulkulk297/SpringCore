package com.springcore.main.dao;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springcore.main.model.Address;
import com.springcore.main.model.PolicyHolder;

@Repository
public class PolicyHolderDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public PolicyHolderDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public boolean addPolicyHolder(PolicyHolder policyHolder) {
		String sql = "Insert into policyholder (name, panNo, address_id) VALUES (?, ?, ?)";
		int rowsAdded = jdbcTemplate.update(sql, policyHolder.getName(), policyHolder.getPanNo(), policyHolder.getAddress().getId());
		
		if(rowsAdded > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean insertAddress(Address address) {
		String sql = "Insert into Address (street, city, state) VALUES(?,?,?)";
		int rowsAdded = jdbcTemplate.update(sql, address.getStreet(), address.getCity(), address.getState());
		
		if(rowsAdded > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Address getAddressById(int id) {
		String sql = "Select * from Address where id = ?";
		Map<String, Object> addressMap = jdbcTemplate.queryForMap(sql, id);
		
		return new Address((int)addressMap.get("id"), (String)addressMap.get("street"), (String)addressMap.get("city"), (String)addressMap.get("state"));
	}
	
	public List<PolicyHolder> getAllPolicyHolders(){
		String sql = "Select * from policyholder JOIN Address ON policyholder.address_id = address.id";
		List<PolicyHolder> policyHolders = jdbcTemplate.query(sql, new RowMapper<PolicyHolder>(){

			@Override
			public PolicyHolder mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				
				return new PolicyHolder(rs.getInt("id"), rs.getString("name"), rs.getString("panNo"), new Address(rs.getInt("id"), rs.getString("street"), rs.getString("city"), rs.getString("state")));
			}
			
		});
		
		return policyHolders;
	}
	
	
	

}
