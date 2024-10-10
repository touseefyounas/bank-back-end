package com.fdmgroup.Bank.account;

import com.fdmgroup.Bank.customer.Customer;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class SavingsAccount extends Account {
	
	private double interestRate;

	public SavingsAccount() {
		super();
	}

	public SavingsAccount(double balance, double interestRate,
			@NotNull(message = "Please associate this Account with a Customer") Customer customer) {
		super(balance, customer);
		this.interestRate=interestRate;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	@Override
	public String toString() {
		return "SavingsAccount [interestRate=" + interestRate + "]";
	}
	
}
