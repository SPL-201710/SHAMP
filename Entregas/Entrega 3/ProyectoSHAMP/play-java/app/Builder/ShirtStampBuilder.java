package Builder;

import Interfaces.OrderShirtGeneralDto;
import dto.OrderShirtDto;
import models.StampShirt;

public abstract class ShirtStampBuilder {

	protected OrderShirtDto orderShirtDto;

	
    public OrderShirtDto getOrderShirtDto() {
		return orderShirtDto;
	}
	public void setOrderShirtDto(OrderShirtDto orderShirtDto) {
		this.orderShirtDto = orderShirtDto;
	}

	
	public void newOrderShirtDto() { this.orderShirtDto = new OrderShirtDto(); }
 
    public abstract OrderShirtGeneralDto mapper(StampShirt stampShirt);
}
