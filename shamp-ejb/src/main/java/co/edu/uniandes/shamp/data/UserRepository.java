package co.edu.uniandes.shamp.data;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import co.edu.uniandes.shamp.model.User;
import co.edu.uniandes.shamp.util.exception.BusinessException;
import co.edu.uniandes.shamp.util.exception.SystemException;

@ApplicationScoped
public class UserRepository {

  @Inject
  private EntityManager em;

  @Inject
  private Logger logger;

  public User findByUsername(final String username) {
    try {
      return this.em.createNamedQuery(User.FIND_BY_USERNAME, User.class)
          .setParameter("username", username).getSingleResult();
    } catch (final NoResultException nre) {
      this.logger.log(Level.WARNING, "NOT FOUND: username " + username);
      return null;
    }
  }

  public User findByUsernameAndPassword(final String username, final String password)
      throws BusinessException {
    try {
      return this.em.createNamedQuery(User.FIND_BY_USERNAME_AND_PASSWORD, User.class)
          .setParameter("username", username).setParameter("password", password).getSingleResult();
    } catch (final NoResultException nre) {
      this.logger.log(Level.WARNING, "UNAUTHORIZED: " + username + " " + password);
      throw new BusinessException("Nombre de usuario o contraseña incorrectos",
          "UNAUTHORIZED" + nre);
    }
  }

  public void persist(final User user) {
    try {
      this.em.persist(user);
    } catch (final PersistenceException pe) {
      this.logger.log(Level.SEVERE, "Error to persist user", pe);
      throw new SystemException("Error to persist user");
    }
  }

}
