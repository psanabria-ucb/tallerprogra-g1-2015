package bo.edu.ucbcba.Taller.controller;


import bo.edu.ucbcba.Taller.dao.TallerEntityManager;
import bo.edu.ucbcba.Taller.exceptions.ValidationException;
import bo.edu.ucbcba.Taller.model.Maintenance;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Osmar on 17/05/2016.
 */
public class MaintenanceController {

    public void create(String ci,String pla,String mar, String cost,String des)
    {
        Maintenance maintenance = new Maintenance();
       if(ci.matches("[0-9]+") && pla.matches("[a-z]+") && mar.matches("[a-z]+") && cost.matches("[0-9]+") && des.matches("[a-z]+"))
       {
            maintenance.setCi(Integer.parseInt(ci));
            maintenance.setPlaca(pla);
            maintenance.setMarca(mar);
            maintenance.setCosto(Integer.parseInt(cost));
            maintenance.setDescrip(des);
        }
        else
       {
            throw new ValidationException("TIENE QUE LLENAR EL FORMULARIO");
        }
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(maintenance);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Maintenance> show(){
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        TypedQuery<Maintenance> query = entityManager.createQuery("select c from Maintenance c",Maintenance.class);
        List<Maintenance> response = query.getResultList();
        entityManager.close();
        return response;
    }

    public void delete(int ci)
    {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Maintenance.class,ci));
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    public List<Maintenance> searhCI(String q){
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
            TypedQuery<Maintenance> query = entityManager.createQuery("select m from Maintenance m WHERE m.ci = :a",Maintenance.class);
            query.setParameter("a",a);
            List<Maintenance> res = query.getResultList();
            entityManager.close();
            return res;
        }
        else {
            throw new ValidationException("EL CAMPO DEBE SER UN NUMERO PARA BUSCAR");
        }
    }


}
