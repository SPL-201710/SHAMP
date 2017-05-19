package services;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.mvc.Controller;
import play.mvc.Result;
import play.libs.Json;
import play.libs.Json.*;
import util.Util;
import models.*;
import Backend.*;

public class StampRestService extends Controller {

	 public Result getAll()
		{
			try
			{
				return created(Util.createResponse(Json.toJson(StampAdmin.getAll()), true));
			}
			catch(Exception ex)
			{
				 return badRequest(Util.createResponse(ex.getMessage(), false)); 
			}
		}
	 
	 public Result getAllStampDto()
		{
			try
			{
				return created(Util.createResponse(Json.toJson(StampAdmin.getAllStampDto()), true));
			}
			catch(Exception ex)
			{
				 return badRequest(Util.createResponse(ex.getMessage(), false)); 
			}
		}
	 
	 
	
}

