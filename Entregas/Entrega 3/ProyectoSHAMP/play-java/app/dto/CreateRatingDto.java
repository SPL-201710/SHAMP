package dto;

import java.util.Date;

public class CreateRatingDto 
{
	
    private long stamp_id;
    private int stamp_rating;
	private String stamp_comments;
	
	public long getStamp_id() {
		return stamp_id;
	}
	public void setStamp_id(long stamp_id) {
		this.stamp_id = stamp_id;
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
	

}
