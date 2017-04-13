package co.edu.uniandes.shamp.data;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import co.edu.uniandes.shamp.model.OrderShirt;
import co.edu.uniandes.shamp.model.StampShirt;
import co.edu.uniandes.shamp.util.exception.SystemException;

@ApplicationScoped
public class OrderStampShirtRepository {

  @Inject
  private EntityManager em;

  @Inject
  private Logger logger;

  public List<OrderShirt> findOrderStampByUserOrderId(final int user_order_id) {
    try {
      return this.em.createNamedQuery(OrderShirt.FIND_BY_USER_ORDER_ID, OrderShirt.class)
          .setParameter("userOrderId", user_order_id).getResultList();

    } catch (final NoResultException nre) {
      this.logger.log(Level.WARNING, "problemas al listar todas las estampas");
      return null;
    }
  }

  public StampShirt findStampShirtById(final int id) {
    try {
      return this.em.createNamedQuery(StampShirt.FIND_BY_ID, StampShirt.class)
          .setParameter("id", id).getSingleResult();

    } catch (final NoResultException nre) {
      this.logger.log(Level.WARNING, "problemas al listar todas las estampas");
      return null;
    }
  }

  public void persist(final OrderShirt orderShirt) {
    try {
      this.em.persist(orderShirt);
    } catch (final PersistenceException pe) {
      this.logger.log(Level.SEVERE, "Error to persist orderShirt", pe);
      throw new SystemException("Error to persist orderShirt");
    }
  }
}
