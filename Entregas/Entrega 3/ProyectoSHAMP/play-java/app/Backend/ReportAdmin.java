package Backend;

import dto.ReportDto;
import models.Order;
import models.Shirt;
import models.Stamp;
import models.StampShirt;
import models.User;

import java.util.*;

import java.util.Date;

public final class ReportAdmin 
{
	
	public static List<ReportDto> getAllOrders()
	{
		List<ReportDto> response = new ArrayList<ReportDto>();
		List<StampShirt> allStampShirts = StampShirt.find.all();
		List<Stamp> allStamps = Stamp.find.all();
		List<User> allUsers = User.find.all();
		List<Shirt> allShirt = Shirt.find.all();
		List<Order> allOrder = Order.find.all();
		for(int i = 0; i< allStampShirts.size();i++)
		{
			 
			boolean control = false;
			Order tempOrder = null;
			for(int j = 0;j<allOrder.size()&&!control; j++)
			{
				if(allOrder.get(j).order_id == allStampShirts.get(i).order_id.order_id)
				{
					control = true;
					tempOrder = allOrder.get(j);
				}
			}
			
			control = false;
			User tempUser = null;
			for(int j=0; j<allUsers.size()&& !control;j++)
			{
				if(allUsers.get(j).user_id == tempOrder.user_id.user_id)
				{
					control = true;
					tempUser = allUsers.get(j);
				}
			}
			
			control = false;
			Stamp tempStamp = null;
			for(int j=0; j<allStamps.size()&&!control;j++)
			{
				if(allStamps.get(j).stamp_id == allStampShirts.get(i).stamp_id.stamp_id)
				{
					control = true;
					tempStamp = allStamps.get(j);
				}
			}
			
			control = false;
			User tempArtist = null;
			for(int j=0;j<allUsers.size()&&!control;j++)
			{
				if(allUsers.get(j).user_id == tempStamp.user_id.user_id)
				{
					tempArtist = allUsers.get(j);
					control = true;
				}
			}
			
			control = false;
			Shirt tempShirt = null;
			for(int j = 0; j<allShirt.size()&& !control;j++)
			{
				if(allShirt.get(j).shirt_id == allStampShirts.get(i).shirt_id.shirt_id)
				{
					tempShirt =allShirt.get(j);
					control = true;
				}
			}
					
			response.add(createReportDto( allStampShirts.get(i),tempOrder,tempUser,tempStamp,tempArtist,tempShirt));
		}
		return response;
	}
	
	public static List<ReportDto> filterByDate(List<ReportDto> originalList, Date initialDate, Date endDate)
	{
		List<ReportDto> response = new ArrayList<ReportDto>();
		for(int i = 0; i< originalList.size();i++)
		{
			ReportDto tempDto = originalList.get(i);
			if(tempDto.getOrderDate().after(initialDate) && tempDto.getOrderDate().before(endDate))
			{
				response.add(tempDto);
			}
		}
		return response;
	}
	
	public static List<ReportDto> filterByArtist(List<ReportDto> originalList, String artistEmail)
	{
		List<ReportDto> response = new ArrayList<ReportDto>();
		for(int i = 0; i< originalList.size();i++)
		{
			ReportDto tempDto = originalList.get(i);
			if(tempDto.getArtistMail().equals(artistEmail))
			{
				response.add(tempDto);
			}
		}
		return response;
	}
	
	public static List<ReportDto> filterByUser(List<ReportDto> originalList, String userEmail)
	{
		List<ReportDto> response = new ArrayList<ReportDto>();
		for(int i = 0; i< originalList.size();i++)
		{
			ReportDto tempDto = originalList.get(i);
			if(tempDto.getUserMail().equals(userEmail))
			{
				response.add(tempDto);
			}
		}
		return response;
	}
	
	public static List<ReportDto> filterByStamp(List<ReportDto> originalList, long stampId)
	{
		List<ReportDto> response = new ArrayList<ReportDto>();
		for(int i = 0; i< originalList.size();i++)
		{
			ReportDto tempDto = originalList.get(i);
			if(tempDto.getStampId()== stampId)
			{
				response.add(tempDto);
			}
		}
		return response;
	}
	
	public static List<ReportDto> filterByValue(List<ReportDto> originalList, double minValue, double maxValue)
	{
		List<ReportDto> response = new ArrayList<ReportDto>();
		for(int i = 0; i< originalList.size();i++)
		{
			ReportDto tempDto = originalList.get(i);
			if(tempDto.getValue()>= minValue && tempDto.getValue()<= maxValue)
			{
				response.add(tempDto);
			}
		}
		return response;
	}
	
