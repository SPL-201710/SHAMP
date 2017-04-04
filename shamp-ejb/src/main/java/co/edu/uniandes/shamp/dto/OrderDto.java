package co.edu.uniandes.shamp.dto;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class OrderDto {
  @JsonProperty
  private DetailOrderDto order;
  @JsonProperty
  private List<ShirtDto> products;
  @JsonProperty
  private Integer user_id;



  public DetailOrderDto getOrder() {
    return this.order;
  }

  public List<ShirtDto> getProducts() {
    return this.products;
  }

  public Integer getUser_id() {
    return this.user_id;
  }

  public void setOrder(final DetailOrderDto order) {
    this.order = order;
  }

  public void setProducts(final List<ShirtDto> products) {
    this.products = products;
  }

  public void setUser_id(Integer user_id) {
    this.user_id = user_id;
  }

}
