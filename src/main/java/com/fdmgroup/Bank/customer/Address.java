package com.fdmgroup.Bank.customer;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADDRESS_ID_GEN")
	@SequenceGenerator(name="ADDRESS_ID_GEN", sequenceName="ADDRESS_ID_SEQ")
	private long addressId;
	@NotBlank(message="Street Number must not be blank")
	private String streetNumber;
	@NotBlank(message="Postal Code must not be blank")
	@Size(message="Postal must not exceed 7 characters")
	private String postalCode;
	@NotBlank(message="City must not be blank")
	private String city;
	@NotBlank(message="Provnice not be blank")
	private String province;
	
	public Address() {
	
	}

	public Address(long addressId, @NotBlank(message = "Street Number must not be blank") String streetNumber,
			@NotBlank(message = "Postal Code must not be blank") @Size(message = "Postal must not exceed 7 characters") String postalCode,
			@NotBlank(message = "City must not be blank") String city,
			@NotBlank(message = "Provnice not be blank") String province) {
		
		this.addressId = addressId;
		this.streetNumber = streetNumber;
		this.postalCode = postalCode;
		this.city = city;
		this.province = province;
	}

	public Address(@NotBlank(message = "Street Number must not be blank") String streetNumber,
			@NotBlank(message = "Postal Code must not be blank") @Size(message = "Postal must not exceed 7 characters") String postalCode,
			@NotBlank(message = "City must not be blank") String city,
			@NotBlank(message = "Provnice not be blank") String province) {
	
		this.streetNumber = streetNumber;
		this.postalCode = postalCode;
		this.city = city;
		this.province = province;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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

	@Override
	public int hashCode() {
		return Objects.hash(addressId, city, postalCode, province, streetNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return addressId == other.addressId && Objects.equals(city, other.city)
				&& Objects.equals(postalCode, other.postalCode) && Objects.equals(province, other.province)
				&& Objects.equals(streetNumber, other.streetNumber);
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", streetNumber=" + streetNumber + ", postalCode=" + postalCode
				+ ", city=" + city + ", province=" + province + "]";
	}
	
}
