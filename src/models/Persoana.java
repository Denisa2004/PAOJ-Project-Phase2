package models;

public class Persoana {
    protected String nume;
    protected String email;
    protected String telefon;

    public Persoana(String nume, String email, String telefon) {
        this.nume = nume;
        this.email = email;
        this.telefon = telefon;
    }

    public String getNume() {
        return nume;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }

    @Override
    public String toString() {
        return "Nume: " + nume + ", Email: " + email + ", Telefon: " + telefon;
    }
}