package com.springcore.main;

import java.lang.foreign.AddressLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springcore.main.exception.InvalidIdException;
import com.springcore.main.model.Address;
import com.springcore.main.model.Customer;
import com.springcore.main.model.PolicyHolder;
import com.springcore.main.service.CustomerService;
import com.springcore.main.service.PolicyHolderService;












public class Main {
	
	public static void main(String args[]) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		CustomerService customerService = context.getBean(CustomerService.class);
		PolicyHolderService policyHolderService = context.getBean(PolicyHolderService.class);
		Scanner sc = new Scanner(System.in);
		boolean running = true;
		while(running) 
		{
			
			System.out.println("--------------SpringCore------------");
			System.out.println("1. Insert Customer");
			System.out.println("2. Update Customer");
			System.out.println("3. Get Customer by Id");
			System.out.println("4. Delete Customer");
			System.out.println("5. Get All Customers");
			System.out.println("6. Insert PolicyHolder;");
			System.out.println("7. Get All PolicyHolders:");
			System.out.println("8. Insert Address: ");
			System.out.println("0. EXIT");
			System.out.println("Enter Choice: ");
			int choice = sc.nextInt();
			
			switch(choice) 
		  {
			case 1->{
				System.out.println("Enter Name: ");
				String name = sc.next();
				System.out.println("Enter City: ");
				String city = sc.next();
				
				Customer customer = context.getBean(Customer.class,name, city);
				boolean result = customerService.insertCustomer(customer);
				
				if(result) {
					System.out.println("Customer inserted Successfully");
				}
				else {
					System.out.println("Insertion Failed");
				}
			}
			
			case 2->{
				System.out.println("Enter Customer Id: ");
				int id = sc.nextInt();
				System.out.println("Here are your exisiting Details: ");
				Customer customer = customerService.getCustomerById(id);
				System.out.println(customer);
				
				boolean runningg = true;
				while(runningg) {
					System.out.println("What do you want to Update?: ");
					System.out.println("1. Name");
					System.out.println("2. City");
					System.out.println("0. Skip");
					int value = sc.nextInt();
					switch(value) {
					case 1->{
						System.out.println("Existing name: " + customer.getName());
						System.out.println("Enter Name: ");
						String name = sc.next();
						customer.setName(name);
					}
					case 2->{
						System.out.println("Existing city: " + customer.getCity());
						System.out.println("Enter City: ");
						String city = sc.next();
						customer.setCity(city);
					}
					case 0->{
						System.out.println("Ok!!");
						runningg = false;
					}
					default->{
						System.out.println("Invalid Choice");
					}
					}
					
				}
				
				boolean result = customerService.updateCustomer(customer);
				
				if(result) {
					System.out.println("Customer updated Successfully");
				}
				else {
					System.out.println("Updation Failed");
				}
				
			}
			
			case 3->{
				System.out.println("Get Customer by Id");
				System.out.println("Enter customer Id: ");
				int id = sc.nextInt();
				
				try {
					Customer customer = customerService.getCustomerById(id);
					System.out.println(customer);
					
				} catch (InvalidIdException e) {
					
					System.out.println(e.getMessage());
				}
				
				
			}
			
			case 4->{
				System.out.println("Delete");
				System.out.println("Enter customer id to delete: ");
				int id = sc.nextInt();
				boolean result = customerService.deleteCustomer(id);
				if(result) {
					System.out.println("Deleted Successfully");
				}
				
				
			}
			case 5->{
				System.out.println("Get All Customers");
				System.out.println("Version 1");
				List<Customer> customers = customerService.getAllCustomers();
				for(Customer c: customers) {
					System.out.println(c);
				}
				
				System.out.println("Version 2");
				List<Customer> customers2 = customerService.getAllCustomersV2();
				for(Customer c: customers2) {
					System.out.println(c);
				}
				
			
				
				
			}
			
			case 6->{
				System.out.println("Enter Your Information: ");
				System.out.println("Enter name: ");
				String name = sc.next();
				System.out.println("Enter PAN NO");
				String panString = sc.next();
				System.out.println("Enter Address Id: ");
				int aid = sc.nextInt();
				Address address = policyHolderService.getAddressById(aid);
				PolicyHolder policyholder = new PolicyHolder(name, panString, address);
				boolean result2 = policyHolderService.addPolicyHolder(policyholder);
				if(result2) {
					System.out.println("Inserted Successfully");
					
				}
				else {
					System.out.println("SOMETHING WENT WRONG");
				}
				
			
				
			}
			
			case 7->{
				
				//Get all PolicyHolders
				System.out.println("All PolicyHolders: ");
				List<PolicyHolder> policyHolders = policyHolderService.getAllPolicyHolders();
				for(PolicyHolder ph: policyHolders) {
					System.out.println(policyHolders);
				}
				
			
				
			}
			
			case 8->{
				System.out.println("Insert Address: ");
			
				System.out.println("Enter Street: ");
				String street = sc.nextLine();
				System.out.println("Enter City: ");
				String city = sc.next();
				System.out.println("Enter State: ");
				String state = sc.next();
				
				Address address = new Address(street, city, state);
				boolean result = policyHolderService.addAddress(address);
				if(result) {
					System.out.println("Address Inserted Successfully");
				}
				else {
					System.out.println("Something went wrong");
				}
			}
			
			
			case 0->{
				running = false;
			}
			default->{
				System.out.println("INVALID CHOICE");
			}
			
			
			
		}
			
		}
		sc.close();
		context.close();
		
	}
	

}
