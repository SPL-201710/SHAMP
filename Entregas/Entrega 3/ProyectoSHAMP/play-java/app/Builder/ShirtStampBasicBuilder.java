package Builder;

import Interfaces.OrderShirtGeneralDto;
import dto.OrderShirtBasicDto;
import models.StampShirt;

public class ShirtStampBasicBuilder extends ShirtStampBuilder {

	@Override
	public OrderShirtGeneralDto mapper(StampShirt stampShirt) {
		OrderShirtBasicDto orderShirtBasicDto = new OrderShirtBasicDto();
		return orderShirtBasicDto.mapper(stampShirt);
	}

}