	public static List<ReportDto> createReport
	(boolean byDate, Date initialDate, Date endDate,
	boolean byArtist, String mailArtist,
	boolean byUser, String mailUser,
	boolean byStamp, long stampId,
	boolean byValue, double minValue, double maxValue) throws Exception
	{
		List<ReportDto> response = getAllOrders();
		
		if(byDate)
		{
			response = filterByDate(response, initialDate, endDate);
		}
		
		if(byArtist)
		{
			response = filterByArtist(response, mailArtist);
		}
		
		if(byUser)
		{
			response = filterByUser(response, mailUser);
		}
		
		if(byStamp)
		{
			response = filterByStamp(response, stampId);
		}
		
		if(byValue)
		{
			response = filterByValue(response,minValue,maxValue);
		}
		
		return response;
		
	}
	public static List<ReportDto> reportByDate(Date initialDate, Date endDate)
	{
		List<ReportDto> response = new ArrayList<ReportDto>();
		
		List<Order> tempOrders = Order.find.where().gt("creation_date", initialDate).le("creation_date", endDate).findList();
		for(int i = 0; i<tempOrders.size();i++)
		{
			List<StampShirt> tempStampShirt = StampShirt.find.where().eq("order_id", tempOrders.get(i)).findList();
			for(int j = 0; j<tempStampShirt.size();j++)
			{
				//response.add(createReportDto(tempStampShirt.get(j)));
			}
		}
		
		return response;
	}
	
	public static List<ReportDto> reportByArtist(String artistMail)
	{
		List<ReportDto> response = new ArrayList<ReportDto>();
		User artist = User.find.where().eq("user_mail", artistMail).findUnique();
		List<Stamp> tempStamps  = Stamp.find.where().eq("user_id", artist).findList();
		for(int i = 0;i<tempStamps.size();i++)
		{
			List<StampShirt> tempStampShirts = StampShirt.find.where().eq("stamp_id", tempStamps.get(i)).findList();
			for(int j=0;j<tempStampShirts.size();j++)
			{
				//response.add(createReportDto( tempStampShirts.get(j)));
			}
		}
		return response;
	}
	
	public static List<ReportDto> reportByUser(String userMail)
	{
		List<ReportDto> response = new ArrayList<ReportDto>();
		User user = User.find.where().eq("user_mail", userMail).findUnique();
		List<Order> tempOrders = Order.find.where().eq("user_id", user).findList();
		for(int i = 0; i<tempOrders.size();i++)
		{
			List<StampShirt> tempStampShirt = StampShirt.find.where().eq("order_id", tempOrders.get(i)).findList();
			for(int j = 0; j<tempStampShirt.size();j++)
			{
				//response.add(createReportDto(tempStampShirt.get(j)));
			}
		}
		
		return response;
	}
	
	public static List<ReportDto> reportByStamp(long stampId)
	{
		List<ReportDto> response = new ArrayList<ReportDto>();
		Stamp tempStamp = Stamp.find.where().eq("stamp_id", stampId).findUnique();
		List<StampShirt> tempStampShirts = StampShirt.find.where().eq("stamp_id", tempStamp).findList();
		for(int i = 0; i<tempStampShirts.size();i++)
		{
			//response.add(createReportDto( tempStampShirts.get(i)));
		}
		return response;
	}
	
	public static List<ReportDto> reportByValue(double minValue, double maxValue)
	{
		List<ReportDto> response = new ArrayList<ReportDto>();
		List<StampShirt> tempStampShirts = StampShirt.find.findList();
		for(int i = 0; i<tempStampShirts.size();i++)
		{
			StampShirt tempStampShirt = tempStampShirts.get(i);
			double quantity = tempStampShirt.quantity;
			double stampPrice = tempStampShirt.stamp_id.stamp_price;
			double shirtPrice = tempStampShirt.shirt_id.shirt_price;
			double totalValue = (stampPrice+shirtPrice)*quantity;
			if(totalValue>= minValue && totalValue<= maxValue)
			{
				//response.add( createReportDto(tempStampShirts.get(i)));
			}
		}
		
		return response;
	}
	
	public static ReportDto createReportDto ( StampShirt tempStampShirt, Order tempOrder, User tempUser, 
			Stamp tempStamp, User tempArtist, Shirt tempShirt)
	{
		
		ReportDto response = new ReportDto();
		
		try
		{
			/*
			long id_order = tempStampShirt.order_id.order_id;
			
			Order tempOrder = Order.find.where().eq("order_id", id_order).findUnique();
			long id_user = tempOrder.user_id.user_id;
			User tempUser = User.find.where().eq("user_id", id_user).findUnique();
			long id_stamp = tempStampShirt.stamp_id.stamp_id;
			Stamp tempStamp = Stamp.find.where().eq("stamp_id", id_stamp).findUnique();
			long id_artist = tempStamp.user_id.user_id;
			User tempArtist = User.find.where().eq("user_id", id_artist).findUnique();
			long id_shirt = tempStampShirt.shirt_id.shirt_id;
			Shirt tempShirt = Shirt.find.where().eq("shirt_id", id_shirt).findUnique();
			*/
			
			response.setUserMail(tempUser.user_mail);
			response.setOrderId(tempOrder.order_id);
			response.setOrderDate(tempOrder.creation_date);
			response.setStampId(tempStampShirt.stamp_id.stamp_id);
			response.setArtistMail(tempArtist.user_mail);
			response.setQuantity((int)tempStampShirt.quantity);
			double quantity = tempStampShirt.quantity;
			double stampPrice = tempStamp.stamp_price;
			double shirtPrice = tempShirt.shirt_price;
			double totalValue = (stampPrice+shirtPrice)*quantity;
			response.setValue(totalValue);
			return response;
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			if(tempStampShirt!= null)
			{
				System.out.println(tempStampShirt.toString());
				System.out.println(tempStampShirt.stamp_shirt_id);
				
			}
		}
		return response;
		
	}
}
