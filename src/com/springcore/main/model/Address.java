package com.springcore.main.model;

import org.springframework.stereotype.Component;

@Component
public class Address {
	
	private int id;
	private String street;
	private String city;
	private String state;
	
	public Address() {
		
	}
	
	public Address(int id, String street, String city, String state) {
		this.id = id;
		this.street = street;
		this.city = city;
		this.state = state;
	}
	
	public Address(String street, String city, String state) {
		this.street = street;
		this.city = city;
		this.state = state;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", city=" + city + ", state=" + state + "]";
	}
	
	
	
	

}
