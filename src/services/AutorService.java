package services;

import dao.AutorDAO;
import models.Autor;
import models.Carte;

import java.util.List;

public class AutorService {
    private static AutorService instance;
    private AutorDAO autorDAO;

    private AutorService() {
        autorDAO = new AutorDAO();
    }

    public static synchronized AutorService getInstance() {
        if (instance == null) {
            instance = new AutorService();
        }
        return instance;
    }

    public void adaugaAutor(Autor autor) {
        autorDAO.adaugaAutor(autor);
        AuditService.getInstance().scrieActiune("adaugaAutor");
    }

    public Autor getAutorDupaId(int id) {
        return autorDAO.getAutorDupaId(id);
    }

    public void afiseazaTotiAutorii() {
        List<Autor> autori = autorDAO.getTotiAutorii();

        if (autori.isEmpty()) {
            System.out.println("Nu exista autori in baza de date.");
        } else {
            for (Autor autor : autori) {
                System.out.println(autor);
            }
        }
    }

    public void actualizeazaAutor(Autor autor) {
        autorDAO.actualizeazaAutor(autor);
        AuditService.getInstance().scrieActiune("actualizeazaAutor");
    }

    public void stergeAutor(int id) {
        autorDAO.stergeAutor(id);
        AuditService.getInstance().scrieActiune("stergeAutor");

    }
}
