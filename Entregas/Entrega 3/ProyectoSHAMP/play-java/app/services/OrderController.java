package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.mvc.Controller;
import play.mvc.Result;
import play.libs.Json;
import play.libs.Json.*;
import util.AuthUtils;
import util.Util;
import models.*;
import Backend.OrderAdmin;
import Backend.UserAdmin;
import dto.CustomerDto;
import dto.OrderDto;
import dto.OrderResponseDto;
import dto.Session;
import dto.UserLogin;
import java.util.*;
import com.nimbusds.jose.JOSEException;

public class OrderController extends Controller
{
	public Result registerOrder()
	{
		try
		{
			JsonNode json = request().body().asJson();
			if(json == null) {
				return badRequest(Util.createResponse("Expecting Json data", false));
			}
			OrderAdmin.createOrder(Json.fromJson(json, OrderDto.class));
			JsonNode jsonObject = Json.toJson("");
			return ok(Util.createResponse(jsonObject, true));
		}
		catch (Exception ex)
		{
			return badRequest(Util.createResponse(ex.getMessage(), false)); 
		}
	}
	
	public Result getOrderByUserID(double id)
	{
		try
		{

			List<OrderDto> responseListOrderDto = OrderAdmin.getOrderByUserID(id);
			JsonNode jsonObject = Json.toJson(responseListOrderDto);
			return ok(Util.createResponse(jsonObject, true));

		}
		catch (Exception ex)
		{
			return badRequest(Util.createResponse(ex.getMessage(), false)); 
		}

	}
	
	public Result getOrderResponseByUserID()
	{
		try
		{
			Map<String,String[]> headers = request().headers();
			String[] user_id = headers.get("user_id");
			if(user_id.length>0)
			{
				long id  = Long.parseLong(user_id[0]);
				List<OrderResponseDto> responseListOrderDto = OrderAdmin.getOrderResponseByUserID(id);
				JsonNode jsonObject = Json.toJson(responseListOrderDto);
				return ok(Util.createResponse(jsonObject, true));
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

