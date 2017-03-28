package co.edu.uniandes.shamp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@javax.persistence.Entity
@Table(name = "stamp_shirt")
public class StampShirt extends Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shamp_shirt_id", unique = true, nullable = false)
	private Integer id;

	@NotEmpty
	@Column(name = "stamp_size", nullable = false)
	private String stampSize;

	@NotEmpty
	@Column(name = "stamp_location", nullable = false)
	private String stampLocation;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "stamp_id")
	private Stamp stamp;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "shirt_id")
	private Shirt shirt;

	public String getStampSize() {
		return this.stampSize;
	}

	public void setStampSize(final String stampSize) {
		this.stampSize = stampSize;
	}

	public String getStampLocation() {
		return this.stampLocation;
	}

	public void setStampLocation(final String stampLocation) {
		this.stampLocation = stampLocation;
	}

	public Stamp getStamp() {
		return this.stamp;
	}

	public void setStamp(final Stamp stamp) {
		this.stamp = stamp;
	}

	public Shirt getShirt() {
		return this.shirt;
	}

	public void setShirt(final Shirt shirt) {
		this.shirt = shirt;
	}

}
