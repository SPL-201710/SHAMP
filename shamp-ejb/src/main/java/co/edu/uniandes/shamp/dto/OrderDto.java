package co.edu.uniandes.shamp.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(Include.NON_NULL)
public class OrderDto {
	@JsonProperty
	private String user_id;
	@JsonProperty
	private DetailOrderDto order;
	@JsonProperty
	private List<shirtDto> products;

	public String getUser_id() {
		return this.user_id;
	}

	public void setUser_id(final String user_id) {
		this.user_id = user_id;
	}

	public DetailOrderDto getOrder() {
		return this.order;
	}

	public void setOrder(final DetailOrderDto order) {
		this.order = order;
	}

	public List<shirtDto> getProducts() {
		return this.products;
	}

	public void setProducts(final List<shirtDto> products) {
		this.products = products;
	}

}
