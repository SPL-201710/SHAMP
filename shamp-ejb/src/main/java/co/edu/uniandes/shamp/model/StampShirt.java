package co.edu.uniandes.shamp.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;


@javax.persistence.Entity
@Table(name = "stamp_shirt")
public class StampShirt extends Entity {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "shirt_id", unique = true, nullable = false)
  private Integer id;

  @NotEmpty
  @Column(name = "shirt_color", nullable = false)
  private String shirtColor;

  @NotEmpty
  @Column(name = "shirt_large_image_path", nullable = false)
  private String shirtLargeImagePath;

  @NotEmpty
  @Column(name = "shirt_sex", nullable = false)
  private String shirtSex;

  @NotEmpty
  @Column(name = "shirt_size", nullable = false)
  private String shirtSize;

  @NotEmpty
  @Column(name = "shirt_small_image_path", nullable = false)
  private String shirtSmallImagePath;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Integer getId() {
    return this.id;
  }

  public String getShirtColor() {
    return this.shirtColor;
  }

  public String getShirtLargeImagePath() {
    return this.shirtLargeImagePath;
  }

  public String getShirtSex() {
    return this.shirtSex;
  }

  public String getShirtSize() {
    return this.shirtSize;
  }

  public String getShirtSmallImagePath() {
    return this.shirtSmallImagePath;
  }

  public User getUser() {
    return this.user;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setShirtColor(String shirtColor) {
    this.shirtColor = shirtColor;
  }

  public void setShirtLargeImagePath(String shirtLargeImagePath) {
    this.shirtLargeImagePath = shirtLargeImagePath;
  }

  public void setShirtSex(String shirtSex) {
    this.shirtSex = shirtSex;
  }

  public void setShirtSize(String shirtSize) {
    this.shirtSize = shirtSize;
  }

  public void setShirtSmallImagePath(String shirtSmallImagePath) {
    this.shirtSmallImagePath = shirtSmallImagePath;
  }

  public void setUser(User user) {
    this.user = user;
  }
}

