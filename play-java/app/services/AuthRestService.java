package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.mvc.Controller;
import play.mvc.Result;
import play.libs.Json;
import play.libs.Json.*;
import util.Util;
import models.*;
import Backend.UserAdmin;
import dto.CustomerDto;
import dto.Session;

public class AuthRestService extends Controller
{
	public Result login()
	{
		return null;
	}
	
	public Result register()
	{
		
		
		try
		{
			
			JsonNode json = request().body().asJson();
			
			
			if(json == null)
			{
				return badRequest(Util.createResponse("Expecting Json data", false));
			}
			Session tempSession = UserAdmin.registerUser(Json.fromJson(json, CustomerDto.class));
			JsonNode jsonObject = Json.toJson(tempSession);
			return created(Util.createResponse(jsonObject, true));
			
		}
		catch(Exception ex)
		{
			 return badRequest(Util.createResponse(ex.getMessage(), false)); 
		}
		
	}
	
	
}
