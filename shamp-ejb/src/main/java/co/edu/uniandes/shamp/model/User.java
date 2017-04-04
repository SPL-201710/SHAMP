package co.edu.uniandes.shamp.model;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@javax.persistence.Entity
@NamedQueries({
    @NamedQuery(name = User.FIND_BY_USERNAME,
        query = "select u from User u where u.username = :username"),
    @NamedQuery(name = User.FIND_BY_USERNAME_AND_PASSWORD,
        query = "select u from User u where u.username = :username and u.password = :password"),
    @NamedQuery(name = User.FIND_BY_ID, query = "select u from User u where u.id = :userId"),})
@Table(name = "users")
public class User extends Entity {

  public static final String FIND_BY_ID = PREFIX + "User.findByID";

  public static final String FIND_BY_USERNAME = PREFIX + "User.findByUsername";

  public static final String FIND_BY_USERNAME_AND_PASSWORD =
      PREFIX + "User.findByUsernameAndPassword";



  @Column(name = "user_mail", nullable = false)
  private String email;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", unique = true, nullable = false)
  private Integer id;

  @NotEmpty
  @Column(name = "user_password", nullable = false)
  private String password;

  @NotEmpty
  @Column(name = "user_first_name")
  private String surname;

  @NotEmpty
  @Column(name = "username", unique = true, nullable = false)
  private String username;

  @Column(name = "user_type")
  private Integer userType;

  public String getEmail() {
    return this.email;
  }

  public Integer getId() {
    return this.id;
  }

  public final String getPassword() {
    return this.password;
  }

  public final String getSurname() {
    return this.surname;
  }

  public final String getUsername() {
    return this.username;
  }

  public final Integer getUserType() {
    return this.userType;
  }

  public void setEmail(final String email) {
    this.email = email;
  }


  public void setId(final Integer id) {
    this.id = id;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public void setSurname(final String surname) {
    this.surname = surname;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  public void setUserType(final Integer userType) {
    this.userType = userType;
  }

}
