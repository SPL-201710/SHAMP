package models;


import play.data.validation.*;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;
import play.libs.Crypto;
import com.fasterxml.jackson.annotation.*;
import java.util.Date;

@Entity
@Table(name="stamps")
public class Stamp extends Model 
{
    @Id
    public long stamp_id;
    
    @ManyToOne
    @Column(name="user_id")
    public User user_id;
    
    @ManyToOne
    public Categories category_id;
    
    
    public int stamp_status;

    @Constraints.MaxLength(value=20,message="validation.maxLength")
    @Column(nullable=false, length=20)
    public String stamp_name;
    
    @Constraints.MaxLength(value=50,message="validation.maxLength")
    @Column(nullable=false, length=50)
    public String stamp_short_description;
    
    @Constraints.MaxLength(value=255,message="validation.maxLength")
    @Column(nullable=false, length=255)
    public String stamp_long_description;
    
    @Column(nullable=false)
    public double stamp_price;
    
    @Constraints.MaxLength(value=255,message="validation.maxLength")
    @Column(length=255)
    public String stamp_small_image_path;
    
    @Constraints.MaxLength(value=255,message="validation.maxLength")
    @Column(length=255)
    public String stamp_large_image_path;
    
    public double stamp_rating;
    
    @Constraints.MaxLength(value=255,message="validation.maxLength")
    @Column(length=255)
    public String stamp_blackwhite;
    
    @Constraints.MaxLength(value=255,message="validation.maxLength")
    @Column(length=255)
    public String stamp_negative;
    
    @Constraints.MaxLength(value=255,message="validation.maxLength")
    @Column(length=255)
    public String stamp_sephia;
    
    @Constraints.MaxLength(value=255,message="validation.maxLength")
    @Column(length=255)
    public String stamp_blur;
    
    public String stamp_buyer;
    
    public String name;
    
    public boolean active;
    
    public Date creation_date;
    
    public void updateRating(double rating)
    {
    	stamp_rating = rating;
    }
    
    
    
    public String getStamp_small_image_path() {
		return stamp_small_image_path;
	}



	public void setStamp_small_image_path(String stamp_small_image_path) {
		this.stamp_small_image_path = stamp_small_image_path;
	}



	public String getStamp_large_image_path() {
		return stamp_large_image_path;
	}



	public void setStamp_large_image_path(String stamp_large_image_path) {
		this.stamp_large_image_path = stamp_large_image_path;
	}



	public String getStamp_blackwhite() {
		return stamp_blackwhite;
	}



	public void setStamp_blackwhite(String stamp_blackwhite) {
		this.stamp_blackwhite = stamp_blackwhite;
	}



	public String getStamp_negative() {
		return stamp_negative;
	}



	public void setStamp_negative(String stamp_negative) {
		this.stamp_negative = stamp_negative;
	}



	public long getStamp_id() {
		return stamp_id;
	}

	public void setStamp_id(long stamp_id) {
		this.stamp_id = stamp_id;
	}

	public static Finder<Long,Stamp> find = new Finder<Long,Stamp>(Long.class, Stamp.class);
    
    
}