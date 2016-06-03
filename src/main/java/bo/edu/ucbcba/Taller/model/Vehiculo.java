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
        if (placa == null)
            throw new bo.edu.ucbcba.Taller.exceptions.ValidationException("Null title");
        if (placa.isEmpty())
            throw new bo.edu.ucbcba.Taller.exceptions.ValidationException("Title can't be empty");
        if (placa.length() > 255)
            throw new bo.edu.ucbcba.Taller.exceptions.ValidationException("Title is too long");
        this.placa = placa;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color == null)
            throw new bo.edu.ucbcba.Taller.exceptions.ValidationException("Null title");
        if (color.isEmpty())
            throw new bo.edu.ucbcba.Taller.exceptions.ValidationException("Title can't be empty");
        if (color.length() > 255)
            throw new bo.edu.ucbcba.Taller.exceptions.ValidationException("Title is too long");
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        if (marca == null)
            throw new bo.edu.ucbcba.Taller.exceptions.ValidationException("Null title");
        if (marca.isEmpty())
            throw new bo.edu.ucbcba.Taller.exceptions.ValidationException("Title can't be empty");
        if (marca.length() > 255)
            throw new bo.edu.ucbcba.Taller.exceptions.ValidationException("Title is too long");
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
        if (origen == null)
            throw new bo.edu.ucbcba.Taller.exceptions.ValidationException("Null title");
        if (origen.isEmpty())
            throw new bo.edu.ucbcba.Taller.exceptions.ValidationException("Title can't be empty");
        if (origen.length() > 255)
            throw new bo.edu.ucbcba.Taller.exceptions.ValidationException("Title is too long");
        this.origen = origen;
    }
}

