package co.edu.uniandes.shamp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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


@javax.persistence.Entity
@NamedQueries({@NamedQuery(name = Shirt.FIND_ALL,
		query = "select e from Shirt e where e.active = true order by e.id desc"),
		@NamedQuery(name = Shirt.FIND_BY_ID, query = "select e from Shirt e where e.active = true and e.id = :shirtId order by e.id desc"), })
@Table(name = "shirt")
public class Shirt extends Entity {

	public static final String FIND_ALL = PREFIX + "Shirt.findALL";

	public static final String FIND_BY_ID = PREFIX + "Shirt.findById";

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

	@NotEmpty
	@Column(name = "shirt_price", nullable = false)
	private String shirtPrice;

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

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setShirtColor(final String shirtColor) {
		this.shirtColor = shirtColor;
	}

	public void setShirtLargeImagePath(final String shirtLargeImagePath) {
		this.shirtLargeImagePath = shirtLargeImagePath;
	}

	public void setShirtSex(final String shirtSex) {
		this.shirtSex = shirtSex;
	}

	public void setShirtSize(final String shirtSize) {
		this.shirtSize = shirtSize;
	}

	public void setShirtSmallImagePath(final String shirtSmallImagePath) {
		this.shirtSmallImagePath = shirtSmallImagePath;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public String getShirtPrice() {
		return this.shirtPrice;
	}

	public void setShirtPrice(final String shirtPrice) {
		this.shirtPrice = shirtPrice;
	}


}

