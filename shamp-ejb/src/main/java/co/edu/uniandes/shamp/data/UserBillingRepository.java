package co.edu.uniandes.shamp.data;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import co.edu.uniandes.shamp.model.UserBilling;
import co.edu.uniandes.shamp.util.exception.SystemException;

@ApplicationScoped
public class UserBillingRepository {

  @Inject
  private EntityManager em;

  @Inject
  private Logger logger;

  public UserBilling findId(final int userId) {
    try {
      return this.em.createNamedQuery(UserBilling.FIND_BY_ID, UserBilling.class)
          .setParameter("userId", userId).getSingleResult();

    } catch (final NoResultException nre) {
      this.logger.log(Level.WARNING, "problemas al listar todas las estampas");
      return null;
    }
  }

  public void persist(final UserBilling userBilling) {
    try {
      this.em.persist(userBilling);
    } catch (final PersistenceException pe) {
      this.logger.log(Level.SEVERE, "Error to persist userBilling", pe);
      throw new SystemException("Error to persist userBilling");
    }
  }

}
