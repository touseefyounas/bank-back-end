package com.fdmgroup.Bank.account;

import java.net.URI;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.Bank.customer.Customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {
	
	private AccountService accServ;
	
	public AccountController(AccountService accServ) {
		this.accServ = accServ;
	}
	
	@Operation(summary="Fetches all the stored Accounts")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode="404")	
	})
	@GetMapping
	public ResponseEntity<List<Account>> getAllAccounts(){
		return ResponseEntity.ok(accServ.getAllAccounts());
	}
	
	@Operation(summary="Fetches all the savings accounts")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode="404")	
	})
	@GetMapping("savings")
	public ResponseEntity<List<SavingsAccount>> getAllSavingsAccounts(){
		return ResponseEntity.ok(accServ.getAllSavingsAccounts());
	}
	
	@Operation(summary="Fetches all the checking accounts")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode="404")	
	})
	@GetMapping("checking")
	public ResponseEntity<List<CheckingAccount>> getAllCheckingAccounts(){
		return ResponseEntity.ok(accServ.getAllCheckingAccounts());
	}
	
	@Operation(summary="Fetches account by account Id")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode="404")	
	})
	@GetMapping("/{id}")
	public Account getAccountById(@PathVariable long id) {
		return accServ.getAccountById(id);
	}
	
	@Operation(summary="Creates an account for a customer with customer Id")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201", content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode="404")	
	})
	@PostMapping
	public ResponseEntity<Account> createAccount(@RequestBody AccountDto accountDto){
		Account newAccount = accServ.createAccount(accountDto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newAccount.getAccountId()).toUri();
		return ResponseEntity.created(location).body(newAccount);
	}
	
	@Operation(summary="Deletes an account with account Id")
	@DeleteMapping("/{id}")
	public void deleteAccount(@PathVariable long id) {
		accServ.deleteCustomerById(id);
	}
	
	@Operation(summary="Fetches all the accounts with 'city' query parameter")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode="404")	
	})
	@GetMapping("/find-accounts")
	public ResponseEntity<List<Account>> getAccountsByCityName(@RequestParam String city){
		return ResponseEntity.ok(accServ.getAccountsByCityName(city));
	}
	
	@Operation(summary="Updates the interest rate of an Account by Account Id")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode="404")	
	})
	@PatchMapping("/{id}/interest")
	public ResponseEntity<SavingsAccount> setInterestRate(@PathVariable long id, @RequestParam double interestRate){
		return ResponseEntity.ok(accServ.setInterestRate(id, interestRate));
	}
	
	@Operation(summary="Updates the balance of an Account by Account Id")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode="404")	
	})
	@PatchMapping("/{id}/balance")
	public ResponseEntity<Account> setBalance(@PathVariable long id, @RequestParam double balance){
		return ResponseEntity.ok(accServ.setBalance(id, balance));
	}
	
	@GetMapping("/{id}/find-customer")
	public ResponseEntity<Customer> getCustomerbyAccountId(@PathVariable long id){
		return ResponseEntity.ok(accServ.getCustomerById(id));
	}
	
}
