package services;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.mvc.Controller;
import play.mvc.Result;
import play.libs.Json;
import play.libs.Json.*;
import util.AuthUtils;
import util.Util;
import models.*;
import Backend.UserAdmin;
import dto.ChangePasswordDto;
import dto.CustomerDto;
import dto.Session;
import dto.UserLogin;

import com.nimbusds.jose.JOSEException;

public class AuthRestService extends Controller
{
	public Result login()
	{
		try
		{
			JsonNode json = request().body().asJson();
			if(json == null)
			{
				return badRequest(Util.createResponse("Expecting Json data", false));
			}
			Session tempSession = UserAdmin.loginUser(Json.fromJson(json, UserLogin.class));
			AuthUtils.createToken(request().remoteAddress(), tempSession);
			JsonNode jsonObject = Json.toJson(tempSession);
			return ok(Util.createResponse(jsonObject, true));
		}
		catch (Exception ex)
		{
			return badRequest(Util.createResponse(ex.getMessage(), false)); 
		}
		
	}
	
	public Result changePassword()
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
				Session tempSession = UserAdmin.changeUserPassword(id, Json.fromJson(json, ChangePasswordDto.class));	
				JsonNode jsonObject = Json.toJson(tempSession);
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
			AuthUtils.createToken(request().remoteAddress(), tempSession);
			JsonNode jsonObject = Json.toJson(tempSession);
			return created(Util.createResponse(jsonObject, true));
			
			
		}
		catch(Exception ex)
		{
			 return badRequest(Util.createResponse(ex.getMessage(), false)); 
		}
		
	}
	
	
}
