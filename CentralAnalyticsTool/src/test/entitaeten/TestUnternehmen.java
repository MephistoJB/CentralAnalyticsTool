package test.entitaeten;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import CentralAnalyticsTool.model.entitaeten.Adresse;
import CentralAnalyticsTool.model.entitaeten.Ansprechpartner;
import CentralAnalyticsTool.model.entitaeten.Indikation;
import CentralAnalyticsTool.model.entitaeten.Unternehmen;

public class TestUnternehmen {

	@Before
	public void setUp() throws Exception {

		EntityManager em = AllTests.factory.createEntityManager();

		// Starte eine Transaktion, damit wir jede neue Entität registrieren
		em.getTransaction().begin();

		// Lies alle Entitäten ein
		Query q = em.createQuery("select u from Unternehmen u");

		// Haben wir Entitäten?
		boolean createNewEntries = (q.getResultList().size() == 0);

		// Wenn keine da sind, erstellen wir welche
		if (createNewEntries) {
			assertTrue(q.getResultList().size() == 0);
			Unternehmen unternehmen = new Unternehmen();

			Adresse adresse = new Adresse();
			adresse.setStrasse("Teststrasse");
			adresse.setHausnummer("2");
			adresse.setPlz("33333");
			adresse.setOrt("Testort");
			em.persist(adresse);

			Indikation indi = new Indikation();
			indi.setErsterIndikationsschluesselteil("40");
			indi.setZweiterIndikationsschluesselteil("B123");
			em.persist(indi);

			unternehmen.setErsterSchwerpunkt(indi);
			unternehmen.setZweiterSchwerpunkt(null);
			unternehmen.setAdresse(adresse);

			em.persist(unternehmen);

			for (int i = 0; i < 40; i++) {
				Adresse adr = new Adresse();
				adr.setStrasse((i + 1) + "Teststrasse");
				adr.setHausnummer("" + i * 2);
				adr.setPlz("" + i * 100);
				adr.setOrt((i + 1) + "Testort");
				em.persist(adr);

				Ansprechpartner partner = new Ansprechpartner();
				partner.setName("Partner " + i);
				partner.setPosition("Position " + i);
				partner.setVorname("Vorname " + i);
				partner.setUnternehmen(unternehmen);
				partner.setTelefonnummer(""
						+ Math.round((i * Math.random() * 1000000)));
				partner.setAdresse(adr);

				unternehmen.addAnsprechpartner(partner);

				em.persist(partner);
				em.persist(unternehmen);
			}
		}

		// Commit, damit die gespeicherte Transaktion übernommen wird
		em.getTransaction().commit();

		// Schön Ressourcen sparen
		em.close();
	}

	@Test
	public void checkAvailableUnternehmen() {

		// Jetzt mal schauen ob es bereits Indikationen gibt
		EntityManager em = AllTests.factory.createEntityManager();

		// Hole mir alle Indikationen
		Query q = em.createQuery("select i from Indikation i");

		// Wir müssten 1 Indikation haben
		assertTrue(q.getResultList().size() == 1);

		// Jetzt mal schauen ob es bereits Ansprechpartner gibt

		// Hole mir alle Ansprechpartner
		q = em.createQuery("select a from Ansprechpartner a");

		// Wir müssten 40 Ansprechpartner haben
		assertTrue(q.getResultList().size() == 40);

		// Jetzt schauen ob es eine Adresse gibt

		// Hole mir alle Adressen
		q = em.createQuery("select b from Adresse b");

		// Wir müssten 41 Adressen haben
		assertTrue(q.getResultList().size() == 41);

		// Hole mir alle Unternehmen
		q = em.createQuery("select u from Unternehmen u");

		// Wir müssten 1 Unternehmen haben
		assertTrue(q.getResultList().size() == 1);

		em.close();
	}
	
	@Test
	public void checkUnternehmen() {
		EntityManager em = AllTests.factory.createEntityManager();
		// Jetzt schauen wir uns mal die Beziehung der Unternehmen mit den
		// Ansprechpartnern, der Adresse und den Indikationen an
		// Wir müssten 1 Medikament haben und dieses müsste die 40
		// Jahresübersichten haben
		Query q = em.createQuery("select u from Unternehmen u");

		// Wir müssten jetzt 1 Unternehmen mit 40 Ansprechpartnern, 1 Adresse und 1 Indikationhaben
		assertTrue(q.getResultList().size() == 1);
		assertTrue(((Unternehmen) q.getSingleResult()).getAnsprechpartner()
				.size() == 40);
		assertTrue(((Unternehmen) q.getSingleResult()).getAdresse()!=null);
		assertTrue(((Unternehmen) q.getSingleResult()).getErsterSchwerpunkt()!=null);
		assertFalse(((Unternehmen) q.getSingleResult()).getZweiterSchwerpunkt()!=null);
		assertFalse(((Unternehmen) q.getSingleResult()).getDritterSchwerpunkt()!=null);
		em.close();
	}

	@After
	public void tearDown() {
		// Nun räumen wir mal hinter uns auf
		AllTests.deleteAllTables(new String[]{"Ansprechpartner", "Unternehmen", "Indikation", "Adresse"});
	}
}
