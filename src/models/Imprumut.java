package models;

import java.util.Date;
import java.util.Calendar;

public class Imprumut {
    private Carte carte;
    private Date dataImprumut;
    private Date dataReturnare;
    private Cititor cititor;

    public Imprumut(Carte carte, Cititor cititor) {
        this.carte = carte;
        this.cititor = cititor;
        this.dataImprumut = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(this.dataImprumut);
        c.add(Calendar.DAY_OF_MONTH, 14); // 2 săptămâni
        this.dataReturnare = c.getTime();
    }


    public Carte getCarte() {
        return carte;
    }

    public Date getDataImprumut() {
        return dataImprumut;
    }

    public Date getDataReturnare() {
        return dataReturnare;
    }

    public Cititor getCititor() {
        return cititor;
    }

    @Override
    public String toString() {
        return "Imprumut: " + carte.getTitlu() + " de " + cititor.getNume() + ", De la: " + dataImprumut + " până la: " + dataReturnare;
    }
}