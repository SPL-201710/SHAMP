package co.edu.uniandes.shamp.model;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@javax.persistence.Entity
@NamedQueries({@NamedQuery(name = UserBilling.FIND_BY_ID,
    query = "select u from UserBilling u where u.user.id = :userId"),})
@Table(name = "user_billing")
public class UserBilling extends Entity {

  public static final String FIND_BY_ID = PREFIX + "UserBilling.findByID";

  @Column(name = "billing_status", nullable = false)
  private Integer billingStatus;

  @Column(name = "cvv", nullable = false)
  private String cvv;

  @Column(name = "expiration_date", nullable = false)
  private String expirationDate;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "billing_id", unique = true, nullable = false)
  private Integer id;

  @Column(name = "name_card", nullable = false)
  private String nameCard;

  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "user_address", nullable = false)
  private String userAddress;

  @Column(name = "user_city", nullable = false)
  private String userCity;

  @Column(name = "user_country", nullable = false)
  private String userCountry;

  @Column(name = "user_credit_card", nullable = false)
  private String userCreditCard;

  public Integer getBillingStatus() {
    return this.billingStatus;
  }

  public String getCvv() {
    return this.cvv;
  }

  public String getExpirationDate() {
    return this.expirationDate;
  }

  public Integer getId() {
    return this.id;
  }

  public String getNameCard() {
    return this.nameCard;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public User getUser() {
    return this.user;
  }

  public String getUserAddress() {
    return this.userAddress;
  }

  public String getUserCity() {
    return this.userCity;
  }

  public String getUserCountry() {
    return this.userCountry;
  }

  public String getUserCreditCard() {
    return this.userCreditCard;
  }

  public void setBillingStatus(Integer billingStatus) {
    this.billingStatus = billingStatus;
  }

  public void setCvv(String cvv) {
    this.cvv = cvv;
  }

  public void setExpirationDate(String expirationDate) {
    this.expirationDate = expirationDate;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setNameCard(String nameCard) {
    this.nameCard = nameCard;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setUserAddress(String userAddress) {
    this.userAddress = userAddress;
  }

  public void setUserCity(String userCity) {
    this.userCity = userCity;
  }

  public void setUserCountry(String userCountry) {
    this.userCountry = userCountry;
  }

  public void setUserCreditCard(String userCreditCard) {
    this.userCreditCard = userCreditCard;
  }



}
