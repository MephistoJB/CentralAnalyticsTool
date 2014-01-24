package test.entitaeten;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestMedikamente.class, TestAdresse.class,
		TestAnsprechpartner.class, TestUnternehmen.class })
public class AllTests {

	private static final String PERSISTENCE_UNIT_NAME = "Entitaeten";
	static final EntityManagerFactory factory = Persistence
			.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

	public static void deleteAllTables(String[] entities) {

		EntityManager em = factory.createEntityManager();
		// Begin a new local transaction so that we can persist a new entity
		em.getTransaction().begin();
		for (String entity : entities) {
			Query q = em.createQuery("SELECT m FROM " + entity + " m");
			List<?> list = q.getResultList();
			for (Object o : list)
				em.remove(o);
		}
		em.getTransaction().commit();
		em.close();
	}
}
