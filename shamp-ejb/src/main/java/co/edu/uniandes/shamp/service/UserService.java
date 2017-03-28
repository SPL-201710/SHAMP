package co.edu.uniandes.shamp.service;


import java.util.Objects;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.edu.uniandes.shamp.data.UserRepository;
import co.edu.uniandes.shamp.dto.Session;
import co.edu.uniandes.shamp.dto.UserDto;
import co.edu.uniandes.shamp.model.User;
import co.edu.uniandes.shamp.util.exception.BusinessException;

@Stateless
public class UserService {

  @Inject
  private Logger loggger;

  @Inject
  private UserRepository repository;

  private UserDto getUserDto(final User u) {
    final UserDto dto = new UserDto();
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
    final UserDto dto = this.getUserDto(u);
    final Session session = new Session();
    session.setUser(dto);
    return session;
  }



  public void register(final User user) throws BusinessException {
    this.loggger
        .info("Register username " + user.getUsername() + " password " + user.getPassword());
    if (Objects.isNull(this.repository.findByUsername(user.getUsername()))) {
      this.repository.persist(user);
    } else {
      throw new BusinessException("Username exist", "CONFLICT");
    }
  }

}
