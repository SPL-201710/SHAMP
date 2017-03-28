package co.edu.uniandes.shamp.dto;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import co.edu.uniandes.shamp.model.Shirt;

@JsonInclude(Include.NON_NULL)
public class OrderDto {

  private String quantity;
  private String shirtColor;
  private List<Shirt> shirts;

  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getShirtColor() {
    return this.shirtColor;
  }

  public String getShirtSex() {
    return this.shirtSex;
  }

  public String getShirtSize() {
    return this.shirtSize;
  }

  public List<String> getSize() {
    return this.size;
  }

  public String getUserId() {
    return this.userId;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setShirtColor(String shirtColor) {
    this.shirtColor = shirtColor;
  }

  public void setShirtSex(String shirtSex) {
    this.shirtSex = shirtSex;
  }

  public void setShirtSize(String shirtSize) {
    this.shirtSize = shirtSize;
  }

  public void setSize(List<String> size) {
    this.size = size;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
