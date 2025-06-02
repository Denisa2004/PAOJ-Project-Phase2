package services;

import models.*;

import java.util.*;
import java.util.stream.Collectors;

public class BibliotecaService {
    private SortedSet<Carte> carti = new TreeSet<>(Comparator.comparing(Carte::getTitlu));
    private List<Cititor> cititori = new ArrayList<>();
    private List<Bibliotecar> bibliotecari = new ArrayList<>();
    private List<Editura> edituri = new ArrayList<>();
    private List<Autor> autori = new ArrayList<>();
    private List<Sectiune> sectiuni = new ArrayList<>();
    private Map<Cititor, List<Imprumut>> imprumuturi = new HashMap<>();

    public void adaugaCarte(Carte carte) {
        carti.add(carte);
        carte.getEditura().adaugaCartePublicata(carte);
        carte.getSectiune().adaugaCarte(carte);
    }

    public void inregistreazaCititor(Cititor cititor) {
        cititori.add(cititor);
    }

    public void inregistreazaBibliotecar(Bibliotecar bibliotecar) {
        bibliotecari.add(bibliotecar);
    }

    public void imprumutaCarte(Cititor cititor, Carte carte) {
        if (carte.getStoc() <= 0) {
            System.out.println("Cartea nu este disponibilă momentan.");
            return;
        }
        Imprumut imprumut = new Imprumut(carte, cititor);
        imprumuturi.computeIfAbsent(cititor, k -> new ArrayList<>()).add(imprumut);
        carte.setStoc(carte.getStoc() - 1);
    }

    public void returneazaCarte(Cititor cititor, Carte carte) {
        List<Imprumut> lista = imprumuturi.getOrDefault(cititor, new ArrayList<>());
        lista.removeIf(imprumut -> imprumut.getCarte().equals(carte));
        carte.setStoc(carte.getStoc() + 1);
    }

    public void afiseazaCartiDisponibile() {
        carti.stream().filter(c -> c.getStoc() > 0).forEach(System.out::println);
    }

    public void afiseazaCartiDupaAutor(Autor autor) {
        List<Carte> cartiAutor = carti.stream()
                .filter(c -> c.getAutor().equals(autor))
                .collect(Collectors.toList());

        if (cartiAutor.isEmpty()) {
            System.out.println("Autorul " + autor.getNume() + " nu are carti in biblioteca.");
        } else {
            cartiAutor.forEach(System.out::println);
        }
    }

    public void afiseazaImprumuturiCititor(Cititor cititor) {
        List<Imprumut> lista = imprumuturi.get(cititor);
        if (lista != null) lista.forEach(System.out::println);
        else System.out.println("Cititorul nu are împrumuturi.");
    }

    public Carte carteCuCelMaiMareStoc() {
        return carti.stream().max(Comparator.comparingInt(Carte::getStoc)).orElse(null);
    }

    public List<Carte> cautaCarteDupaTitlu(String titlu) {
        return carti.stream()
                .filter(c -> c.getTitlu().toLowerCase().contains(titlu.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Cititor> topCititori() {
        return imprumuturi.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public void afiseazaCartiDinSectiune(String numeSectiune) {
        carti.stream()
                .filter(c -> c.getSectiune().getNume().equalsIgnoreCase(numeSectiune))
                .forEach(System.out::println);
    }
}
