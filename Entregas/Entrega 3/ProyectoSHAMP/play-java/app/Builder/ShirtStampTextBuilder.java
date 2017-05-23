package Builder;

import Interfaces.OrderShirtGeneralDto;
import dto.OrderShirtTextDto;
import models.StampShirt;

public class ShirtStampTextBuilder extends ShirtStampBuilder {

	@Override
	public OrderShirtGeneralDto mapper(StampShirt stampShirt) {
		OrderShirtTextDto orderShirtTextDto = new OrderShirtTextDto();
		return orderShirtTextDto.mapper(stampShirt);
	}
}
