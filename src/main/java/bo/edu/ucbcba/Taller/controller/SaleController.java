package bo.edu.ucbcba.Taller.controller;

import bo.edu.ucbcba.Taller.dao.TallerEntityManager;
import bo.edu.ucbcba.Taller.exceptions.ValidationException;
import bo.edu.ucbcba.Taller.model.Sale;
import bo.edu.ucbcba.Taller.model.Stock;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SaleController {

     public void create(String cant, Stock s, String day, String month, String year){

         int p,r;
        Sale sale = new Sale();

        if (day.isEmpty() || month.isEmpty() || year.isEmpty() || cant.isEmpty())
            throw new ValidationException("Por favor verífique los campos vacios");

        if (cant.matches("[0-9]{1,100}")) {
            if (cant.length() > 7)
                throw new ValidationException("En el campo cantidad solamente se permite una longitud maxima de 7");
            else
                sale.setcant(Integer.parseInt(cant));
                p = getStockPrice(s);
                r = p * Integer.parseInt(cant);
                sale.setPrice(p);
                sale.settotal(r);
        }
        else{
             throw new ValidationException("En el campo cantidad solamente se permite numeros enteros");
        }

        sale.setstocks(s);

         if ((Integer.parseInt(day)>0) && (Integer.parseInt(day)<32)) {
             if ((Integer.parseInt(month)>0) && (Integer.parseInt(month)<13)) {
                 if (Integer.parseInt(year) < 1990) {
                     throw new ValidationException("En el campo Año ingreso año inválido");
                 } else {
                     LocalDate today = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
                     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                     sale.setD(formatter.format(today));
                 }
             } else {
                 throw new ValidationException("En el campo Mes ingreso incorrectamente el mes");
             }
         }
         else {
             throw new ValidationException("En el campo Día ingreso incorrectamente el día");
         }

         EntityManager entityManager = TallerEntityManager.createEntityManager();
         entityManager.getTransaction().begin();
         entityManager.persist(sale);
         entityManager.getTransaction().commit();
         entityManager.close();
    }

    public int getStockPrice(Stock s) {
        EntityManager em = TallerEntityManager.createEntityManager();
        TypedQuery query = em.createQuery("select d from Stock d WHERE GROUP BY.s d.cost", Stock.class);
        int list = query.getFirstResult();
        em.close();
        return list;
    }

    public List<Sale> searchSale(String q) {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        TypedQuery<Sale> query = entityManager.createQuery("select m from Sale m WHERE lower(m.d) like :d", Sale.class);
        query.setParameter("d", "%" + q.toLowerCase() + "%");
        List<Sale> response = query.getResultList();
        entityManager.close();
        return response;
    }

    public void delete(int id)
    {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Sale.class, id));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
