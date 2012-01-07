package nhlapp.domain;

import mvc.temp.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tuote {

    private int id;
    private int hinta;
    private String nimi;
    private int saldo;

    public Tuote(String nimi, int hinta, int saldo) {
        this.hinta = hinta;
        this.nimi = nimi;
        this.saldo = saldo;
    }

    public Tuote() {
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public int getHinta() {
        return hinta;
    }

    public String getNimi() {
        return nimi;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setHinta(int hinta) {
        this.hinta = hinta;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }        
    
    @Override
    public String toString() {
        return id + ": " + nimi + ", hinta " + hinta + ", varastossa: " + saldo + " kpl";
    }
}
