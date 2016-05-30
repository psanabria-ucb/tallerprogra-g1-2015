package bo.edu.ucbcba.Taller.model;

/**
 * Created by Usuario on 27/05/2016.
 */

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
    public void setD(String d){this.d=d;}

    @Column(length = 50)
    private String code;

    public String getCode(){return code;}
    public void setCode (String code){this.code=code;}

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


}

