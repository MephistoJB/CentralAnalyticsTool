package test.entitaeten;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import CentralAnalyticsTool.model.entitaeten.Jahresuebersicht;

public class TestJahresuebersicht {

	@Before
	public void setUp() throws Exception {
		EntityManager em = AllTests.factory.createEntityManager();

		// Starte eine Transaktion, damit wir jede neue Entität registrieren
		em.getTransaction().begin();

		// Lies alle Entitäten ein
		Query q = em.createQuery("select a from Jahresuebersicht a");
		// Liste müsste eigentlich leer sein

		// Haben wir Entitäten?
		boolean createNewEntries = (q.getResultList().size() == 0);

		// Wenn keine da sind, erstellen wir welche
		if (createNewEntries) {
			assertTrue(q.getResultList().size() == 0);

			for (int i = 0; i < 40; i++) {
				Jahresuebersicht jueber = new Jahresuebersicht();
				jueber.setJahr(2014+i);
				jueber.setUmsatz(241*(i+1));
				jueber.setVerordnungen(i+1);
				jueber.setHerstellerabgabgepreis(i+1);
				jueber.setApothekenpreis(i+1);
				em.persist(jueber);
			}
		}

		// Commit, damit die gespeicherte Transaktion übernommen wird
		em.getTransaction().commit();

		// Schön Ressourcen sparen
		em.close();
	}

	@Test
	public void checkAvailableAdressen() {

		// Jetzt mal schauen ob es bereits Jahresuebersichten gibt
		EntityManager em = AllTests.factory.createEntityManager();

		// Hole mir alle Jahresuebersicht
		Query q = em.createQuery("select a from Jahresuebersicht a");

		// Wir müssten 40 Jahresuebersicht haben
		assertTrue(q.getResultList().size() == 40);

		em.close();
	}

	@After
	public void tearDown() {
		// Nun räumen wir mal hinter uns auf
		AllTests.deleteAllTables(new String[]{"Jahresuebersicht"});
	}
}
