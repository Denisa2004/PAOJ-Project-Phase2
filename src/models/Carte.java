package models;

public class Carte {
    private int id;
    private String titlu;
    private Autor autor;
    private String gen;
    private int an;
    private Sectiune sectiune;
    private Editura editura;
    private int stoc;

    public Carte(String titlu, Autor autor, String gen, int an, Sectiune sectiune, Editura editura, int stoc) {
        this.titlu = titlu;
        this.autor = autor;
        this.gen = gen;
        this.an = an;
        this.sectiune = sectiune;
        this.editura = editura;
        this.stoc = stoc;
    }

    public Carte(int id, String titlu, Autor autor, String gen, int an, Sectiune sectiune, Editura editura, int stoc) {
        this.id = id;
        this.titlu = titlu;
        this.autor = autor;
        this.gen = gen;
        this.an = an;
        this.sectiune = sectiune;
        this.editura = editura;
        this.stoc = stoc;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitlu() {
        return titlu;
    }

    public Autor getAutor() {
        return autor;
    }

    public String getGen() {
        return gen;
    }

    public int getAn() {
        return an;
    }

    public Sectiune getSectiune() {
        return sectiune;
    }

    public Editura getEditura() {
        return editura;
    }

    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }

    @Override
    public String toString() {
        return "\"" + titlu + "\" de " + autor.getNume() + ", Gen: " + gen
                + ", An: " + an + ", Sectiune: " + sectiune.getNume() +
                ", Editura: " + editura.getNume() + ", Stoc: " + stoc;
    }
}
