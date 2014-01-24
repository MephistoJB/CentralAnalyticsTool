package test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import WorkBooster.model.entitaeten.Jahresuebersicht;

public class TestMain {
	
  private static final String PERSISTENCE_UNIT_NAME = "Entitaeten";
  private static EntityManagerFactory factory;

  public static void main(String[] args) {
    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    EntityManager em = factory.createEntityManager();
    
    // read the existing entries and write to console
    Query q = em.createQuery("select t from Jahresuebersicht t");
    List<Jahresuebersicht> mediList = q.getResultList();
    for (Jahresuebersicht medi : mediList) {
      System.out.println(medi);
    }
    System.out.println("Size: " + mediList.size());

    em.close();
  }
} 