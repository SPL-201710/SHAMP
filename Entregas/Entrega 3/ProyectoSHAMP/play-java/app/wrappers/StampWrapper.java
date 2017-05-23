package wrappers;

import java.util.List;

import Interfaces.IStampDto;
import dto.RatingDto;
import dto.StampBasicDto;
import dto.StampFilterRatingDto;
import dto.StampFiltersDto;
import dto.StampRatingDto;
import models.Stamp;
import models.StampRating;
import play.Play;

public class StampWrapper 
{
	private Stamp originalStamp;
	
	public StampWrapper (Stamp stamp)
	{
		this.originalStamp = stamp;
	}
	
	public void updateRating()
	{
		List<StampRating> ratings =StampRating.find.where().eq("stamp_id", originalStamp).eq("rating_status",1).findList();
		double totalRating = 0;
		for(int i = 0; i<ratings.size();i++)
		{
			totalRating += ratings.get(i).stamp_rating;
		}
		totalRating = totalRating/ratings.size();
		originalStamp.updateRating(totalRating);
		originalStamp.update();
	}
	
	 public IStampDto getStampDto()
	 {
		 String rating = Play.application().configuration().getString("feature.productrating");
		 String filters = Play.application().configuration().getString("feature.filters");
		 
		 boolean controlRating = false;
		 boolean controlFilters = false;
		 
		 if(rating != null)
		 {
			 if(rating.equals("true"))
			 {
				 controlRating = true;
			 }
		 }
		 
		 if(filters != null)
		 {
			 if(filters.equals("true"))
			 {
				 controlFilters = true;
			 }
		 }
		 
		 IStampDto response = null;
		 if(!controlRating && !controlFilters)
		 {
			 response = getStampBasicDto();
		 }
		 else if(controlRating && !controlFilters)
		 {
			 response = getStampRatingDto();
		 }
		 else if(!controlRating && controlFilters)
		 {
			 response = getStampFiltersDto();
		 }
		 else if(controlRating && controlFilters)
		 {
			 response =getStampFilterRatingDto();
		 }
		 
		 return response;
		 
		 
	 }
	 
	 public IStampDto getStampRatingDto()
	 {
		 StampRatingDto temp = new StampRatingDto();
			temp.setCategory_id(originalStamp.category_id.category_id);
			temp.setCategory_name(originalStamp.category_id.category_name);
			temp.setStamp_id(originalStamp.stamp_id);
			temp.setStamp_image_path(originalStamp.stamp_small_image_path);
			temp.setStamp_name(originalStamp.name);
			temp.setStamp_price(originalStamp.stamp_price);
			temp.setStamp_short_description(originalStamp.stamp_short_description);
			temp.setStamp_status(originalStamp.stamp_status);
			temp.setUser_id(originalStamp.user_id.user_id);
			temp.setUser_email(originalStamp.user_id.user_mail);
			temp.setStamp_rating(originalStamp.stamp_rating);
			List<StampRating> ratings =StampRating.find.where().eq("stamp_id", originalStamp).eq("rating_status",1).findList();
			for(int i= 0;i<ratings.size();i++)
			{
				temp.addRating(createRatingDto(ratings.get(i)));
			}
			return temp;
	 }
	 
	 public IStampDto getStampFiltersDto()
	 {
		 StampFiltersDto temp = new StampFiltersDto();
			temp.setCategory_id(originalStamp.category_id.category_id);
			temp.setCategory_name(originalStamp.category_id.category_name);
			temp.setStamp_id(originalStamp.stamp_id);
			temp.setStamp_image_path(originalStamp.stamp_small_image_path);
			temp.setStamp_name(originalStamp.name);
			temp.setStamp_price(originalStamp.stamp_price);
			temp.setStamp_short_description(originalStamp.stamp_short_description);
			temp.setStamp_status(originalStamp.stamp_status);
			temp.setUser_id(originalStamp.user_id.user_id);
			temp.setUser_email(originalStamp.user_id.user_mail);
			temp.setStamp_blackwhite(originalStamp.stamp_blackwhite);
			temp.setStamp_negative(originalStamp.stamp_negative);
	    	return temp;
	 }
	 
	 public IStampDto getStampFilterRatingDto()
	 {
		 StampFilterRatingDto temp = new StampFilterRatingDto();
			temp.setCategory_id(originalStamp.category_id.category_id);
			temp.setCategory_name(originalStamp.category_id.category_name);
			temp.setStamp_id(originalStamp.stamp_id);
			temp.setStamp_image_path(originalStamp.stamp_small_image_path);
			temp.setStamp_name(originalStamp.name);
			temp.setStamp_price(originalStamp.stamp_price);
			temp.setStamp_short_description(originalStamp.stamp_short_description);
			temp.setStamp_status(originalStamp.stamp_status);
			temp.setUser_id(originalStamp.user_id.user_id);
			temp.setUser_email(originalStamp.user_id.user_mail);
			temp.setStamp_blackwhite(originalStamp.stamp_blackwhite);
			temp.setStamp_negative(originalStamp.stamp_negative);
			temp.setStamp_rating(originalStamp.stamp_rating);
			List<StampRating> ratings =StampRating.find.where().eq("stamp_id", originalStamp).eq("rating_status",1).findList();
			for(int i= 0;i<ratings.size();i++)
			{
				temp.addRating(createRatingDto(ratings.get(i)));
			}
	    	return temp;
	 }
	 
	 public IStampDto getStampBasicDto()
	 {
		 StampBasicDto temp = new StampBasicDto();
			temp.setCategory_id(originalStamp.category_id.category_id);
			temp.setCategory_name(originalStamp.category_id.category_name);
			temp.setStamp_id(originalStamp.stamp_id);
			temp.setStamp_image_path(originalStamp.stamp_small_image_path);
			temp.setStamp_name(originalStamp.name);
			temp.setStamp_price(originalStamp.stamp_price);
			temp.setStamp_short_description(originalStamp.stamp_short_description);
			temp.setStamp_status(originalStamp.stamp_status);
			temp.setUser_id(originalStamp.user_id.user_id);
			temp.setUser_email(originalStamp.user_id.user_mail);
	    	return temp;
	 }
	 
	 public RatingDto createRatingDto(StampRating rating)
	 {
		 RatingDto tempRating = new RatingDto();
		 tempRating.setCreation_date(rating.creation_date);
		 tempRating.setRating_id(rating.rating_id);
		 tempRating.setStamp_comments(rating.stamp_comments);
		 tempRating.setStamp_id(rating.stamp_id.stamp_id);
		 tempRating.setStamp_rating(rating.stamp_rating);
		 tempRating.setUser_email(rating.user_id.user_mail);
		 return tempRating;
	 }
	
	
}
