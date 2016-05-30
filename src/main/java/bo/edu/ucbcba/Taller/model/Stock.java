package bo.edu.ucbcba.Taller.model;

/* Stock Entity */

//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import java.util.LinkedList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;

@Entity //Esto es una tabla
public class Stock {

    @Id
    //@SequenceGenerator ( name = "seq" , initialValue = 1 , allocationSize = 100 )
    @GeneratedValue ( strategy = GenerationType.AUTO) //. SEQUENCE , generator = "seq" )
    private int id;
    //hay que corregir el id no se genera automaticamente

    @OneToMany
    private List<Sale> sales;

    public Stock() {
        id = 0;
        name = "";
        cost = 0;
        code = "";
        quantity = 0;
        sales = new LinkedList<>();
    }

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private float cost;

    @Column(length = 15)
    private String code;

    @Column(length = 100)
    private int quantity;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public float getCost() {
        return cost;
    }
    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){this.quantity = quantity;}

    public List<Sale> getsales() {
        return sales;
    }
    public void setsales(List<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return String.format("%s", code);
    }

}
