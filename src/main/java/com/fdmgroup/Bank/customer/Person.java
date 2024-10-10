package com.fdmgroup.Bank.customer;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
@DiscriminatorValue(value="Person")
public class Person extends Customer {

	public Person() {
	}

	public Person(@NotBlank(message = "Name must not be blank") String name, Address address) {
		super(name, address);
	}
	
	
	
}
