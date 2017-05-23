package dto;

import Interfaces.OrderShirtGeneralDto;
import models.Shirt;
import models.Stamp;
import models.StampShirt;

public class OrderShirtBasicDto implements OrderShirtGeneralDto
{
	private long stamp_id;
	private long shirt_id;
	private Double quantity;
	private String shirt_size;
    private String shirt_sex;
    private String shirt_location;
    private String stamp_url;
    
	public long getStamp_id() {
		return stamp_id;
	}
	public void setStamp_id(long stamp_id) {
		this.stamp_id = stamp_id;
	}
	public long getShirt_id() {
		return shirt_id;
	}
	public void setShirt_id(long shirt_id) {
		this.shirt_id = shirt_id;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public String getShirt_size() {
		return shirt_size;
	}
	public void setShirt_size(String shirt_size) {
		this.shirt_size = shirt_size;
	}
	public String getShirt_sex() {
		return shirt_sex;
	}
	public void setShirt_sex(String shirt_sex) {
		this.shirt_sex = shirt_sex;
	}
	public String getShirt_location() {
		return shirt_location;
	}
	public void setShirt_location(String shirt_location) {
		this.shirt_location = shirt_location;
	}
	
	
	
	public String getStamp_url() {
		return stamp_url;
	}
	public void setStamp_url(String stamp_url) {
		this.stamp_url = stamp_url;
	}
	
	@Override
	public OrderShirtGeneralDto mapper(StampShirt stampShirt) {
		
		OrderShirtBasicDto OrderShirtBasicDto = new OrderShirtBasicDto();
		OrderShirtBasicDto.setQuantity(stampShirt.quantity);
		OrderShirtBasicDto.setShirt_id(stampShirt.shirt_id.shirt_id);
		OrderShirtBasicDto.setShirt_location(stampShirt.stamp_location);
		OrderShirtBasicDto.setShirt_sex("");
		OrderShirtBasicDto.setShirt_size(stampShirt.stamp_size);
		OrderShirtBasicDto.setStamp_id(stampShirt.stamp_id.stamp_id);
		OrderShirtBasicDto.setStamp_url(stampShirt.stamp_url);
		return OrderShirtBasicDto;
	}
}
