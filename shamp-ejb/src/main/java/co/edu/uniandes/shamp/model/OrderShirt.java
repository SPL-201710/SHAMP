package co.edu.uniandes.shamp.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@javax.persistence.Entity
@Table(name = "order_shirt")
public class OrderShirt extends Entity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_shirt_id", unique = true, nullable = false)
  private Integer id;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "order_id")
  private UserOrder order;


  @Column(name = "shirt_quantity")
  private Double shirtQuantity;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "shirt_id")
  private StampShirt stampShirt;

  public Integer getId() {
    return this.id;
  }

  public UserOrder getOrder() {
    return this.order;
  }

  public Double getShirtQuantity() {
    return this.shirtQuantity;
  }

  public StampShirt getStampShirt() {
    return this.stampShirt;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setOrder(UserOrder order) {
    this.order = order;
  }

  public void setShirtQuantity(Double shirtQuantity) {
    this.shirtQuantity = shirtQuantity;
  }

  public void setStampShirt(StampShirt stampShirt) {
    this.stampShirt = stampShirt;
  }

}
