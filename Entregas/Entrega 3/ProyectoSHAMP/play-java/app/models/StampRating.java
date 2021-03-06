package models;

import play.data.validation.*;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;
import com.avaje.ebean.Model.Finder;

import play.libs.Crypto;
import com.fasterxml.jackson.annotation.*;
import java.util.Date;

@Entity
@Table(name="stamp_ratings")
public class StampRating extends Model
{
	@Id
    public long rating_id;
	
	@ManyToOne
    @Column(name="stamp_id")
    public Stamp stamp_id;
	
	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	@ManyToOne
	@Column(name="user_id")
	public User user_id;
	
	@Column(nullable=false)
	public int rating_status;
	
	@Column(nullable=false)
	public int stamp_rating;
	
	@Column(nullable=false)
	public String stamp_comments;
	
	@Column(nullable=false)
	public Date creation_date;
	
	public static Finder<Long,StampRating> find = new Finder<Long,StampRating>(Long.class, StampRating.class);
	
}
