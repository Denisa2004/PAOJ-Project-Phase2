package services;

import dao.SectiuneDAO;
import models.Autor;
import models.Carte;
import models.Sectiune;

import java.util.List;

public class SectiuneService {
    private static SectiuneService instance;
    private SectiuneDAO sectiuneDAO;

    private SectiuneService() {
        sectiuneDAO = new SectiuneDAO();
    }

    public static synchronized SectiuneService getInstance() {
        if (instance == null) {
            instance = new SectiuneService();
        }
        return instance;
    }

    public void adaugaSectiune(Sectiune sectiune) {
        sectiuneDAO.adaugaSectiune(sectiune);
        AuditService.getInstance().scrieActiune("adaugaSectiune");
    }

    public Sectiune getSectiuneDupaId(int id) {
        return sectiuneDAO.getSectiuneDupaId(id);
    }

    public void afiseazaToateSectiunile() {
        List<Sectiune> sectiuni = sectiuneDAO.getToateSectiunile();

        if (sectiuni.isEmpty()) {
            System.out.println("Nu exista sectiuni in baza de date.");
        } else {
            for (Sectiune sectiune : sectiuni) {
                System.out.println(sectiune);
            }
        }
    }

    public void actualizeazaSectiune(Sectiune sectiune) {
        sectiuneDAO.actualizeazaSectiune(sectiune);
        AuditService.getInstance().scrieActiune("actualizeazaSectiune");
    }

    public void stergeSectiune(int id) {
        sectiuneDAO.stergeSectiune(id);
        AuditService.getInstance().scrieActiune("stergeSectiune");
    }
}
