
package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.mvc.Controller;
import play.mvc.Result;
import play.libs.Json;
import play.libs.Json.*;
import util.Util;
import models.*;
import Backend.ShirtAdmin;
import Backend.OrderAdmin;
import dto.CustomerDto;
import dto.Session;

public class OrderRestService extends Controller
{
	public Result getOrderById(Long id)
	{
		try
		{
			if(id != 0){
				return created(Util.createResponse(Json.toJson(OrderAdmin.getOrderById(id)), true));	
			} else {
				return badRequest(Util.createResponse("Id Invalido", false)); 
			}
		}
		catch(Exception ex)
		{
			 return badRequest(Util.createResponse(ex.getMessage(), false)); 
		}
	}	
}
