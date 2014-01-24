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

		// Starte eine Transaktion, damit wir jede neue Entit�t registrieren
		em.getTransaction().begin();

		// Lies alle Entit�ten ein
		Query q = em.createQuery("select m from Medikament m");

		// Haben wir Entit�ten?
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

		// Commit, damit die gespeicherte Transaktion �bernommen wird
		em.getTransaction().commit();

		// Sch�n Ressourcen sparen
		em.close();
	}

	@Test
	public void checkAvailableMedikamente() {

		// Jetzt mal schauen ob es bereits Medikamente gibt
		EntityManager em = AllTests.factory.createEntityManager();

		// Hole mir alle Medikamente
		Query q = em.createQuery("select m from Medikament m");

		// Wir m�ssten 1 Medikament haben
		assertTrue(q.getResultList().size() == 1);

		// Jetzt mal schauen ob es bereits Jahres�bersichten gibt

		// Hole mir alle Jahres�bersichten
		q = em.createQuery("select j from Jahresuebersicht j");

		// Wir m�ssten 40 Jahres�bersichten haben
		assertTrue(q.getResultList().size() == 40);

		em.close();
	}

	@Test
	public void checkMedikament() {
		EntityManager em = AllTests.factory.createEntityManager();
		// Jetzt schauen wir uns mal die Beziehung der Medikamente mit den
		// Jahres�bersichten an
		// Wir m�ssten 1 Medikament haben und dieses m�sste die 40
		// Jahres�bersichten haben
		Query q = em.createQuery("select m from Medikament m");

		// Wir m�ssten jetzt 1 Medikament mit 40 Jahres�bersichten haben
		assertTrue(q.getResultList().size() == 1);
		assertTrue(((Medikament) q.getSingleResult()).getJahresuebersichten()
				.size() == 40);
		em.close();
	}

	@After
	public void tearDown() {
		// Nun r�umen wir mal hinter uns auf
		AllTests.deleteAllTables(new String[]{"Medikament", "Jahresuebersicht"});
	}
}
