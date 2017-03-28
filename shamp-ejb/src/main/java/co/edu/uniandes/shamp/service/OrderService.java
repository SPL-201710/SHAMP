package co.edu.uniandes.shamp.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.edu.uniandes.shamp.dto.OrderDto;
import co.edu.uniandes.shamp.util.exception.BusinessException;

@Stateless
public class OrderService {

	@Inject
	private Logger loggger;

	public void register(final OrderDto orderDto) throws BusinessException {
		this.loggger.info("Register username");

	}

}
