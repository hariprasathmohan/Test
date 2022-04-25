//$Id$
package com.bank.models;

public class CustomerModel {
	private String name;
	private Address address;
	private String mobile;
	private String email;
	public CustomerModel(String name, Address address, String mobile, String email) {
		this.name = name;
		this.address = address;
		this.mobile = mobile;
		this.email = email;
	}
	public CustomerModel() {}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address.toString();
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
