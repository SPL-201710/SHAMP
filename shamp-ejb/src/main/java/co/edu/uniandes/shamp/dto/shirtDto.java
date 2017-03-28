package co.edu.uniandes.shamp.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class shirtDto {

	@JsonProperty
	private int stamp_id;

	@JsonProperty
	private int shirt_id;

	@JsonProperty
	private int quantity;

	@JsonProperty
	private String size;

	@JsonProperty
	private String location;

	public int getStamp_id() {
		return this.stamp_id;
	}

	public void setStamp_id(final int stamp_id) {
		this.stamp_id = stamp_id;
	}

	public int getShirt_id() {
		return this.shirt_id;
	}

	public void setShirt_id(final int shirt_id) {
		this.shirt_id = shirt_id;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(final int quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return this.size;
	}

	public void setSize(final String size) {
		this.size = size;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(final String location) {
		this.location = location;
	}
}
