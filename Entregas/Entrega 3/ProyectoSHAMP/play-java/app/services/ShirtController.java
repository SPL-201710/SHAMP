package services;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Backend.ShirtAdmin;
import Backend.StampAdmin;
import Interfaces.IStampDto;
import dto.ShirtDto;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import util.Util;

public class ShirtController extends Controller 
{
	public Result getShirts()
	{
		List<ShirtDto> shirts = ShirtAdmin.getShirts();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonData = mapper.convertValue(shirts, JsonNode.class);
		return ok(Util.createResponse(jsonData, true));
	}
	
	public Result getShirt(long id)
	{
		ShirtDto shirt = ShirtAdmin.getShirt(id);
		JsonNode jsonObject = Json.toJson(shirt);
		return ok(Util.createResponse(jsonObject, true));
	}
}
