package com.fdmgroup.Bank.account;

import com.fdmgroup.Bank.customer.Customer;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class CheckingAccount extends Account {
	
	private int nextCheckNumber;
	
	public CheckingAccount () {
		
	}

	public CheckingAccount(double balance,
			@NotNull(message = "Please associate this Account with a Customer") Customer customer) {
		super(balance, customer);
		this.nextCheckNumber = 1;
		
	}

	public int getNextCheckNumber() {
		int currentCheckNumber = nextCheckNumber;
		nextCheckNumber += 1;
		return currentCheckNumber;
	}

	public void setNextCheckNumber(int nextCheckNumber) {
		this.nextCheckNumber = nextCheckNumber;
	}

	@Override
	public String toString() {
		return "CheckingAccount [nextCheckNumber=" + nextCheckNumber + "]";
	}
	
	
	
}
