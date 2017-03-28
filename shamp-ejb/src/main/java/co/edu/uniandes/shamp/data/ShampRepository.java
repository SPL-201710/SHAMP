package co.edu.uniandes.shamp.data;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import co.edu.uniandes.shamp.model.Stamp;

@ApplicationScoped
public class ShampRepository {

	@Inject
	private EntityManager em;

	@Inject
	private Logger logger;

	public List<Stamp> findAll() {
		try {
			return this.em.createNamedQuery(Stamp.FIND_ALL, Stamp.class).getResultList();
		} catch (final NoResultException nre) {
			this.logger.log(Level.WARNING, "problemas al listar todas las estampas");
			return null;
		}
	}

	public Stamp findId(final int stampId) {
		try {
			return this.em.createNamedQuery(Stamp.FIND_BY_ID, Stamp.class).setParameter("stampId", stampId)
					.getSingleResult();

		} catch (final NoResultException nre) {
			this.logger.log(Level.WARNING, "problemas al listar todas las estampas");
			return null;
		}
	}

}
