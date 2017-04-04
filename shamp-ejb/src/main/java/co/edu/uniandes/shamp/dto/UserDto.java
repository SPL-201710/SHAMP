package co.edu.uniandes.shamp.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class UserDto {

  private String email;
  private int id;
  private String name;
  private String surname;
  private String username;

  public String getEmail() {
    return this.email;
  }

  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getSurname() {
    return this.surname;
  }

  public String getUsername() {
    return this.username;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setSurname(final String surname) {
    this.surname = surname;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

}
