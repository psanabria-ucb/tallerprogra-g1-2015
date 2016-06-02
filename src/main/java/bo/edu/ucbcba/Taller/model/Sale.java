package bo.edu.ucbcba.Taller.model;

import bo.edu.ucbcba.Taller.exceptions.ValidationException;

import javax.persistence.*;

@Entity
public class Sale {
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO)
    private int id;

    public int getId(){return id;}
    public void setId(int id){this.id=id;}

    private String d;

    public String getD(){return d;}
    public void setD(String d){
        if (d == null)
            throw new ValidationException("Null title");
        if (d.isEmpty())
            throw new ValidationException("Title can't be empty");
        if (d.length() > 255)
            throw new ValidationException("Title is too long");
        this.d=d;
    }

    @ManyToOne
    private Stock stocks;

    public Stock getstocks(){return stocks;}
    public void setstocks(Stock stocks){this.stocks=stocks;}

    private float total;

    public float gettotal(){return total;}
    public void settotal(float total){this.total=total;}

    private int cant;

    public int getcant(){return cant;}
    public void setcant(int cant){this.cant=cant;}

    private float price;

    public float getPrice(){return price;}
    public void setPrice(float price){this.price=price;}
}

