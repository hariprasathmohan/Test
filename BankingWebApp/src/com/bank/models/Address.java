//$Id$
package com.bank.models;

public class Address {
	private String line1;
	private String line2;
	private String city;
	private String state;
	public Address(String line1, String line2, String city, String state) {
		super();
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.state = state;
	}
	@Override
	public String toString() {
		return  line1 +"," + line2 + "," + city + ","+ state + ".";
	}
	
	
}
