package bo.edu.ucbcba.Taller.controller;


import bo.edu.ucbcba.Taller.model.User;
import bo.edu.ucbcba.Taller.dao.TallerEntityManager;
import bo.edu.ucbcba.Taller.exceptions.ValidationException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


public class UserController {

    public void create(String name,
                       String username,
                       String password) {

        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);

        //  if (cost >= 60)
        //     throw new ValidationException("Minutes can't be greater than 59");
        // stock.setCost(1000);


        //if (quantity >= 60)
        //   throw new ValidationException("Minutes can't be greater than 59");
        //stock.setQuantity(100);


        EntityManager entityManager = TallerEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<User> searchStock(String q) {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        TypedQuery<User> query = entityManager.createQuery("select m from User m WHERE lower(m.name) like :name", User.class);
        query.setParameter("name", "%" + q.toLowerCase() + "%");
        List<User> response = query.getResultList();
        entityManager.close();
        return response;
    }

}
