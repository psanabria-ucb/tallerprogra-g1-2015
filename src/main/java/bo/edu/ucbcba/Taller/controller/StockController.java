package bo.edu.ucbcba.Taller.controller;

import bo.edu.ucbcba.Taller.model.Stock;
import bo.edu.ucbcba.Taller.dao.TallerEntityManager;
import bo.edu.ucbcba.Taller.exceptions.ValidationException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class StockController {

    public void create(String name,
                       String cost,
                       String code,
                       String quantity) {

        Stock stock = new Stock();
        stock.setName(name);
        stock.setCode(code);



      //  if (cost >= 60)
       //     throw new ValidationException("Minutes can't be greater than 59");
       // stock.setCost(1000);


        //if (quantity >= 60)
         //   throw new ValidationException("Minutes can't be greater than 59");
        //stock.setQuantity(100);


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

}
