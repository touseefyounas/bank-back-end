package com.fdmgroup.Bank.customer;


public class CustomerCreateDTO {
	
	private String name;
	private String streetNumber;
	private String postalCode;
	private String customerType;
	
	
	public CustomerCreateDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	@Override
	public String toString() {
		return "CustomerCreateDTO [name=" + name + ", streetNumber=" + streetNumber + ", postalCode=" + postalCode
				+ ", customerType=" + customerType + "]";
	}
	
	
	
}
