package services;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Backend.MessageAdmin;
import Backend.UserAdmin;
import dto.MessageCreationDto;
import dto.MessageDto;
import dto.Session;
import dto.UserLogin;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import util.AuthUtils;
import util.Util;
import views.html.adminMessages;

public class MessageController extends Controller 
{
	public Result getMessages()
	{
		try
		{
			Map<String,String[]> headers = request().headers();
			String[] user_id = headers.get("user_id");
			if(user_id.length>0)
			{
				long id  = Long.parseLong(user_id[0]);
				List<MessageDto> messages = MessageAdmin.getUnresponsedMessages(id);
				ObjectMapper mapper = new ObjectMapper();
				JsonNode jsonData = mapper.convertValue(messages, JsonNode.class);
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
	
	
	public Result deleteMessage(long id)
	{
		try
		{
			MessageAdmin.deleteMessage(id);
			JsonNode jsonObject = Json.toJson("Mensaje eliminado");
			return ok(Util.createResponse(jsonObject, true));
					
		}
		catch(Exception ex)
		{
			return badRequest(Util.createResponse(ex.getMessage(), false));
		}
	}
	
	public Result responseMessages()
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
				MessageDto tempMessage = MessageAdmin.sendMessage(Json.fromJson(json, MessageCreationDto.class), id);
				JsonNode jsonObject = Json.toJson(tempMessage);
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
