package dto;

import Builder.ShirtStampBuilder;
import Interfaces.OrderShirtGeneralDto;
import models.StampShirt;

public class MapperShirtStamp {

    private ShirtStampBuilder shirtStampBuilder;
    
    public ShirtStampBuilder getShirtStampBuilder() {
		return shirtStampBuilder;
	}

	public void setShirtStampBuilder(ShirtStampBuilder shirtStampBuilder) {
		this.shirtStampBuilder = shirtStampBuilder;
	}
	
	public OrderShirtDto getOrderShirtDto() {
		return this.shirtStampBuilder.getOrderShirtDto();
	}

	public OrderShirtGeneralDto mapper(StampShirt stampShirt) {
		return shirtStampBuilder.mapper(stampShirt);
    }
}


