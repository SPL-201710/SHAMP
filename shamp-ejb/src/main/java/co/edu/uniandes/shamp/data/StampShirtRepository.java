package co.edu.uniandes.shamp.data;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import co.edu.uniandes.shamp.model.StampShirt;
import co.edu.uniandes.shamp.util.exception.SystemException;

@ApplicationScoped
public class StampShirtRepository {

	@Inject
	private Logger logger;

	@Inject
	private EntityManager em;

	public void persist(final StampShirt stampShirt) {
		try {
			this.em.persist(stampShirt);
		} catch (final PersistenceException pe) {
			this.logger.log(Level.SEVERE, "Error to persist stampShirt", pe);
			throw new SystemException("Error to persist stampShirt");
		}
	}
}
