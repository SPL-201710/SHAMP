package dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import models.Stamp;
import models.User;

public class RatingDto 
{
	
    private long rating_id;
    private long stamp_id;
	private String user_email;
	private int stamp_rating;
	private String stamp_comments;
	private Date creation_date;
    
    public long getRating_id() {
		return rating_id;
	}
	public void setRating_id(long rating_id) {
		this.rating_id = rating_id;
	}
	public long getStamp_id() {
		return stamp_id;
	}
	public void setStamp_id(long stamp_id) {
		this.stamp_id = stamp_id;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public int getStamp_rating() {
		return stamp_rating;
	}
	public void setStamp_rating(int stamp_rating) {
		this.stamp_rating = stamp_rating;
	}
	public String getStamp_comments() {
		return stamp_comments;
	}
	public void setStamp_comments(String stamp_comments) {
		this.stamp_comments = stamp_comments;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	
}
