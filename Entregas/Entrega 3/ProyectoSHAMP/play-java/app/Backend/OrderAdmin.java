package Backend;

import models.*;
import forms.*;
import play.data.DynamicForm.*;
import play.data.Form.*;
import play.data.*;
import java.text.SimpleDateFormat;
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.util.*;
import play.Play;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import Builder.ShirtStampBasicBuilder;
import Builder.ShirtStampBuilder;
import Builder.ShirtStampTextBuilder;
import Interfaces.OrderShirtGeneralDto;
import dto.*;

public final class OrderAdmin{
    
	public static void createOrder(OrderDto orderDto)
    {
		User tempUser = User.find.where().eq("user_id", orderDto.getUser_id()).findUnique();
		
		Order order = new Order();
		
		order.active = true;
		order.creation_date = new Date();
		order.name = tempUser.name;
		order.order_status = "En carro de compras";
		order.user_id = tempUser;
		order.delivery_address = orderDto.getOrder().getDelivery_address();
		order.contact_phone = orderDto.getOrder().getContact_phone();
		order.city = orderDto.getOrder().getCity();
		order.country = orderDto.getOrder().getCountry();
		order.save();

		for (OrderShirtDto orderShirtDto : orderDto.getProducts()) {
			
			Stamp tempStamp = Stamp.find.where().eq("stamp_id", orderShirtDto.getShirt_id()).findUnique();
			Shirt tempShirt = Shirt.find.where().eq("shirt_id", orderShirtDto.getStamp_id()).findUnique();
			
			StampShirt stampShirt = new StampShirt();
			
			stampShirt.active=true;
			stampShirt.name = "";
			stampShirt.creation_date = new Date();
			stampShirt.quantity = orderShirtDto.getQuantity();
			stampShirt.order_id = order;
			stampShirt.stamp_location = orderShirtDto.getShirt_location();
			stampShirt.stamp_size = orderShirtDto.getShirt_size();
			stampShirt.stamp_id = tempStamp;
			
			if(orderShirtDto.getText_color() != null) {
				stampShirt.text_color = orderShirtDto.getText_color();
			} else  {
				stampShirt.text_color = "";
			}
			 
			if(orderShirtDto.getText_font() != null) {
				stampShirt.text_font = orderShirtDto.getText_font();
			} else  {
				stampShirt.text_font = "";
			}
			
			if(orderShirtDto.getText_size() != null) {
				stampShirt.text_size = orderShirtDto.getText_size();
			} else  {
				stampShirt.text_size = "";
			}
			
			if(orderShirtDto.getText() != null) {
				stampShirt.text = orderShirtDto.getText();
			} else  {
				stampShirt.text = "";
			}
			
			if(orderShirtDto.getStamp_url() != null) {
				stampShirt.stamp_url = orderShirtDto.getStamp_url();
			} else  {
				stampShirt.stamp_url = "";
			}
			
			stampShirt.shirt_id = tempShirt;
			stampShirt.save();
		}
    }
	
	public static List<OrderDto> getOrderByUserID(double id)
    {
		User tempUser = User.find.where().eq("user_id", id).findUnique();
		List<OrderDto> responseListOrderDto = new ArrayList<OrderDto>();
		List<Order> tempListOrder =  Order.find.where().eq("user_id", tempUser).findList();

        	for (Order tempOrder : tempListOrder) {
    			DetailOrderDto detailOrderDto= new DetailOrderDto();
    			
    			detailOrderDto.setCity(tempOrder.city);
    			detailOrderDto.setContact_phone(tempOrder.contact_phone);
    			detailOrderDto.setCountry(tempOrder.country);
    			detailOrderDto.setDelivery_address(tempOrder.delivery_address);
    			
    			OrderDto orderDto = new OrderDto();
    			orderDto.setOrder(detailOrderDto);
    			
    			 List<OrderShirtDto> products = new ArrayList<OrderShirtDto>();
    			 List<StampShirt> tempListStampShirt =  StampShirt.find.where().eq("order_id", tempOrder).findList();
    			
    			for (StampShirt stampShirt : tempListStampShirt) {
    				Stamp tempStamp = Stamp.find.where().eq("stamp_id", stampShirt.stamp_id).findUnique();
    				Shirt tempShirt = Shirt.find.where().eq("shirt_id", stampShirt.shirt_id).findUnique();
    				OrderShirtDto orderShirtDto = new OrderShirtDto();
    				orderShirtDto.setQuantity(stampShirt.quantity);
    				orderShirtDto.setShirt_id(tempShirt.shirt_id);
    				orderShirtDto.setShirt_location(stampShirt.stamp_location);
    				orderShirtDto.setShirt_sex("");
    				orderShirtDto.setShirt_size(stampShirt.stamp_size);
    				orderShirtDto.setStamp_id(tempStamp.stamp_id);
    				products.add(orderShirtDto);
    			}
    			orderDto.setProducts(products);
    			responseListOrderDto.add(orderDto);
    		}
		return responseListOrderDto;

    }
	
	public static List<OrderResponseDto> getOrderResponseByUserID(double id)
    {
		User tempUser = User.find.where().eq("user_id", id).findUnique();
		List<OrderResponseDto> responseListOrderDto = new ArrayList<OrderResponseDto>();
		List<Order> tempListOrder =  Order.find.where().eq("user_id", tempUser).findList();

        	for (Order tempOrder : tempListOrder) {
    			DetailOrderDto detailOrderDto= new DetailOrderDto();
    			
    			detailOrderDto.setCity(tempOrder.city);
    			detailOrderDto.setContact_phone(tempOrder.contact_phone);
    			detailOrderDto.setCountry(tempOrder.country);
    			detailOrderDto.setDelivery_address(tempOrder.delivery_address);
    			
    			OrderResponseDto orderResponseDto = new OrderResponseDto();
    			orderResponseDto.setOrder(detailOrderDto);
    			
    			 List<OrderShirtGeneralDto> products = new ArrayList<OrderShirtGeneralDto>();
    			 List<StampShirt> tempListStampShirt =  StampShirt.find.where().eq("order_id", tempOrder).findList();
    			 
    			 String shirttext = Play.application().configuration().getString("feature.shirttext");
    			 
    			 for (StampShirt stampShirt : tempListStampShirt) {
    				MapperShirtStamp mapperShirtStamp = new MapperShirtStamp();
    				
    				if(shirttext != null)
    				 {
    					 if(shirttext.equals("true"))
    					 {
    						 ShirtStampBuilder shirtStampBuilder = new ShirtStampTextBuilder();
    	    					mapperShirtStamp.setShirtStampBuilder(shirtStampBuilder);
    	    					products.add(mapperShirtStamp.mapper(stampShirt));
    					 } else {
    						 ShirtStampBuilder shirtStampBuilder = new ShirtStampBasicBuilder();
    	    					mapperShirtStamp.setShirtStampBuilder(shirtStampBuilder);
    	    					products.add(mapperShirtStamp.mapper(stampShirt));
    					 }
    				 }
    			}
    			orderResponseDto.setProducts(products);
    			responseListOrderDto.add(orderResponseDto);
    		}
		return responseListOrderDto;

    }

}
