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

    private int total;

    public int gettotal(){return total;}
    public void settotal(int total){this.total=total;}

    private int cant;

    public int getcant(){return cant;}
    public void setcant(int cant){this.cant=cant;}

    private int price;

    public int getPrice(){return price;}
    public void setPrice(int price){this.price=price;}
}

