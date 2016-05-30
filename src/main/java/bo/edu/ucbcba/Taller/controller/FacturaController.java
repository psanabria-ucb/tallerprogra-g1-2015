package bo.edu.ucbcba.Taller.controller;

/**
 * Created by Rebeca on 18/05/2016.
 */
import bo.edu.ucbcba.Taller.dao.TallerEntityManager;
import bo.edu.ucbcba.Taller.exceptions.ValidationException;
import bo.edu.ucbcba.Taller.model.Factura;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class FacturaController {

    public void create(String ci,String nombre,String date, String cost,String des)
    {
        Factura maintenance = new Factura();
        if (ci.matches("[0-9]+"))
            maintenance.setCi(Integer.parseInt(ci));
        else
            throw new ValidationException("ci no es un numero");

        //NOMBRE
        if (nombre.matches("[a-zA-Z]+"))
            maintenance.setNombre(nombre);
        else
            throw new ValidationException("Nombre solo admite letras");
        //COSTO
        if (cost.matches("[0-9]+"))
            maintenance.setCosto(Integer.parseInt(cost));
        else
            throw new ValidationException("El costo no es un numero");
        //descrip
        maintenance.setDescrip(des);




        EntityManager entityManager = TallerEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(maintenance);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Factura> show(){
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        TypedQuery<Factura> query = entityManager.createQuery("select c from Factura c",Factura.class);
        List<Factura> response = query.getResultList();
        entityManager.close();
        return response;
    }

    public void delete(int id)
    {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Factura.class,id));
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    public List<Factura> searhCI(String q){
        int a;
        if(q.matches("[0-9]+")){
            if(q.isEmpty())
            {
                a=0;
            }
            else {
                a=Integer.parseInt(q);
            }
            EntityManager entityManager = TallerEntityManager.createEntityManager();
            TypedQuery<Factura> query = entityManager.createQuery("select m from Factura m WHERE m.ci = :a",Factura.class);
            query.setParameter("a",a);
            List<Factura> res = query.getResultList();
            entityManager.close();
            return res;
        }
        else {
            throw new ValidationException("EL CAMPO DEBE SER UN NUMERO PARA BUSCAR");
        }
    }
}
