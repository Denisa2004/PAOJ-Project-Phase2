package models;

import java.util.List;
import java.util.ArrayList;

public class Sectiune {
    private int id;
    private String nume;
    private String locatie;
    private int capacitate;

    public Sectiune(int id, String nume, String locatie, int capacitate) {
        this.id = id;
        this.nume = nume;
        this.locatie = locatie;
        this.capacitate = capacitate;
    }

    public Sectiune(String nume, String locatie, int capacitate) {
        this.nume = nume;
        this.locatie = locatie;
        this.capacitate = capacitate;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNume() {
        return nume;
    }

    public String getLocatie() {
        return locatie;
    }

    public int getCapacitate() {
        return capacitate;
    }

    private List<Carte> carti = new ArrayList<>();

    public void adaugaCarte(Carte carte) {
        carti.add(carte);
    }

    @Override
    public String toString() {
        return "Sectiune: " + nume + ", Locație: " + locatie + ", Capacitate: " + capacitate;
    }
}