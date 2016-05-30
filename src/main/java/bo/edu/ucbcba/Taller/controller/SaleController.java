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



/**
 * Created by Usuario on 27/05/2016.
 */
public class SaleController {

     public void create(String code, String day,String month,String year,  Stock s, String cant, String total){

        Sale sale = new Sale();

        if (day.isEmpty() || month.isEmpty() || year.isEmpty() || code.isEmpty() || total.isEmpty() || cant.isEmpty())
            throw new ValidationException("Por favor verifique los campos vacios");

        if (code.matches("\\A[^0-9`!@#\\$%\\^&*+_=]+\\z"))
            sale.setCode(code);
        else
            throw new ValidationException("En el campo código solamente se permite letras");

        if (total.matches("[\\-\\+]?[0-9]*(\\.[0-9]+)?"))
            sale.settotal(Integer.parseInt(total));
        else
            throw new ValidationException("En el campo total solamente se permite numeros");

        if (cant.matches("[0-9]{1,10000}"))
            sale.setcant(Integer.parseInt(cant));
        else
            throw new ValidationException("En el campo cantidad solamente se permite numeros enteros");

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
             throw new ValidationException("En el campo Dia ingreso incorrectamente el día");
         }

        EntityManager entityManager = TallerEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(sale);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Sale> searchSale(String q) {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        TypedQuery<Sale> query = entityManager.createQuery("select m from Sale m WHERE lower(m.code) like :code", Sale.class);
        query.setParameter("code", "%" + q.toLowerCase() + "%");
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
