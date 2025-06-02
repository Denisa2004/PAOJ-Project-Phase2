package models;

public class Bibliotecar extends Persoana {
    private int codAngajat;
    private String tura;

    public Bibliotecar(String nume, String email, String telefon, int codAngajat, String tura) {
        super(nume, email, telefon);
        this.codAngajat = codAngajat;
        this.tura = tura;
    }

    public int getCodAngajat() {
        return codAngajat;
    }

    public String getTura() {
        return tura;
    }

    @Override
    public String toString() {
        return "Bibliotecar -> Cod: " + codAngajat + ", " + super.toString() + ", Tura: " + tura;
    }
}
