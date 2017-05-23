package services;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import Backend.StampAdmin;
import Backend.UserAdmin;
import Interfaces.IStampDto;
import dto.CustomerDto;
import dto.Session;
import dto.StampBasicDto;
import dto.CreateRatingDto;
import dto.RatingDto;
import models.Stamp;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import util.AuthUtils;
import util.Util;

public class StampController extends Controller 
{
	public Result getStamps()
	{
		List<IStampDto> stamps = StampAdmin.getStamps();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonData = mapper.convertValue(stamps, JsonNode.class);
		return ok(Util.createResponse(jsonData, true));
	}
	
	public Result getPrivateStamps()
	{
		
		try
		{
			Map<String,String[]> headers = request().headers();
			String[] user_id = headers.get("user_id");
			if(user_id.length>0)
			{
				long id  = Long.parseLong(user_id[0]);
				List<IStampDto> stamps = StampAdmin.getPrivateStamps(id);
				ObjectMapper mapper = new ObjectMapper();
				JsonNode jsonData = mapper.convertValue(stamps, JsonNode.class);
				return ok(Util.createResponse(jsonData, true));
			}
			else
			{
				throw new Exception("Error en credenciales de acceso");
			}
		}
		catch (Exception ex)
		{
			return badRequest(Util.createResponse(ex.getMessage(), false));
		}
		
		
	}
	
	public Result getStamp(long id)
	{
		IStampDto stamp = StampAdmin.getStamp(id);
		JsonNode jsonObject = Json.toJson(stamp);
		return ok(Util.createResponse(jsonObject, true));
	}
	
	public Result getPrivateStamp(long id)
	{
		try
		{

			Map<String,String[]> headers = request().headers();
			String[] user_id = headers.get("user_id");
			if(user_id.length>0)
			{
				long id_user  = Long.parseLong(user_id[0]);
				IStampDto stamps = StampAdmin.getPrivateStamp(id, id_user);
				ObjectMapper mapper = new ObjectMapper();
				JsonNode jsonData = mapper.convertValue(stamps, JsonNode.class);
				return ok(Util.createResponse(jsonData, true));
			}
			else
			{
				throw new Exception("Error en credenciales de acceso");
			}
		}
		catch (Exception ex)
		{
			return badRequest(Util.createResponse(ex.getMessage(), false));
		}
		
	}
	
	public Result createRating()
	{
		try
		{
			Map<String,String[]> headers = request().headers();
			String[] user_id = headers.get("user_id");
			if(user_id.length>0)
			{
				long id  = Long.parseLong(user_id[0]);
				JsonNode json = request().body().asJson();
				if(json == null)
				{
					return badRequest(Util.createResponse("Expecting Json data", false));
				}
				
				RatingDto tempRating = StampAdmin.createRating(Json.fromJson(json, CreateRatingDto.class), id);
				JsonNode jsonObject = Json.toJson(tempRating);
				return created(Util.createResponse(jsonObject, true));
			}
			else
			{
				throw new Exception("Error en credenciales de acceso");
			}
		}
		catch (Exception ex)
		{
			return badRequest(Util.createResponse(ex.getMessage(), false)); 
		}
		
		
		
	}
}
