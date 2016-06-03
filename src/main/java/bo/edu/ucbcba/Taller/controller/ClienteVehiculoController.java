package bo.edu.ucbcba.Taller.controller;

import bo.edu.ucbcba.Taller.dao.TallerEntityManager;
import bo.edu.ucbcba.Taller.exceptions.ValidationException;
import bo.edu.ucbcba.Taller.model.Customer;
import bo.edu.ucbcba.Taller.model.Maintenance;
import bo.edu.ucbcba.Taller.model.Vehiculo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Osmar on 02/06/2016.
 */
public class ClienteVehiculoController {
    public void create(String placa, String color, String marca, String modelo, String origen){
        //Customer customer = new Customer();
        Vehiculo vehiculo=new Vehiculo();

        ///registro de vehiculo
        if(placa.length()>7){
            throw new ValidationException("LA PLACA DEBE SER MAXIMO DE 7 CARACTERES");
        }
        else {
            if (placa.isEmpty() || placa == null) {
                throw new ValidationException("TIENE QUE LLENAR EL CAMPO DE LA PLACA");
            } else {
                if (placa.matches("[0-9a-z]+") || placa.matches("[0-9A-Z]+")) {
                    //vehiculo.setPlaca(placa);
                    if(placa.length()>7){
                        throw new ValidationException("LA LONGITUD NO ES VALIDA");
                    }
                    else {
                        EntityManager entityManager = TallerEntityManager.createEntityManager();
                        TypedQuery<Vehiculo> query = entityManager.createQuery("select c from Vehiculo c WHERE c.placa =  :placa",Vehiculo.class);
                        query.setParameter("placa",placa);
                        List<Vehiculo> response = query.getResultList();
                        entityManager.close();
                        if(response.isEmpty()){
                            vehiculo.setPlaca(placa);
                        }
                        else {
                            throw new ValidationException("VEHICULO YA EXISTE");
                        }
                    }
                }
                else {
                    throw new ValidationException("LA PLACA DEL VEHICULO NO ES VALIDO");
                }
            }
        }

        if(color.isEmpty() || color==null){
            throw new ValidationException("TIENE QUE LLENAR EL CAMPO DE COLOR");
        }else {
            if(color.matches("[a-z]+") || color.matches("[A-Z]+")){
                vehiculo.setColor(color);
            }
            else {
                throw new ValidationException("COLOR DEL VEHICULO NO ES VALIDO");
            }
        }

        //marca
        if(marca.isEmpty() || marca==null){
            throw new ValidationException("TIENE QUE LLENAR EL CAMPO DE LA MARCA");
        }else {
            if(marca.matches("[a-z]+") || marca.matches("[A-Z]+")){
                vehiculo.setMarca(marca);
            }
            else {
                throw new ValidationException("LA MARCA DEL VEHICULO NO ES VALIDA");
            }
        }

        //modelo
        if(modelo.isEmpty() || marca==null){
            throw new ValidationException("TIENE QUE LLENAR EL CAMPO DEL MODELO");
        }else {
            if(modelo.matches("[0-9]+")){
                vehiculo.setModelo(Integer.parseInt(modelo));
            }
            else {
                throw new ValidationException("MODELO DE VEHICULO NO VALIDO");
            }
        }
        //origen

        if(origen.isEmpty() || origen==null){
            throw new ValidationException("TIENE QUE LLENAR EL CAMPO DE LA MARCA");
        }else {
            if(origen.matches("[a-z]+") || origen.matches("[A-Z]+") || origen.matches("[A-Za-z]+")){
                vehiculo.setOrigen(origen);
            }
            else {
                throw new ValidationException("ORIGEN DE  VEHICULO NO ES VALIDA");
            }
        }


        EntityManager entityManager = TallerEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        //entityManager.persist(customer);
        entityManager.persist(vehiculo);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Vehiculo> show(){
        EntityManager entityManager = TallerEntityManager.createEntityManager();
        TypedQuery<Vehiculo> query = entityManager.createQuery("select m from Vehiculo m ",Vehiculo.class);
        List<Vehiculo> response = query.getResultList();
        entityManager.close();
        return response;
    }

}
