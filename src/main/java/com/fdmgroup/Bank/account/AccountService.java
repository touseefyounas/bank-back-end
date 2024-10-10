package com.fdmgroup.Bank.account;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fdmgroup.Bank.customer.Customer;
import com.fdmgroup.Bank.customer.CustomerNotFoundException;
import com.fdmgroup.Bank.customer.CustomerService;

@Service
public class AccountService {
	
	private CustomerService customerServ;
	private AccountRepository accRepo;
	
	public AccountService(AccountRepository accRepo, CustomerService customerServ) {
		this.accRepo = accRepo;
		this.customerServ = customerServ;
	}

	public List<Account> getAllAccounts() {
		return accRepo.findAllAccounts();
	}
	
	public List<CheckingAccount> getAllCheckingAccounts(){
		return accRepo.findAllCheckingAccounts();
	}
	
	public List<SavingsAccount> getAllSavingsAccounts(){
		return accRepo.findAllSavingsAccounts();
	}
	

	public Account createAccount(AccountDto accountDto) {
		Account account = null;
		
		System.out.println(accountDto);
		System.out.println("Customer ID: " + accountDto.getCustomerId());
		System.out.println("Balance: " + accountDto.getBalance());
		
		Customer customer = customerServ.getCustomerById(accountDto.getCustomerId());
		
		if (customer == null) {
			throw new CustomerNotFoundException("Customer with id " + accountDto.getCustomerId()+" does not exist");
		}
		
		if (accountDto.getAccountType().equalsIgnoreCase("savings")) {
			account = new SavingsAccount(accountDto.getBalance(), accountDto.getInterestRate(), customer);
			customer.addAccount(account);
		} else if (accountDto.getAccountType().equalsIgnoreCase("checking")) {
			account = new CheckingAccount(accountDto.getBalance(), customer);
			customer.addAccount(account);
		}
		return accRepo.save(account);
	}

	public Account getAccountById(long id) {
		return accRepo.findById(id).orElseThrow(()-> new AccountNotFoundException("Account with id "+id+" does not exist."));
	}

	public void deleteCustomerById(long id) {
		accRepo.delete(getAccountById(id));
		
	}

	public List<Account> getAccountsByCityName(String city) {
		return accRepo.findAccountsByCityName(city);
	}

}
