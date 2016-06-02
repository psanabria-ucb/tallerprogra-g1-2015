package bo.edu.ucbcba.Taller.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Usuario on 16/05/2016.
 */

// This is a singleton that give us a entity manager to manage
// in the entire DAO
public class TallerEntityManager {
    private static TallerEntityManager entityManager;

    private EntityManagerFactory entityManagerFactory;

    private TallerEntityManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Taller");//
    }

    private static TallerEntityManager getInstance() {
        if (entityManager == null)
            entityManager = new TallerEntityManager();
        return entityManager;
    }

    public static EntityManager createEntityManager() {
        return getInstance().entityManagerFactory.createEntityManager();
    }
}