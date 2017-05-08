package dto;

import models.*;


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
