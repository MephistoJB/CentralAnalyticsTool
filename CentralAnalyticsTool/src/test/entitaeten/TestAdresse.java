package test.entitaeten;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import CentralAnalyticsTool.model.entitaeten.Adresse;

public class TestAdresse {

	@Before
	public void setUp() throws Exception {
		EntityManager em = AllTests.factory.createEntityManager();

		// Starte eine Transaktion, damit wir jede neue Entität registrieren
		em.getTransaction().begin();

		// Lies alle Entitäten ein
		Query q = em.createQuery("select a from Adresse a");
		// Liste müsste eigentlich leer sein

		// Haben wir Entitäten?
		boolean createNewEntries = (q.getResultList().size() == 0);

		// Wenn keine da sind, erstellen wir welche
		if (createNewEntries) {
			assertTrue(q.getResultList().size() == 0);

			for (int i = 0; i < 40; i++) {
				Adresse adr = new Adresse();
				adr.setStrasse((i + 1) + "Teststrasse");
				adr.setHausnummer("" + i * 2);
				adr.setPlz("" + i * 100);
				adr.setOrt((i + 1) + "Testort");
				em.persist(adr);
			}
		}

		// Commit, damit die gespeicherte Transaktion übernommen wird
		em.getTransaction().commit();

		// Schön Ressourcen sparen
		em.close();
	}

	@Test
	public void checkAvailableAdressen() {

		// Jetzt mal schauen ob es bereits Adressen gibt
		EntityManager em = AllTests.factory.createEntityManager();

		// Hole mir alle Adressen
		Query q = em.createQuery("select a from Adresse a");

		// Wir müssten 40 Adressen haben
		assertTrue(q.getResultList().size() == 40);

		em.close();
	}

	@After
	public void tearDown() {
		// Nun räumen wir mal hinter uns auf
		AllTests.deleteAllTables(new String[]{"Adresse"});
	}
}
