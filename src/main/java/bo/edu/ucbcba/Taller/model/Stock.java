package bo.edu.ucbcba.Taller.model;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Stock {

    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO)
    private int id;

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
        return String.format("%s", name);
    }

}
