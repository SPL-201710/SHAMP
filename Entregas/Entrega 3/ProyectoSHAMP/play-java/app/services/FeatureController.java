package services;

import com.fasterxml.jackson.databind.JsonNode;

import Backend.FeatureAdmin;
import Backend.UserAdmin;
import dto.CustomerDto;
import dto.FeatureDto;
import dto.Session;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import util.Util;

public class FeatureController extends Controller 
{
	public Result getActiveFeatures()
	{
		
		
		
		try
		{
			FeatureDto activeFeatures = FeatureAdmin.getActiveFeature();
			JsonNode jsonObject = Json.toJson(activeFeatures);
			return ok(Util.createResponse(jsonObject, true));
		}
		catch(Exception ex)
		{
			return ok("error"); 
			//return badRequest(Util.createResponse(ex.getMessage(), false)); 
		}
		
	}
}
