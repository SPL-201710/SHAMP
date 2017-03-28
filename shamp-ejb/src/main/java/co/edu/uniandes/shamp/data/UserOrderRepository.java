package co.edu.uniandes.shamp.data;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import co.edu.uniandes.shamp.model.UserOrder;
import co.edu.uniandes.shamp.util.exception.SystemException;

@ApplicationScoped
public class UserOrderRepository {

  @Inject
  private EntityManager em;

  @Inject
  private Logger logger;


  public void persist(final UserOrder userOrder) {
    try {
      this.em.persist(userOrder);
    } catch (final PersistenceException pe) {
      this.logger.log(Level.SEVERE, "Error to persist stampShirt", pe);
      throw new SystemException("Error to persist stampShirt");
    }
  }

}
