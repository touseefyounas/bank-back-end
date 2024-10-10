package com.fdmgroup.Bank.customer;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerUpdateDTO {
	
	@NotBlank(message="Name must not be blank")
	private String name;
	@NotBlank(message="City must not be blank")
	private String city;
	@NotBlank(message="Province must not be blank")
	private String province;
	@NotBlank(message="Postal Code must not be blank")
	@Size(message="Postal code must not exceed 7 characters")
	private String postalCode;
	
	public CustomerUpdateDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, name, postalCode, province);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerUpdateDTO other = (CustomerUpdateDTO) obj;
		return Objects.equals(city, other.city) && Objects.equals(name, other.name)
				&& Objects.equals(postalCode, other.postalCode) && Objects.equals(province, other.province);
	}

	@Override
	public String toString() {
		return "CustomerDTO [name=" + name + ", city=" + city + ", province=" + province + ", postalCode=" + postalCode
				+ "]";
	}
	
	
	
}
