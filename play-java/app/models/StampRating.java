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
	
	@Column(nullable=false)
	public User user_rating;
	
	@Column(nullable=false)
	public int rating_status;
	
	@Column(nullable=false)
	public int stamp_rating;
	
	@Column(nullable=false)
	public int stamp_comments;
	
	@Column(nullable=false)
	public Date creation_date;
	
	public static Finder<Long,StampRating> find = new Finder<Long,StampRating>(Long.class, StampRating.class);
	
}
