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

        //validacion del ci del cliente
        if(ci.isEmpty()){
            throw new ValidationException("TIENE QUE INTRODUCIR EL CI DEL CLIENTE");
        }
        else {
            if(ci.matches("[0-9]+")){
                if(ci.length()>10){
                    throw new ValidationException ("EL CI DEL CLIENTE NO PUEDE TENER MAS DE 9 DIGITOS");
                }
                else {
                    /*
                    int a = Integer.parseInt(ci);
                    EntityManager entityManager = TallerEntityManager.createEntityManager();
                    TypedQuery<Maintenance> query = entityManager.createQuery("select c from Maintenance c WHERE c.ci =  :a", Maintenance.class);
                    query.setParameter("a", a);
                    List<Maintenance> response = query.getResultList();
                    entityManager.close();
                    */
                    //if (response.isEmpty())
                        maintenance.setCi(Integer.parseInt(ci));
                    //else
                        //throw new ValidationException("El CI DEL CLIENTE YA EXISTE");
                }
            }
            else {
                throw new ValidationException("EL CI DEL CLIENTE TIENE QUE SER NUMERO");
            }
        }

        //valida la placa
        if(pla.length()>7){
            throw new ValidationException("LA PLACA DEBE SER MAXIMO DE 7 CARACTERES");
        }
        else {
            if (pla.isEmpty() || pla == null) {
                throw new ValidationException("TIENE QUE LLENAR EL CAMPO DE LA PLACA");
            } else {
                if (pla.matches("[0-9a-z]+") || pla.matches("[0-9A-Z]+")) {
                    maintenance.setPlaca(pla);
                }
                else {
                    throw new ValidationException("LA PLACA DEL VEHICULO NO ES VALIDO");
                }
            }
        }

        //valida de la marca
        if(mar.length()>15){
            throw new ValidationException("LA PLACA DEBE SER MAXIMO DE 6 CARACTERES");
        }
        else {
            if (mar.isEmpty() || mar == null) {
                throw new ValidationException("TIENE QUE LLENAR EL CAMPO DE LA MARCA DEL VEHICULO");
            } else {
                if (mar.matches("[A-za-z]+") || mar.matches("[A-Z]+") ||mar.matches("[a-z]+")) {
                    maintenance.setMarca(mar);
                }
                else {
                    throw new ValidationException("TIENE QUE INTRODUCIR LETRAS NO NUMERO");
                }
            }
        }

        //validacionde costo

        if(cost.length()>10){
            throw new ValidationException("LONGITUD DE COSTO INVALIDO");
        }
        else {
            if (cost.isEmpty() || cost == null) {
                throw new ValidationException("TIENE QUE LLENAR EL CAMPO DEL COSTO TIENE QUE SER LLENADO");
            } else {
                if (cost.matches("[0-9]+")) {
                    maintenance.setCosto(Integer.parseInt(cost));
                }
                else {
                    throw new ValidationException("EL COSTO TIENE QUE SER NUEMRO");
                }
            }
        }

        //validacion de la descripcion


        if(des.length()>90){
            throw new ValidationException("LONGITUD DE LA DESCRICION NO ES VALIDA");
        }
        else {
            if (des.isEmpty() || des == null) {
                throw new ValidationException("TIENE QUE LLENAR EL CAMPO DE LA DESCRIPCION");
            } else {
                if (!des.matches("[0-9]+")) {
                    maintenance.setDescrip(des);
                }
                else {
                    throw new ValidationException("TIENE QUE LLENAR EL CAMPO DE LA DESCRIPCION");
                }
            }
        }
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(maintenance);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    ///lista los mantenimientos

    public List<Maintenance> show(){
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        TypedQuery<Maintenance> query = entityManager.createQuery("select c from Maintenance c",Maintenance.class);
        List<Maintenance> response = query.getResultList();
        entityManager.close();
        return response;
    }

    public void delete(int ci)
    {
        if(ci!=0) {
            EntityManager entityManager = TallerEntityManager.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Maintenance.class, ci));
            entityManager.getTransaction().commit();
            entityManager.close();
        }
        else {
            throw new ValidationException("ERROR CI INVALIDO");
        }
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
            //throw new ValidationException("TIENE QUE INTRODUCIR EL CI DEL CLIENTE");
            if(q.matches("[0-9a-z]+") || q.matches("[0-9A-Z]+"))
            {

            }
            else {
                throw new ValidationException("TIENE QUE INTRODUCIR EL CI DEL CLIENTE O LA PLACA DEL VEHICULO");
            }
            EntityManager entityManager = TallerEntityManager.createEntityManager();
            TypedQuery<Maintenance> query = entityManager.createQuery("select m from Maintenance m WHERE m.placa = :q",Maintenance.class);
            query.setParameter("q",q);
            List<Maintenance> res = query.getResultList();
            entityManager.close();
            return res;

        }

    }



}



