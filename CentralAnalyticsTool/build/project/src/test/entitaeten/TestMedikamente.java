package test.entitaeten;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import WorkBooster.model.entitaeten.Jahresuebersicht;
import WorkBooster.model.entitaeten.Medikament;

public class TestMedikamente {

	@Before
	public void setUp() throws Exception {

		EntityManager em = AllTests.factory.createEntityManager();

		// Starte eine Transaktion, damit wir jede neue Entität registrieren
		em.getTransaction().begin();

		// Lies alle Entitäten ein
		Query q = em.createQuery("select m from Medikament m");

		// Haben wir Entitäten?
		boolean createNewEntries = (q.getResultList().size() == 0);

		// Wenn keine da sind, erstellen wir welche
		if (createNewEntries) {
			assertTrue(q.getResultList().size() == 0);
			Medikament medi = new Medikament();
			medi.setHersteller("Hersteller Alpha");
			medi.setIndikation("Indikation Alpha");
			medi.setPznName("PZNName Alpha");
			medi.setWirkstoffgruppe("Wirkstoffgruppe Alpha");
			em.persist(medi);

			for (int i = 0; i < 40; i++) {
				Jahresuebersicht jUeber = new Jahresuebersicht();
				jUeber.setJahr(2013 + 1);
				jUeber.setApothekenpreis(12.0 + i);
				jUeber.setHerstellerabgabgepreis(5.0 + i);
				jUeber.setUmsatz(200.00 * i);
				jUeber.setVerordnungen(4 * i);

				em.persist(jUeber);
				medi.addJahresuebersicht(jUeber);
				em.persist(medi);
			}
		}

		// Commit, damit die gespeicherte Transaktion übernommen wird
		em.getTransaction().commit();

		// Schön Ressourcen sparen
		em.close();
	}

	@Test
	public void checkAvailableMedikamente() {

		// Jetzt mal schauen ob es bereits Medikamente gibt
		EntityManager em = AllTests.factory.createEntityManager();

		// Hole mir alle Medikamente
		Query q = em.createQuery("select m from Medikament m");

		// Wir müssten 1 Medikament haben
		assertTrue(q.getResultList().size() == 1);

		// Jetzt mal schauen ob es bereits Jahresübersichten gibt

		// Hole mir alle Jahresübersichten
		q = em.createQuery("select j from Jahresuebersicht j");

		// Wir müssten 40 Jahresübersichten haben
		assertTrue(q.getResultList().size() == 40);

		em.close();
	}

	@Test
	public void checkMedikament() {
		EntityManager em = AllTests.factory.createEntityManager();
		// Jetzt schauen wir uns mal die Beziehung der Medikamente mit den
		// Jahresübersichten an
		// Wir müssten 1 Medikament haben und dieses müsste die 40
		// Jahresübersichten haben
		Query q = em.createQuery("select m from Medikament m");

		// Wir müssten jetzt 1 Medikament mit 40 Jahresübersichten haben
		assertTrue(q.getResultList().size() == 1);
		assertTrue(((Medikament) q.getSingleResult()).getJahresuebersichten()
				.size() == 40);
		em.close();
	}

	@After
	public void tearDown() {
		// Nun räumen wir mal hinter uns auf
		AllTests.deleteAllTables(new String[]{"Medikament", "Jahresuebersicht"});
	}
}
