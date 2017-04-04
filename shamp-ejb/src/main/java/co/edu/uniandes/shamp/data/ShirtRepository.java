package co.edu.uniandes.shamp.data;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import co.edu.uniandes.shamp.model.Shirt;

@ApplicationScoped
public class ShirtRepository {

	@Inject
	private EntityManager em;

	@Inject
	private Logger logger;

	public List<Shirt> findAll() {
		try {
			return this.em.createNamedQuery(Shirt.FIND_ALL, Shirt.class).getResultList();
		} catch (final NoResultException nre) {
			this.logger.log(Level.WARNING, "problemas al listar todas las estampas");
			return null;
		}
	}

	public Shirt findId(final int shirtId) {
		try {
			return this.em.createNamedQuery(Shirt.FIND_BY_ID, Shirt.class).setParameter("shirtId", shirtId)
					.getSingleResult();

		} catch (final NoResultException nre) {
			this.logger.log(Level.WARNING, "problemas al listar todas las estampas");
			return null;
		}
	}

}