package com.fdmgroup.Bank.customer;

import java.net.URI;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
	private CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@Operation(summary="Fetches all the stored Personal customers")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", content= @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode="404")
	})
	@GetMapping
	public List<Customer> getPersonalCustomers(){
		return customerService.getCustomers();
	}
	
	@Operation(summary = "Fetches a Customer by Id")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode="404")
	})
	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable long id) {
		return customerService.getCustomerById(id);
	}
	
	@Operation(summary = "Updates a Customer by Id")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode="404")
	})
	@PatchMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable long id, @RequestBody CustomerUpdateDTO customerUpdateDTO){
		Customer updatedCustomer = customerService.updateCustomer(id, customerUpdateDTO);
		return ResponseEntity.status(200).body(updatedCustomer);
	}
	
	@Operation(summary = "Deletes a Customer by Id")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode="404")
	})
	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable long id) {
		customerService.deleteCustomerById(id);
	}
	
	@Operation(summary="Creates a new Customer")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201", content= @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
	})
	@PostMapping
	public ResponseEntity<Customer> createCustomerWithNameAndPostalCode(@RequestBody CustomerCreateDTO customerCreateDto){
		Customer newCustomer = customerService.createCustomerWithNameAndPostalCode(customerCreateDto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newCustomer.getCustomerId()).toUri();
		return ResponseEntity.created(location).body(newCustomer);
		
	}
}
