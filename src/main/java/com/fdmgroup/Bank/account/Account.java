package com.fdmgroup.Bank.account;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fdmgroup.Bank.customer.Customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACCOUNT_ID_GEN")
	@SequenceGenerator(name="ACCOUNT_ID_GEN", sequenceName="ACCOUNT_ID_SEQ")
	private long accountId;
	private double balance;
	
	@NotNull(message="Please associate this Account with a Customer")
	@ManyToOne
	@JoinColumn(name="FK_CUST_ID")
	@JsonBackReference
	private Customer customer;

	public Account() {
		
	}

	public Account(double balance,
			@NotNull(message = "Please associate this Account with a Customer") Customer customer) {
		
		this.balance = balance;
		this.customer = customer;
	}

	public Account(long accountId, double balance,
			@NotNull(message = "Please associate this Account with a Customer") Customer customer) {
		
		this.accountId = accountId;
		this.balance = balance;
		this.customer = customer;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + ", customer=" + customer + "]";
	}
	
}
