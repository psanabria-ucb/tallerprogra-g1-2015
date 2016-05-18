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

        if (name.matches("[a-z]+") && username.matches("[a-z]+")) {
            user.setName(name);
            user.setUsername(username);
        }
        else {
            throw new ValidationException("El formulario no esta llenado correctamente");
        }


        EntityManager entityManager = TallerEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<User> search(String q) {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        TypedQuery<User> query = entityManager.createQuery("select m from User m WHERE lower(m.name) like :name", User.class);
        query.setParameter("name", "%" + q.toLowerCase() + "%");
        List<User> response = query.getResultList();
        entityManager.close();
        return response;
    }
    public void delete(int id)
    {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(User.class,id));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
