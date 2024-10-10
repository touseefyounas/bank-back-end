package com.fdmgroup.Bank.account;


public class AccountDto {
	
	private double balance;
	private double interestRate;
	private String accountType;
	private long customerId;
	
	
	
	public AccountDto() {
	}

	public AccountDto(double balance, double interestRate, String accountType, long customerId) {
		super();
		this.balance = balance;
		this.interestRate = interestRate;
		this.accountType = accountType;
		this.customerId = customerId;
	}
	


	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "AccountDto [balance=" + balance + ", interestRate=" + interestRate + ", accountType=" + accountType + ", customerId=" + customerId + "]";
	}
	
	
}
