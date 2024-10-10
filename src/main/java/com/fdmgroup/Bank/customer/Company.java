package com.fdmgroup.Bank.customer;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
@DiscriminatorValue(value="Company")
public class Company extends Customer {

	public Company() {
		super();
	}

	public Company(@NotBlank(message = "Name must not be blank") String name, Address address) {
		super(name, address);
	}
	
}
