package co.edu.uniandes.shamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DetailOrderDto {

	@JsonProperty
	private String delivery_address;

	@JsonProperty
	private String contact_phone;

	@JsonProperty
	private String city;

	@JsonProperty
	private String country;

	public String getDelivery_address() {
		return this.delivery_address;
	}

	public void setDelivery_address(final String delivery_address) {
		this.delivery_address = delivery_address;
	}

	public String getContact_phone() {
		return this.contact_phone;
	}

	public void setContact_phone(final String contact_phone) {
		this.contact_phone = contact_phone;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

}
