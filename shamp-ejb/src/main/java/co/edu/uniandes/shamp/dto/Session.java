package co.edu.uniandes.shamp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import co.edu.uniandes.shamp.model.UserBilling;

@JsonInclude(Include.NON_NULL)
public class Session {

  private String token;

  private UserDto user;

  private UserBilling userBilling;

  public String getToken() {
    return this.token;
  }

  public UserDto getUser() {
    return this.user;
  }

  public UserBilling getUserBilling() {
    return this.userBilling;
  }

  public void setToken(final String token) {
    this.token = token;
  }

  public void setUser(final UserDto user) {
    this.user = user;
  }

  public void setUserBilling(UserBilling userBilling) {
    this.userBilling = userBilling;
  }



}
