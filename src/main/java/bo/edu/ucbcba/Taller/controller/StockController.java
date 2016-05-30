package bo.edu.ucbcba.Taller.controller;

import bo.edu.ucbcba.Taller.model.Stock;
import bo.edu.ucbcba.Taller.dao.TallerEntityManager;
import bo.edu.ucbcba.Taller.exceptions.ValidationException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class StockController {



    public void create(String name, String cost, String code, String quantity) {

        EntityManager entityManager = TallerEntityManager.createEntityManager();
        entityManager.getTransaction().begin();

        Stock stock = new Stock();

        if (name.isEmpty() || cost.isEmpty() || code.isEmpty() || quantity.isEmpty())
            throw new ValidationException("Por favor verifique los campos vacios");


        if (name.matches("\\A[^0-9`!@#\\$%\\^&*+_=]+\\z"))
            stock.setName(name);
        else
            throw new ValidationException("En el campo nombre solamente se permite letras");

        if (cost.matches("[\\-\\+]?[0-9]*(\\.[0-9]+)?"))
            stock.setCost(Float.parseFloat(cost));
        else
            throw new ValidationException("En el campo costo solamente se permite numeros y decimales");

        if (code.matches("[a-zA-Z]{1,1000}")){
                EntityManager em = TallerEntityManager.createEntityManager();
                TypedQuery<Stock> query = em.createQuery("select c from Stock c WHERE c.code = :a", Stock.class);
                query.setParameter("a", code);
                List<Stock> response = query.getResultList();
                em.close();
                if (response.isEmpty())
                    stock.setCode(code);
                else
                    throw new ValidationException("El código ya existe");
        }
        else {
            throw new ValidationException("En el campo código solamente se permite letras");
        }

        if (quantity.matches("[0-9]{1,10000}"))
            stock.setQuantity(Integer.parseInt(quantity));
        else
            throw new ValidationException("En el campo cantidad solamente se permite numeros enteros");


        entityManager.persist(stock);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Stock> getAllStock() {
        EntityManager em = TallerEntityManager.createEntityManager();
        TypedQuery<Stock> query = em.createQuery("select d from Stock d order by d.code", Stock.class);
        List<Stock> list = query.getResultList();
        em.close();
        return list;
    }

    public List<Stock> show()
    {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        TypedQuery<Stock> query = entityManager.createQuery("select c from Stock c", Stock.class);
        List<Stock> response = query.getResultList();
        entityManager.close();
        return response;
    }

    public List<Stock> searchStockbyname(String q) {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        TypedQuery<Stock> query = entityManager.createQuery("select m from Stock m WHERE lower(m.name) like :name", Stock.class);
        query.setParameter("name", "%" + q.toLowerCase() + "%");
        List<Stock> response = query.getResultList();
        entityManager.close();
        return response;
    }

    public List<Stock> searchStockbycode(String q) {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        TypedQuery<Stock> query = entityManager.createQuery("select m from Stock m WHERE lower(m.code) like :code", Stock.class);
        query.setParameter("code", "%" + q.toLowerCase() + "%");
        List<Stock> response = query.getResultList();
        entityManager.close();
        return response;
    }

    public void delete(int ci)
    {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
       // if (ci==(-1))
         //   throw new ValidationException("Porfavor seleccione un repuesto para eliminar");
        //else
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Stock.class, ci));
            entityManager.getTransaction().commit();
            entityManager.close();
    }
}
