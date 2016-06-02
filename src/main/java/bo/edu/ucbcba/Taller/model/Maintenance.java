package bo.edu.ucbcba.Taller.model;

/**
 * Created by Osmar on 17/05/2016.
 */

import javax.persistence.*;
@Entity
public class Maintenance
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 50)
    private int ci;

    //@Column(length = 100)
    //private String placa;
    @Column(length = 100)
    private String placa;

    @Column(length = 100)
    private String marca;

    @Column(length = 100)
    private int costo;

    @Lob
    @Column(length = 500)
    private String descrip;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getPlaca(){
        return  placa;
    }

    public  void setPlaca(String placa){
        this.placa = placa;
    }
    /*
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
    */

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
}
