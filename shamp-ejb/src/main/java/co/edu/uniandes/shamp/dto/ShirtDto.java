package co.edu.uniandes.shamp.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class ShirtDto {

  @JsonProperty
  private String location;

  @JsonProperty
  private int quantity;

  @JsonProperty
  private int shirt_id;

  @JsonProperty
  private String size;

  @JsonProperty
  private int stamp_id;

  public String getLocation() {
    return this.location;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public int getShirt_id() {
    return this.shirt_id;
  }

  public String getSize() {
    return this.size;
  }

  public int getStamp_id() {
    return this.stamp_id;
  }

  public void setLocation(final String location) {
    this.location = location;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void setShirt_id(final int shirt_id) {
    this.shirt_id = shirt_id;
  }

  public void setSize(final String size) {
    this.size = size;
  }

  public void setStamp_id(final int stamp_id) {
    this.stamp_id = stamp_id;
  }


}
