package services;

import dao.CarteDAO;
import models.Carte;

import java.util.List;

public class CarteService {
    private static CarteService instance;
    private CarteDAO carteDAO;

    private CarteService() {
        carteDAO = new CarteDAO();
    }

    public static synchronized CarteService getInstance() {
        if (instance == null) {
            instance = new CarteService();
        }
        return instance;
    }

    public void adaugaCarte(Carte carte) {
        carteDAO.adaugaCarte(carte);
        AuditService.getInstance().scrieActiune("adaugaCarte");

    }

    public void afiseazaToateCartile() {
        List<Carte> carti = carteDAO.getToateCartile();

        if (carti.isEmpty()) {
            System.out.println("Nu exista carti in baza de date.");
        } else {
            for (Carte carte : carti) {
                System.out.println(carte);
            }
        }
    }

    public void stergeCarte(int id) {
        carteDAO.stergeCarte(id);
        AuditService.getInstance().scrieActiune("stergeCarte");
    }

    public void cautaCarteDupaTitlu(String titlu) {
        List<Carte> carti = carteDAO.cautaCarteDupaTitlu(titlu);

        if (carti.isEmpty()) {
            System.out.println("Nu s-au gasit carti cu titlul: " + titlu);
        } else {
            carti.forEach(System.out::println);
        }

        AuditService.getInstance().scrieActiune("Cauta carte dupa titlu");
    }

    public void actualizeazaTitluCarte(int id, String nouTitlu) {
        carteDAO.actualizeazaTitluCarte(id, nouTitlu);
        AuditService.getInstance().scrieActiune("Actualizeaza titlu carte");
    }
}
