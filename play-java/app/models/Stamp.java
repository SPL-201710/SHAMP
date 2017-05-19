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
    
    public double stamp_raiting;
    
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
    
    public String name;
    
    public boolean active;
    
    public Date creation_date;
    
    
    public static Finder<Long,Stamp> find = new Finder<Long,Stamp>(Long.class, Stamp.class);


	public long getStamp_id() {
		return stamp_id;
	}


	public void setStamp_id(long stamp_id) {
		this.stamp_id = stamp_id;
	}


	public User getUser_id() {
		return user_id;
	}


	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}


	public Categories getCategory_id() {
		return category_id;
	}


	public void setCategory_id(Categories category_id) {
		this.category_id = category_id;
	}


	public int getStamp_status() {
		return stamp_status;
	}


	public void setStamp_status(int stamp_status) {
		this.stamp_status = stamp_status;
	}


	public String getStamp_name() {
		return stamp_name;
	}


	public void setStamp_name(String stamp_name) {
		this.stamp_name = stamp_name;
	}


	public String getStamp_short_description() {
		return stamp_short_description;
	}


	public void setStamp_short_description(String stamp_short_description) {
		this.stamp_short_description = stamp_short_description;
	}


	public String getStamp_long_description() {
		return stamp_long_description;
	}


	public void setStamp_long_description(String stamp_long_description) {
		this.stamp_long_description = stamp_long_description;
	}


	public double getStamp_price() {
		return stamp_price;
	}


	public void setStamp_price(double stamp_price) {
		this.stamp_price = stamp_price;
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


	public double getStamp_raiting() {
		return stamp_raiting;
	}


	public void setStamp_raiting(double stamp_raiting) {
		this.stamp_raiting = stamp_raiting;
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


	public String getStamp_sephia() {
		return stamp_sephia;
	}


	public void setStamp_sephia(String stamp_sephia) {
		this.stamp_sephia = stamp_sephia;
	}


	public String getStamp_blur() {
		return stamp_blur;
	}


	public void setStamp_blur(String stamp_blur) {
		this.stamp_blur = stamp_blur;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public Date getCreation_date() {
		return creation_date;
	}


	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
    
    
    
    
}