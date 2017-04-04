package co.edu.uniandes.shamp.data;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import co.edu.uniandes.shamp.model.OrderShirt;
import co.edu.uniandes.shamp.util.exception.SystemException;

@ApplicationScoped
public class OrderStampShirtRepository {

  @Inject
  private EntityManager em;

  @Inject
  private Logger logger;

  public void persist(final OrderShirt orderShirt) {
    try {
      this.em.persist(orderShirt);
    } catch (final PersistenceException pe) {
      this.logger.log(Level.SEVERE, "Error to persist orderShirt", pe);
      throw new SystemException("Error to persist orderShirt");
    }
  }
}
