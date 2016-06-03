package bo.edu.ucbcba.Taller.controller;

import bo.edu.ucbcba.Taller.dao.TallerEntityManager;
import bo.edu.ucbcba.Taller.exceptions.ValidationException;
import bo.edu.ucbcba.Taller.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Rebeca on 01/06/2016.
 */
public class CustomerController {

    public void create(String ci,String firstsName,String lastNameF, String lastNameM,String numberPhono, String address)
    {
        Customer maintenance = new Customer();

        if (ci.matches("[0-9]+")){
            if(ci.length()>7){
                throw new ValidationException("EL CI DEL CLIENTE NO PUEDE TENER MAS DE 7 DIGITOS");
            }
            else {
                maintenance.setCi(Integer.parseInt(ci));
            }

        }
        else
            throw new ValidationException("ci no es un numero");

        /*
        if(ci.isEmpty()){
            throw new ValidationException("TIENE QUE INTRODUCIR EL CI DEL CLIENTE");
        }
        else {
            if(ci.matches("[0-9]+")){
                if(ci.length()>10){
                    throw new ValidationException ("EL CI DEL CLIENTE NO PUEDE TENER MAS DE 9 DIGITOS");
                }
                else {
                    int a = Integer.parseInt(ci);
                    EntityManager entityManager = TallerEntityManager.createEntityManager();
                    TypedQuery<Customer> query = entityManager.createQuery("select c from Customer c WHERE c.ci =  :a", Customer.class);
                    query.setParameter("a", a);
                    List<Customer> response = query.getResultList();
                    entityManager.close();
                    if(response.isEmpty()){
                        maintenance.setCi(Integer.parseInt(ci));
                    }
                    else{
                        throw new ValidationException("El CI DEL CLIENTE YA EXISTE");
                    }
                }
            }
            else {
                throw new ValidationException("EL CI DEL CLIENTE TIENE QUE SER NUMERO");
            }
        }
        */
        //NOMBRE
        if (firstsName.matches("[a-zA-Z]+"))
        {
            if(firstsName.length()>20){
                throw new ValidationException ("EL NOMBRE DEL CLIENTE NO PUEDE TENER MAS DE 10 CARACTERES");
            }
            else {
                maintenance.setFirtsName(firstsName);
            }

        }
        else
            throw new ValidationException("Nombre solo admite letras");



        if (lastNameF.matches("[a-zA-Z]+"))
        {
            if(lastNameF.length()>20){
                throw new ValidationException ("EL APELLIDO PATERNO DEL CLIENTE NO PUEDE TENER MAS DE 10 CARACTERES");
            }
            else {
                maintenance.setLastNameF(lastNameF);
            }

        }
        else
            throw new ValidationException("Apellido Paterno solo admite letras");


        if (lastNameM.matches("[a-zA-Z]+"))
        {
            if(lastNameM.length()>20){
                throw new ValidationException ("EL APELLIDO MATERNO DEL CLIENTE NO PUEDE TENER MAS DE 10 CARACTERES");
            }
            else {
                maintenance.setLastNameM(lastNameM);
            }

        }
        else
            throw new ValidationException("Apellido MATERNO solo admite letras");
        //COSTO

        if (numberPhono.matches("[0-9]+"))
        {
            if(numberPhono.length()>15){
                throw new ValidationException ("TELEFONO DEL CLIENTE NO PUEDE TENER MAS DE 15 CIFRA");
            }
            else {
                maintenance.setNumberPhone(Integer.parseInt(numberPhono));
            }

        }

        else
            throw new ValidationException("Telefono no es un numero");

        if (address.matches("[a-zA-Z]+"))
        {
            if(address.length()>70){
                throw new ValidationException ("DIRECCION DEL CLIENTE NO PUEDE TENER MAS DE 70 CARACTERES");
            }
            else {
                maintenance.setAddress(address);
            }

        }
        else
            throw new ValidationException("Direccion solo admite letras");
        //descrip







        EntityManager entityManager = TallerEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(maintenance);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Customer> show(){
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        TypedQuery<Customer> query = entityManager.createQuery("select c from Customer c",Customer.class);
        List<Customer> response = query.getResultList();
        entityManager.close();
        return response;
    }

    public void delete(int id)
    {
        if(id!=0) {

            EntityManager entityManager = TallerEntityManager.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Customer.class, id));
            entityManager.getTransaction().commit();
            entityManager.close();
        }
        else
        {
            throw new ValidationException("Seleccione un campo ");
        }


    }

    public void delete2(int id)
    {
       // if(id!=0) {

            EntityManager entityManager = TallerEntityManager.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Customer.class, id));
            entityManager.getTransaction().commit();
            entityManager.close();
      /*  }
        else
        {
            throw new ValidationException("Seleccione un campo ");
        }
*/

    }



    public List<Customer> searhCI(String q){
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
            TypedQuery<Customer> query = entityManager.createQuery("select m from Customer m WHERE m.Ci = :a",Customer.class);
            query.setParameter("a",a);
            List<Customer> res = query.getResultList();
            entityManager.close();
            return res;
        }
        else {
            throw new ValidationException("EL CAMPO DEBE SER UN NUMERO PARA BUSCAR");
        }
    }


    public List<Customer> searchCustomerByFirstName(String q) {
      /*  int a;
        if(q.matches("[a-zA-Z]+")){
            if(q.isEmpty())
            {
                a=0;
            }
            else {
                a=Integer.parseInt(q);
            }*/

        EntityManager entityManager = TallerEntityManager.createEntityManager();
        TypedQuery<Customer> query = entityManager.createQuery("select c from Customer c WHERE lower(c.firtsName) like :firtsName", Customer.class);
        query.setParameter("firtsName", "%" + q.toLowerCase() + "%");
        List<Customer> response = query.getResultList();
        entityManager.close();
        return response;

    }

    public List<Customer> searchCustomerByFathersLastName(String q) {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        TypedQuery<Customer> query = entityManager.createQuery("select c from Customer c WHERE lower(c.lastNameM) like :lastNameM", Customer.class);
        query.setParameter("lastNameM", "%" + q.toLowerCase() + "%");
        List<Customer> response = query.getResultList();
        entityManager.close();
        return response;
    }


    public List<Customer> searchCustomerByMothersLastName(String q) {
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        TypedQuery<Customer> query = entityManager.createQuery("select c from Customer c WHERE lower(c.lastNameF) like :lastNameF", Customer.class);
        query.setParameter("lastNameF", "%" + q.toLowerCase() + "%");
        List<Customer> response = query.getResultList();
        entityManager.close();
        return response;
    }

    public List<Customer> getAllCustomer(){
        EntityManager em = TallerEntityManager.createEntityManager();
        TypedQuery<Customer> query = em.createQuery("select d from Customer d order by d.firstName", Customer.class);
        List<Customer> list = query.getResultList();
        em.close();
        return list;
    }


}
