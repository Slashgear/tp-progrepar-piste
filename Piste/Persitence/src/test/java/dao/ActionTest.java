package dao;

import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * @author Alexandre
 *         27/05/2015
 */
public class ActionTest {
    private EntityManager em;

    @Before
    public void setUp() throws Exception {
        em = Persistence.createEntityManagerFactory("NewPersistenceUnit").createEntityManager();
        em.getTransaction().begin();
        Regle regle = new Regle();
        regle.setLibregle("blabla");
        regle.setScoremin(10);
        em.persist(regle);
        em.getTransaction().commit();
    }

    @Test
    public void testName() throws Exception {

    }
}