package services;

import dao.EdituraDAO;
import models.Autor;
import models.Carte;
import models.Editura;

import java.util.List;

public class EdituraService {
    private static EdituraService instance;
    private EdituraDAO edituraDAO;

    private EdituraService() {
        edituraDAO = new EdituraDAO();
    }

    public static synchronized EdituraService getInstance() {
        if (instance == null) {
            instance = new EdituraService();
        }
        return instance;
    }

    public void adaugaEditura(Editura editura) {
        edituraDAO.adaugaEditura(editura);
        AuditService.getInstance().scrieActiune("adaugaEditura");
    }

    public Editura getEdituraDupaId(int id) {
        return edituraDAO.getEdituraDupaId(id);
    }

    public void afiseazaToateEditurile() {
        List<Editura> edituri = edituraDAO.getToateEditurile();

        if (edituri.isEmpty()) {
            System.out.println("Nu exista edituri in baza de date.");
        } else {
            for (Editura editura : edituri) {
                System.out.println(editura);
            }
        }
    }

    public void actualizeazaEditura(Editura editura) {
        edituraDAO.actualizeazaEditura(editura);
        AuditService.getInstance().scrieActiune("actualizeazaEditura");
    }

    public void stergeEditura(int id) {
        edituraDAO.stergeEditura(id);
        AuditService.getInstance().scrieActiune("stergeEditura");
    }
}
