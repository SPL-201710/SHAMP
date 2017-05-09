
package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.mvc.Controller;
import play.mvc.Result;
import play.libs.Json;
import play.libs.Json.*;
import util.Util;
import models.*;
import Backend.StampAdmin;
import dto.CustomerDto;
import dto.Session;

public class StampRestService extends Controller
{
	public Result getAllStamp()
	{
		try
		{
			return created(Util.createResponse(Json.toJson(StampAdmin.getAllStamp()), true));
		}
		catch(Exception ex)
		{
			 return badRequest(Util.createResponse(ex.getMessage(), false)); 
		}
	}	
}
