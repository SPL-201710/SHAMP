package Backend;

import dto.FeatureDto;
import play.Play;

public final  class FeatureAdmin 
{
	public static FeatureDto getActiveFeature()
	{
		FeatureDto activeFeature = new FeatureDto();
		
		String value = Play.application().configuration().getString("feature.advancesearch");
		if(value != null)
		{
			if(value.equals("true"))
			{
				activeFeature.setAdvanceSearch(true);
			}
		}
		
		value = Play.application().configuration().getString("feature.productrating");
		if(value != null)
		{
			if(value.equals("true"))
			{
				activeFeature.setProductRating(true);
			}
		}
		
		value = Play.application().configuration().getString("feature.shirttext");
		if(value != null)
		{
			if(value.equals("true"))
			{
				activeFeature.setShirtText(true);
			}
		}
		
		value = Play.application().configuration().getString("feature.messages");
		if(value != null)
		{
			if(value.equals("true"))
			{
				activeFeature.setMessages(true);
			}
		}
		
		value = Play.application().configuration().getString("feature.filters");
		if(value != null)
		{
			if(value.equals("true"))
			{
				activeFeature.setFilters(true);
			}
		}
		
		value = Play.application().configuration().getString("feature.privatestamp");
		if(value != null)
		{
			if(value.equals("true"))
			{
				activeFeature.setPrivateStamp(true);
			}
		}
		
		value = Play.application().configuration().getString("feature.loginsocialnetwork");
		if(value != null)
		{
			if(value.equals("true"))
			{
				activeFeature.setLoginSocialNetwork(true);
			}
		}
		
		value = Play.application().configuration().getString("feature.sharesocialnetwork");
		if(value != null)
		{
			if(value.equals("true"))
			{
				activeFeature.setShareSocialNetwork(true);
			}
		}
		
		value = Play.application().configuration().getString("feature.changepassword");
		if(value != null)
		{
			if(value.equals("true"))
			{
				activeFeature.setChangePassword(true);
			}
		}
		
		value = Play.application().configuration().getString("feature.changeaddress");
		if(value != null)
		{
			if(value.equals("true"))
			{
				activeFeature.setChangeAddress(true);
			}
		}
		
		value = Play.application().configuration().getString("feature.ratingsreports");
		if(value != null)
		{
			if(value.equals("true"))
			{
				activeFeature.setRatingsReports(true);
			}
		}
		
		value = Play.application().configuration().getString("feature.sellreports");
		if(value != null)
		{
			if(value.equals("true"))
			{
				activeFeature.setSellReports(true);
			}
		}
		 
		return activeFeature;
	}
}
