package com.fdmgroup.Bank.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fdmgroup.Bank.account.Account;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Customer_Type",discriminatorType = DiscriminatorType.STRING)
public abstract class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUSTOMER_ID_GEN")
	@SequenceGenerator(name="CUSTOMER_ID_GEN", sequenceName="CUSTOMER_ID_SEQ")
	private long customerId;
	@NotBlank(message="Name must not be blank")
	private String name;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="FK_ADDRESS_ID")
	private Address address;
	
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<Account> accounts = new ArrayList<>();
	
	public Customer() {
		
	}


	public Customer(long customerId, @NotBlank(message = "Name must not be blank") String name, Address address) {
		
		this.customerId = customerId;
		this.name = name;
		this.address = address;
	}


	public Customer(@NotBlank(message = "Name must not be blank") String name, Address address) {
		
		this.name = name;
		this.address = address;
	}


	public long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Account> getAccounts() {
		return accounts;
	}


	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public void addAccount(Account account) {
		this.accounts.add(account);
	}
	
	public void removeAccount(Account account) {
		this.accounts.remove(account);
	}


	@Override
	public int hashCode() {
		return Objects.hash(accounts, address, customerId, name);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(accounts, other.accounts) && Objects.equals(address, other.address)
				&& customerId == other.customerId && Objects.equals(name, other.name);
	}


	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", address=" + address + ", accounts="
				+ accounts + "]";
	}
	
	
	
}
