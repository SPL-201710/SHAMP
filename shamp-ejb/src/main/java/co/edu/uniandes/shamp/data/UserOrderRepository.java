package co.edu.uniandes.shamp.data;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import co.edu.uniandes.shamp.model.User;
import co.edu.uniandes.shamp.model.UserOrder;
import co.edu.uniandes.shamp.util.exception.SystemException;

@ApplicationScoped
public class UserOrderRepository {

  @Inject
  private EntityManager em;

  @Inject
  private Logger logger;

  public User findByUserId(final int userId) {
    try {
      return this.em.createNamedQuery(User.FIND_BY_ID, User.class).setParameter("userId", userId)
          .getSingleResult();

    } catch (final NoResultException nre) {
      this.logger.log(Level.WARNING, "problemas al listar todas las estampas");
      return null;
    }
  }

  public void persist(final UserOrder userOrder) {
    try {
      this.em.persist(userOrder);
    } catch (final PersistenceException pe) {
      this.logger.log(Level.SEVERE, "Error to persist stampShirt", pe);
      throw new SystemException("Error to persist stampShirt");
    }
  }

}
