package co.edu.uniandes.shamp.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class CustomerDto {

  @JsonProperty
  private String city;

  @JsonProperty
  private String country;

  @JsonProperty
  private String cvv;

  @JsonProperty
  private String email;

  @JsonProperty
  private String expiration_date;

  @JsonProperty
  private String name_card;

  @JsonProperty
  private String password;

  @JsonProperty
  private String phone_number;

  @JsonProperty
  private String surname;

  @JsonProperty
  private String user_address;

  @JsonProperty
  private String user_credit_card;

  @JsonProperty
  private String username;

  public String getCity() {
    return this.city;
  }

  public String getCountry() {
    return this.country;
  }

  public String getCvv() {
    return this.cvv;
  }

  public String getEmail() {
    return this.email;
  }

  public String getExpiration_date() {
    return this.expiration_date;
  }

  public String getName_card() {
    return this.name_card;
  }

  public String getPassword() {
    return this.password;
  }

  public String getPhone_number() {
    return this.phone_number;
  }

  public String getSurname() {
    return this.surname;
  }

  public String getUser_address() {
    return this.user_address;
  }

  public String getUser_credit_card() {
    return this.user_credit_card;
  }

  public String getUsername() {
    return this.username;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setCvv(String cvv) {
    this.cvv = cvv;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setExpiration_date(String expiration_date) {
    this.expiration_date = expiration_date;
  }

  public void setName_card(String name_card) {
    this.name_card = name_card;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setPhone_number(String phone_number) {
    this.phone_number = phone_number;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public void setUser_address(String user_address) {
    this.user_address = user_address;
  }

  public void setUser_credit_card(String user_credit_card) {
    this.user_credit_card = user_credit_card;
  }

  public void setUsername(String username) {
    this.username = username;
  }



}
