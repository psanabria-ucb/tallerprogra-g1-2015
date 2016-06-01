package bo.edu.ucbcba.Taller.controller;

import bo.edu.ucbcba.Taller.dao.TallerEntityManager;
import bo.edu.ucbcba.Taller.exceptions.ValidationException;
import bo.edu.ucbcba.Taller.model.Sale;
import bo.edu.ucbcba.Taller.model.Stock;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class SaleController {

     public void create( Stock s, String cant, String date){

         int p,r;
         int max=200;
         int min=30;
        Sale sale = new Sale();
         Random rd = new Random();
        if (cant.isEmpty())
            throw new ValidationException("Por favor verífique los campos vacios");

        if (cant.matches("[0-9]{1,100}")) {
            if (cant.length() > 7)
                throw new ValidationException("En el campo cantidad solamente se permite una longitud maxima de 7");
            else
                sale.setcant(Integer.parseInt(cant));
                p = rd.nextInt(max-min+1)+min;//getStockPrice(s);
                r = p * Integer.parseInt(cant);
                sale.setPrice(p);
                sale.settotal(r);
        }
        else{
             throw new ValidationException("En el campo cantidad solamente se permite numeros enteros");
        }

        sale.setstocks(s);

         if (isDateValid(date))
         {
             Date d = new Date(date);
             DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
             String convert = dateFormat.format(d);
             sale.setD(convert);
         }
         else {
             throw new ValidationException("La fecha debe estar escrita en el formato Mes/Día/Año, corrija el dato para continuar");
         }

         EntityManager entityManager = TallerEntityManager.createEntityManager();
         entityManager.getTransaction().begin();
         entityManager.persist(sale);
         entityManager.getTransaction().commit();
         entityManager.close();
    }

    public static int getStockPrice(Stock s) {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        TypedQuery query = entityManager.createQuery("select cost from Stock WHERE s=name ORDER BY s", Stock.class);
        //query.setParameter("d", "%" + s.toLowerCase() + "%");
        int response = query.getFirstResult();
        entityManager.close();
        return response;
    }

    public boolean isDateValid(String date)
    {
        String DATE_FORMAT = "MM/dd/yyyy";
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public List<Sale> searchSalebydate(String q) {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        TypedQuery<Sale> query = entityManager.createQuery("select m from Sale m WHERE lower(m.d) like :d", Sale.class);
        query.setParameter("d", "%" + q.toLowerCase() + "%");
        List<Sale> response = query.getResultList();
        entityManager.close();
        return response;
    }

    public void delete(int id)
    {
        if (id!=0)
        {
            EntityManager entityManager = TallerEntityManager.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Sale.class, id));
            entityManager.getTransaction().commit();
            StockController.delete(id);
        }
        else
        {
            throw new ValidationException("Seleccione la venta que desea eliminar");
        }
    }

    public Stock getSale(int id)
    {
        if(id!=0)
        {
            EntityManager entityManager = TallerEntityManager.createEntityManager();
            entityManager.getTransaction().begin();
            Stock response = entityManager.find(Stock.class,id);
            entityManager.getTransaction().commit();
            entityManager.close();
            return response;
        }
        else {
            throw new ValidationException("Seleccione la venta que desea editar");
        }
    }

    public List<Sale> show()
    {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        TypedQuery<Sale> query = entityManager.createQuery("select c from Stock c", Sale.class);
        List<Sale> response = query.getResultList();
        entityManager.close();
        return response;
    }
}
