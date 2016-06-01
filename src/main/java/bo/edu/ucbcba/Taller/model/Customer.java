package bo.edu.ucbcba.Taller.model;

import javax.persistence.*;

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
            this.firtsName = firtsName;
        }

        public String getLastNameF() {
            return lastNameF;
        }

        public void setLastNameF(String lastNameF) {
            this.lastNameF = lastNameF;
        }

        public String getLastNameM() {
            return lastNameM;
        }

        public void setLastNameM(String lastNameM) {
            this.lastNameM = lastNameM;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getNumberPhone() {
            return numberPhone;
        }

        public void setNumberPhone(int numberPhone) {
            this.numberPhone = numberPhone;
        }




    }

