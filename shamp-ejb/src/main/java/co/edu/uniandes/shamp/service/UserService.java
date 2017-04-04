package co.edu.uniandes.shamp.service;


import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.edu.uniandes.shamp.data.UserBillingRepository;
import co.edu.uniandes.shamp.data.UserRepository;
import co.edu.uniandes.shamp.dto.Session;
import co.edu.uniandes.shamp.dto.UserDto;
import co.edu.uniandes.shamp.model.User;
import co.edu.uniandes.shamp.model.UserBilling;
import co.edu.uniandes.shamp.util.exception.BusinessException;

@Stateless
public class UserService {

	@Inject
	private Logger loggger;

	@Inject
	private UserRepository repository;

	@Inject
	private UserBillingRepository repositoryBilling;


	private UserDto getUserDto(final User u) {
		final UserDto dto = new UserDto();
		dto.setId(u.getId());
		dto.setEmail(u.getEmail());
		dto.setName(u.getName());
		dto.setSurname(u.getSurname());
		dto.setUsername(u.getUsername());
		return dto;
	}

	public Session login(final User user) throws BusinessException {
		this.loggger.info("Login username " + user.getUsername() + " password " + user.getPassword());
		final User u =
				this.repository.findByUsernameAndPassword(user.getUsername(), user.getPassword());

		final UserBilling bill = this.repositoryBilling.findId(u.getId());

		final UserDto dto = this.getUserDto(u);
		final Session session = new Session();
		session.setUser(dto);
		session.setUserBilling(bill);
		return session;
	}



	public Session register(final co.edu.uniandes.shamp.dto.CustomerDto customerDto)
			throws BusinessException {
		if (Objects.isNull(this.repository.findByUsername(customerDto.getUsername()))) {
			final User customer = new User();
			customer.setActive(true);
			customer.setCreationDate(new Date());
			customer.setUserType(new Integer(1));

			if (customerDto.getEmail() != null) {
				customer.setEmail(customerDto.getEmail());
			}

			if (customerDto.getPassword() != null) {
				customer.setPassword(customerDto.getPassword());
			}

			if (customerDto.getSurname() != null) {
				customer.setSurname(customerDto.getSurname());
				customer.setName(customerDto.getSurname());
			}

			if (customerDto.getUsername() != null) {
				customer.setUsername(customerDto.getUsername());
			}

			this.repository.persist(customer);

			this.loggger.info(
					"Login username " + customerDto.getUsername() + " password " + customerDto.getPassword());
			final User u = this.repository.findByUsernameAndPassword(customerDto.getUsername(),
					customerDto.getPassword());

			final UserBilling customerBilling = new UserBilling();

			customerBilling.setActive(true);
			customerBilling.setBillingStatus(new Integer(1));
			customerBilling.setCreationDate(new Date());

			if (customerDto.getCity() != null) {
				customerBilling.setUserCity(customerDto.getCity());
			}

			if (customerDto.getUser_address() != null) {
				customerBilling.setUserAddress(customerDto.getUser_address());
			}

			if (customerDto.getUser_credit_card() != null) {
				customerBilling.setUserCreditCard(customerDto.getUser_credit_card());
			}

			if (customerDto.getPhone_number() != null) {
				customerBilling.setPhoneNumber(customerDto.getPhone_number());
			}

			if (customerDto.getCountry() != null) {
				customerBilling.setUserCountry(customerDto.getCountry());
			}

			if (customerDto.getCvv() != null) {
				customerBilling.setCvv(customerDto.getCvv());
			}

			if (customerDto.getExpiration_date() != null) {
				customerBilling.setExpirationDate(customerDto.getExpiration_date());
			}

			if (customerDto.getName_card() != null) {
				customerBilling.setNameCard(customerDto.getName_card());
				customerBilling.setName(customerDto.getName_card());
			}

			customerBilling.setUser(u);

			this.repositoryBilling.persist(customerBilling);

			final UserBilling bill = this.repositoryBilling.findId(u.getId());
			final UserDto dto = this.getUserDto(u);
			final Session session = new Session();
			session.setUser(dto);
			session.setUserBilling(bill);
			return session;
		} else {
			throw new BusinessException("Username exist", "CONFLICT");
		}
	}

}
