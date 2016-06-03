package bo.edu.ucbcba.Taller.model;

/**
 * Created by Osmar on 31/05/2016.
 */
import javax.persistence.*;
import javax.xml.bind.ValidationException;

@Entity
public class Vehiculo {
    @Id
    @Column(length = 100)
    private String placa;

    @Column(length = 100)
    private String color;

    @Column(length = 100)
    private String marca;

    @Column(length = 100)
    private int modelo;

    @Column(length = 100)
    private String origen;

    @ManyToOne
    private Customer customer;

    public String getPlaca() {
        return placa;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPlaca(String placa) {

        this.placa = placa;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }
}

