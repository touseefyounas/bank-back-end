package com.fdmgroup.Bank.customer;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fdmgroup.Bank.customer.geocodingAPI.GeoCodingApi;

@Service
public class CustomerService {
	
	private CustomerRepository customerRepo;
	
	public CustomerService (CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
	}

	public List<Customer> getCustomers() {
		return customerRepo.findAll();
	}

	public Customer getCustomerById(long id) {
		return customerRepo.findById(id).orElseThrow(()-> new CustomerNotFoundException("Customer with id " + id + " does not exist."));
	}

	public Customer createCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	public void deleteCustomerById(long id) {
		customerRepo.delete(customerRepo.findById(id).orElseThrow(()-> new CustomerNotFoundException("Customer with id " + id + " does not exist.")));
	}

	public Customer updateCustomer(long id, CustomerUpdateDTO customerUpdateDTO) {
		Optional<Customer> customerOpt = customerRepo.findById(id);
		
		if (customerOpt.isPresent()) {
			Customer customer = customerOpt.orElseThrow(()-> new CustomerNotFoundException("Customer with id "+ id + " does not exist."));
			customer.setName(customerUpdateDTO.getName());
			customer.getAddress().setCity(customerUpdateDTO.getCity());
			customer.getAddress().setProvince(customerUpdateDTO.getProvince());
			customer.getAddress().setPostalCode(customerUpdateDTO.getPostalCode());
			
			return customerRepo.save(customer);
		}
		
		return null;
		
	}

	public Customer createCustomerWithNameAndPostalCode(CustomerCreateDTO customerCreateDto) {
		Customer customer = null;
		
		Map<String, String> geoInfo = GeoCodingApi.getLocationWebClient(customerCreateDto.getPostalCode());
		
		System.out.println(geoInfo);
		
		String city = geoInfo.get("city");
		String province = geoInfo.get("province");
		
		if (city.isEmpty() ||  province.isEmpty()) {
			throw new AddressNotFoundException("Invalid Postal code");
		}
		
		Address address = new Address(customerCreateDto.getStreetNumber(), customerCreateDto.getPostalCode(), city, province);
		if (customerCreateDto.getCustomerType().equalsIgnoreCase("person")) {
			customer = new Person(customerCreateDto.getName(), address);
		} else if (customerCreateDto.getCustomerType().equalsIgnoreCase("company")) {
			customer = new Company(customerCreateDto.getName(), address);
		}
		return customerRepo.save(customer);
		
	}
	
	
}
