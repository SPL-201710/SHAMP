package co.edu.uniandes.shamp.model;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@javax.persistence.Entity
@Table(name = "stamp_categories")
public class Category extends Entity {

  @NotEmpty
  @Column(name = "category_description_url", nullable = false)
  private String categoryDescriptionUrl;

  @NotEmpty
  @Column(name = "category_name", nullable = false)
  private String categoryName;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id", unique = true, nullable = false)
  private Integer id;



}
