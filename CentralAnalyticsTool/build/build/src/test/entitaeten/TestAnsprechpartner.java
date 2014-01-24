package test.entitaeten;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import WorkBooster.model.entitaeten.Adresse;
import WorkBooster.model.entitaeten.Ansprechpartner;
import WorkBooster.model.entitaeten.Indikation;
import WorkBooster.model.entitaeten.Unternehmen;

public class TestAnsprechpartner {

	@Before
	public void setUp() throws Exception {

		EntityManager em = AllTests.factory.createEntityManager();

		// Starte eine Transaktion, damit wir jede neue Entität registrieren
		em.getTransaction().begin();

		// Lies alle Entitäten ein
		Query q = em.createQuery("select a from Ansprechpartner a");

		// Haben wir Entitäten?
		boolean createNewEntries = (q.getResultList().size() == 0);

		// Wenn keine da sind, erstellen wir welche
		if (createNewEntries) {
			for (int i = 0; i < 40; i++) {
				Adresse adr = new Adresse();
				adr.setStrasse((i + 1) + "Teststrasse");
				adr.setHausnummer("" + i * 2);
				adr.setPlz("" + i * 100);
				adr.setOrt((i + 1) + "Testort");
				em.persist(adr);
				
				Indikation indi = new Indikation();
				indi.setErsterIndikationsschluesselteil("40");
				indi.setZweiterIndikationsschluesselteil("B123");
				em.persist(indi);
				
				Unternehmen unternehmen = new Unternehmen();
				unternehmen.setAdresse(adr);
				unternehmen.setErsterSchwerpunkt(indi);
				unternehmen.setZweiterSchwerpunkt(null);
				em.persist(unternehmen);

				Ansprechpartner partner = new Ansprechpartner();
				partner.setName("Partner " + i);
				partner.setPosition("Position " + i);
				partner.setVorname("Vorname " + i);
				partner.setTelefonnummer(""
						+ Math.round((i * Math.random() * 1000000)));
				partner.setAdresse(adr);

				em.persist(partner);
				
				unternehmen.addAnsprechpartner(partner);
				em.persist(unternehmen);
				partner.setUnternehmen(unternehmen);
				em.persist(partner);
			}

			// Commit, damit die gespeicherte Transaktion übernommen wird
			em.getTransaction().commit();

			// Schön Ressourcen sparen
			em.close();
		}

	}

	@Test
	public void checkAvailableAnsprechpartner() {

		// Jetzt mal schauen ob es bereits Ansprechpartner gibt
		EntityManager em = AllTests.factory.createEntityManager();

		// Hole mir alle Ansprechpartner
		Query q = em.createQuery("select a from Ansprechpartner a");

		// Wir müssten 40 Ansprechpartner haben
		assertTrue(q.getResultList().size() == 40);

		em.close();
	}

	@Test
	public void checkAnsprechpartner() {
		EntityManager em = AllTests.factory.createEntityManager();
		// Jetzt schauen wir uns mal die Beziehung der Medikamente mit den
		// Jahresübersichten an
		// Wir müssten 1 Medikament haben und dieses müsste die 40
		// Jahresübersichten haben
		Query q = em.createQuery("select a from Ansprechpartner a");

		// Wir müssten jetzt 40 Ansprechpartner mit jeweils 1 Adresse haben
		assertTrue(q.getResultList().size() == 40);
		List<?> list = q.getResultList();
		for (Object o : list)
			assertTrue(((Ansprechpartner) o).getAdresse() != null);
		em.close();
	}

	@After
	public void tearDown() {
		// Nun räumen wir mal hinter uns auf
		AllTests.deleteAllTables(new String[]{"Ansprechpartner", "Unternehmen", "Indikation", "Adresse"});
	}

}
