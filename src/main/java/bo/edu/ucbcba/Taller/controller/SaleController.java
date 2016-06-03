package bo.edu.ucbcba.Taller.controller;

import bo.edu.ucbcba.Taller.dao.TallerEntityManager;
import bo.edu.ucbcba.Taller.exceptions.ValidationException;
import bo.edu.ucbcba.Taller.model.Sale;
import bo.edu.ucbcba.Taller.model.Stock;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SaleController {

     public void create( Stock s, String cant, String date){

         float p,r;
         int c=getStockCantidad(s);
        Sale sale = new Sale();
        if (cant.isEmpty() || date.isEmpty())
            throw new ValidationException("Por favor verífique los campos vacios");

        if (cant.matches("[0-9]{1,100}"))
        {
            if (Integer.parseInt(cant) > c)
                    throw new ValidationException("En el campo cantidad no se puede ingresar un numero mayor al stock del repuesto");
            else
                    sale.setcant(Integer.parseInt(cant));
                    p = getStockPrice(s);
                    r = p * Integer.parseInt(cant);
                    sale.setPrice(p);
                    sale.settotal(r);
        }
        else
        {
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

         int resultado= c - Integer.parseInt(cant);
         System.out.println(resultado);
         setStockPrice(s,resultado);
    }

    public static float getStockPrice(Stock s) {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        TypedQuery query = entityManager.createQuery("select x from Stock x WHERE x.name='"+s+"'", Stock.class);
        List<Stock> res = query.getResultList();
        float response=res.get(0).getCost();
        System.out.println(response);
        entityManager.close();
        return response;
    }

    public static void setStockPrice(Stock s,int resultado) {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        TypedQuery query = entityManager.createQuery("select x from Stock x WHERE x.name='"+s+"'", Stock.class);
        List<Stock> res = query.getResultList();
        int id=res.get(0).getId();
        entityManager.close();
        updatequantity(id,resultado);
    }

    public static void updatequantity(int id,int quantity)
    {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        System.out.println(quantity+"---"+id);
        entityManager.getTransaction().begin();
        TypedQuery query = entityManager.createQuery("update Stock set quantity ='"+quantity+"' WHERE id ="+id, Stock.class);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        System.out.println("quaN"+quantity);
        entityManager.close();
    }

    public static int getStockCantidad(Stock s) {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        TypedQuery query = entityManager.createQuery("select x from Stock x WHERE x.name='"+s+"'", Stock.class);
        List<Stock> res = query.getResultList();
        int response=res.get(0).getQuantity();
        System.out.println("qua"+response);
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
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        if (id!=0)
        {
                entityManager.getTransaction().begin();
                entityManager.remove(entityManager.find(Sale.class, id));
                entityManager.getTransaction().commit();
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
