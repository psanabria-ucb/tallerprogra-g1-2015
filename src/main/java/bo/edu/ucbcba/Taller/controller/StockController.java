package bo.edu.ucbcba.Taller.controller;

import bo.edu.ucbcba.Taller.model.Stock;
import bo.edu.ucbcba.Taller.dao.TallerEntityManager;
import bo.edu.ucbcba.Taller.exceptions.ValidationException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.*;
import java.util.List;

public class StockController {

    public void create(String name, String cost, String code, String quantity) {

        Stock stock = new Stock();

        if (quantity.matches("[0-9]+") && cost.matches("[0-9]+") && name.matches("[a-z]+") && code.matches("[a-z]+")) {
            stock.setQuantity(Integer.parseInt(quantity));
            stock.setCost(Integer.parseInt(cost));
            stock.setName(name);
            stock.setCode(code);
        }
        else {
            throw new ValidationException("El formulario no esta llenado correctamente");
        }

        EntityManager entityManager = TallerEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(stock);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Stock> searchStock(String q) {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        TypedQuery<Stock> query = entityManager.createQuery("select m from Stock m WHERE lower(m.name) like :name", Stock.class);
        query.setParameter("name", "%" + q.toLowerCase() + "%");
        List<Stock> response = query.getResultList();
        entityManager.close();
        return response;
    }

    public void delete(int ci)
    {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Stock.class,ci));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
