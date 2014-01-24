package test.entitaeten;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import WorkBooster.model.entitaeten.Indikation;

public class TestIndikation {


	@Before
	public void setUp() throws Exception {
		EntityManager em = AllTests.factory.createEntityManager();

		// Starte eine Transaktion, damit wir jede neue Entit�t registrieren
		em.getTransaction().begin();

		// Lies alle Entit�ten ein
		Query q = em.createQuery("select i from Indikation i");
		// Liste m�sste eigentlich leer sein

		// Haben wir Entit�ten?
		boolean createNewEntries = (q.getResultList().size() == 0);

		// Wenn keine da sind, erstellen wir welche
		if (createNewEntries) {
			assertTrue(q.getResultList().size() == 0);

			for (int i = 0; i < 40; i++) {
				Indikation indi = new Indikation();
				indi.setErsterIndikationsschluesselteil(""+i+1);
				indi.setZweiterIndikationsschluesselteil("B"+i*4);
				indi.setZweiterIndikationsschluesselteil("B"+i*4);
				em.persist(indi);
			}
		}

		// Commit, damit die gespeicherte Transaktion �bernommen wird
		em.getTransaction().commit();

		// Sch�n Ressourcen sparen
		em.close();
	}

	@Test
	public void checkAvailableIndikationen() {

		// Jetzt mal schauen ob es bereits Indikationen gibt
		EntityManager em = AllTests.factory.createEntityManager();

		// Hole mir alle Indikationen
		Query q = em.createQuery("select i from Indikation i");

		// Wir m�ssten 1 Indikation haben
		assertTrue(q.getResultList().size() == 40);

		em.close();
	}

	@After
	public void tearDown() {
		// Nun r�umen wir mal hinter uns auf
		AllTests.deleteAllTables(new String[]{"Indikation"});
	}


}
