package models;

public class Cititor extends Persoana {
    private int idCititor;
    private int varsta;
    private boolean abonat;

    public Cititor(String nume, String email, String telefon, int idCititor, int varsta, boolean abonat) {
        super(nume, email, telefon);
        this.idCititor = idCititor;
        this.varsta = varsta;
        this.abonat = abonat;
    }

    public int getIdCititor() {
        return idCititor;
    }

    public int getVarsta() {
        return varsta;
    }

    public boolean isAbonat() {
        return abonat;
    }

    @Override
    public String toString() {
        return "Cititor -> ID: " + idCititor + ", " + super.toString() + ", Vârstă: " + varsta + ", Abonat: " + (abonat ? "Da" : "Nu");
    }
}