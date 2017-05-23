package dto;

import java.util.ArrayList;
import java.util.List;

import Interfaces.IStampDto;

public class StampFilterRatingDto implements IStampDto 
{
	private long stamp_id;
	private long user_id;
    private String user_email;
    private long category_id;
    private String category_name;
    private int stamp_status;
    private String stamp_name;
    private String stamp_short_description;
    private double stamp_price;
    private String stamp_image_path;
    private String stamp_blackwhite;
    private String stamp_negative;
    private double stamp_rating;
    private List<RatingDto> ratings;
    
    public StampFilterRatingDto()
    {
    	ratings = new ArrayList<RatingDto>();
    }
    
    public void addRating(RatingDto rating)
    {
    	ratings.add(rating);
    }
	
	public long getStamp_id() {
		return stamp_id;
	}
	public void setStamp_id(long stamp_id) {
		this.stamp_id = stamp_id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
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
	public double getStamp_price() {
		return stamp_price;
	}
	public void setStamp_price(double stamp_price) {
		this.stamp_price = stamp_price;
	}
	public String getStamp_image_path() {
		return stamp_image_path;
	}
	public void setStamp_image_path(String stamp_image_path) {
		this.stamp_image_path = stamp_image_path;
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
	public double getStamp_rating() {
		return stamp_rating;
	}
	public void setStamp_rating(double stamp_rating) {
		this.stamp_rating = stamp_rating;
	}
	public List<RatingDto> getRatings() {
		return ratings;
	}
	public void setRatings(List<RatingDto> ratings) {
		this.ratings = ratings;
	}
	
    
    
    
    
}
