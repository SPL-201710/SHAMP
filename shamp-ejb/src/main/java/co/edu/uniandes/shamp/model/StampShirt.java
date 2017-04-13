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
@NamedQueries({@NamedQuery(name = StampShirt.FIND_BY_ID,
    query = "select u from StampShirt u where u.id = :id")})
@Table(name = "stamp_shirt")
public class StampShirt extends Entity {

  public static final String FIND_BY_ID = PREFIX + "UserOrder.findByID";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "stamp_shirt_id", unique = true, nullable = false)
  private Integer id;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "shirt_id")
  private Shirt shirt;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "stamp_id")
  private Stamp stamp;

  @NotEmpty
  @Column(name = "stamp_location", nullable = false)
  private String stampLocation;

  @NotEmpty
  @Column(name = "stamp_size", nullable = false)
  private String stampSize;

  public Integer getId() {
    return this.id;
  }

  public Shirt getShirt() {
    return this.shirt;
  }

  public Stamp getStamp() {
    return this.stamp;
  }

  public String getStampLocation() {
    return this.stampLocation;
  }

  public String getStampSize() {
    return this.stampSize;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setShirt(final Shirt shirt) {
    this.shirt = shirt;
  }

  public void setStamp(final Stamp stamp) {
    this.stamp = stamp;
  }

  public void setStampLocation(final String stampLocation) {
    this.stampLocation = stampLocation;
  }

  public void setStampSize(final String stampSize) {
    this.stampSize = stampSize;
  }



}
