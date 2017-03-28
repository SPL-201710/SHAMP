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

@javax.persistence.Entity
@NamedQueries({@NamedQuery(name = Stamp.FIND_ALL,
query = "select e from Stamp e where e.active = true order by e.id desc"),
	@NamedQuery(name = Stamp.FIND_BY_ID, query = "select e from Stamp e where e.active = true and e.id = :stampId order by e.id desc"), })
@Table(name = "stamps")
public class Stamp extends Entity {

	public static final String FIND_ALL = PREFIX + "Stamp.findALL";

	public static final String FIND_BY_ID = PREFIX + "Stamp.findById";

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stamp_id", unique = true, nullable = false)
	private Integer id;

	@NotEmpty
	@Column(name = "stamp_large_image_path", nullable = false)
	private String stampLargeImagePath;

	@NotEmpty
	@Column(name = "stamp_long_description", nullable = false)
	private String stampLongDescription;

	@NotEmpty
	@Column(name = "stamp_name", nullable = false)
	private String stampName;

	@NotEmpty
	@Column(name = "stamp_price", nullable = false)
	private String stampPrice;

	@NotEmpty
	@Column(name = "stamp_short_description", nullable = false)
	private String stampShortDescription;

	@NotEmpty
	@Column(name = "stamp_small_image_path", nullable = false)
	private String stampSmallImagePath;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Category getCategory() {
		return this.category;
	}

	public Integer getId() {
		return this.id;
	}

	public String getStampLargeImagePath() {
		return this.stampLargeImagePath;
	}

	public String getStampLongDescription() {
		return this.stampLongDescription;
	}

	public String getStampName() {
		return this.stampName;
	}

	public String getStampPrice() {
		return this.stampPrice;
	}

	public String getStampShortDescription() {
		return this.stampShortDescription;
	}

	public String getStampSmallImagePath() {
		return this.stampSmallImagePath;
	}

	public User getUser() {
		return this.user;
	}

	public void setCategory(final Category category) {
		this.category = category;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setStampLargeImagePath(final String stampLargeImagePath) {
		this.stampLargeImagePath = stampLargeImagePath;
	}

	public void setStampLongDescription(final String stampLongDescription) {
		this.stampLongDescription = stampLongDescription;
	}

	public void setStampName(final String stampName) {
		this.stampName = stampName;
	}

	public void setStampPrice(final String stampPrice) {
		this.stampPrice = stampPrice;
	}

	public void setStampShortDescription(final String stampShortDescription) {
		this.stampShortDescription = stampShortDescription;
	}

	public void setStampSmallImagePath(final String stampSmallImagePath) {
		this.stampSmallImagePath = stampSmallImagePath;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}