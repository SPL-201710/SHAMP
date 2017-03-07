package co.edu.uniandes.shamp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Session {

    private String token;
    private UserDto user;

    public String getToken() {
        return this.token;
    }

    public UserDto getUser() {
        return this.user;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public void setUser(final UserDto user) {
        this.user = user;
    }

}