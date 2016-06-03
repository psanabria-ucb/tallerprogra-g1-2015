package bo.edu.ucbcba.Taller.model;

import bo.edu.ucbcba.Taller.exceptions.ValidationException;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Rebeca on 01/06/2016.
 */

    @Entity
    public class Customer {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;

        @Column(length = 50)
        private int Ci;

        @Column(length = 100)
        private String firtsName;

        @Column(length = 100)
        private String lastNameF ;

        @Column(length = 100)
        private String lastNameM ;

        @Lob
        @Column(length = 100)
        private String address;
        private int numberPhone;

        //lista de vehiculos

        @OneToMany
        private List<Vehiculo> vehiculo;

        public Customer(){
            id=0;
            Ci=0;
            firtsName="";
            lastNameF="";
            address="";
            numberPhone=0;
            vehiculo = new LinkedList<>();
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCi() {
            return Ci;
        }

        public void setCi(int ci) {
            Ci = ci;
        }

        public String getFirtsName() {
            return firtsName;
        }

        public void setFirtsName(String firtsName) {
            if (firtsName == null)
                throw new ValidationException("Null title");
            if (firtsName.isEmpty())
                throw new ValidationException("Title can't be empty");
            if (firtsName.length() > 255)
                throw new ValidationException("Title is too long");
            this.firtsName = firtsName;
        }

        public String getLastNameF() {
            return lastNameF;
        }

        public void setLastNameF(String lastNameF) {
            if (lastNameF == null)
                throw new ValidationException("Null title");
            if (lastNameF.isEmpty())
                throw new ValidationException("Title can't be empty");
            if (lastNameF.length() > 255)
                throw new ValidationException("Title is too long");
            this.lastNameF = lastNameF;
        }

        public String getLastNameM() {
            return lastNameM;
        }

        public void setLastNameM(String lastNameM) {
            if (lastNameM == null)
                throw new ValidationException("Null title");
            if (lastNameM.isEmpty())
                throw new ValidationException("Title can't be empty");
            if (lastNameM.length() > 255)
                throw new ValidationException("Title is too long");
            this.lastNameM = lastNameM;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            if (address == null)
                throw new ValidationException("Null title");
            if (address.isEmpty())
                throw new ValidationException("Title can't be empty");
            if (address.length() > 255)
                throw new ValidationException("Title is too long");
            this.address = address;
        }

        public int getNumberPhone() {
            return numberPhone;
        }

        public void setNumberPhone(int numberPhone) {
            this.numberPhone = numberPhone;
        }




    }

