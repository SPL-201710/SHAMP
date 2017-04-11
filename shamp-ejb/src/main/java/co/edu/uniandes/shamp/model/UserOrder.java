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

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@javax.persistence.Entity
@NamedQueries({@NamedQuery(name = UserOrder.FIND_BY_USER_ID,
    query = "select u from UserOrder u where u.user.id = :user_id")})
@Table(name = "user_orders")
public class UserOrder extends Entity {

  public static final String FIND_BY_USER_ID = PREFIX + "UserOrder.findByUserID";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id", unique = true, nullable = false)
  private Integer id;

  @NotEmpty
  @Column(name = "order_status", nullable = false)
  private String orderStatus;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Integer getId() {
    return this.id;
  }


  public String getOrderStatus() {
    return this.orderStatus;
  }

  public User getUser() {
    return this.user;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public void setOrderStatus(String orderStatus) {
    this.orderStatus = orderStatus;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
