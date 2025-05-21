package com.springcore.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springcore.main.dao.PolicyHolderDao;
import com.springcore.main.model.Address;
import com.springcore.main.model.PolicyHolder;

@Service
public class PolicyHolderService {
	
	private PolicyHolderDao policyHolderDao;
	
	public PolicyHolderService(PolicyHolderDao policyHolderDao) {
		this.policyHolderDao = policyHolderDao;
	}
	
	public boolean addPolicyHolder(PolicyHolder policyHolder) {
		if(policyHolder ==  null) {
			System.out.println("NULL DATA");
			return false;
		}
		
		return policyHolderDao.addPolicyHolder(policyHolder);
	}
	
	public boolean addAddress(Address address) {
		if(address == null) {
			System.out.println("NULL DATA");
			return false;
		}
		return policyHolderDao.insertAddress(address);
	}
	public Address getAddressById(int id) {
		return policyHolderDao.getAddressById(id);
	}
	
	public List<PolicyHolder> getAllPolicyHolders(){
		return policyHolderDao.getAllPolicyHolders();
	}
	

}
