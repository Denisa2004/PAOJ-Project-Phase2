import services.*;
import models.*;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CarteService carteService = CarteService.getInstance();
    private static final AutorService autorService = AutorService.getInstance();
    private static final EdituraService edituraService = EdituraService.getInstance();
    private static final SectiuneService sectiuneService = SectiuneService.getInstance();

    public static void main(String[] args) {
        meniuBazaDeDate();
    }

    private static void meniuBazaDeDate() {
        int opt = -1;

        while (opt != 0) {
            System.out.println("\n------- Meniu Etapa 2: JDBC --------");
            System.out.println("1. Afiseaza toate cartile");
            System.out.println("2. Adauga carte");
            System.out.println("3. Sterge carte");
            System.out.println("4. Cauta carte dupa titlu");
            System.out.println("5. Actualizeaza titlu carte");
            System.out.println("6. Afiseaza toti autorii");
            System.out.println("7. Adauga autor");
            System.out.println("8. Actualizeaza autor");
            System.out.println("9. Sterge autor");
            System.out.println("10. Afiseaza toate editurile");
            System.out.println("11. Adauga editura");
            System.out.println("12. Afiseaza toate sectiunile");
            System.out.println("13. Adauga sectiune");
            System.out.println("14. Sterge sectiune ");
            System.out.println("0. Iesire");

            System.out.print("Alege optiunea: ");
            opt = Integer.parseInt(scanner.nextLine());

            switch (opt) {
                case 1 -> carteService.afiseazaToateCartile();
                case 2 -> {
                    System.out.print("Titlu: ");
                    String titlu = scanner.nextLine();

                    System.out.print("Id autor: ");
                    int idAutor = Integer.parseInt(scanner.nextLine());
                    Autor autor = autorService.getAutorDupaId(idAutor);
                    if (autor == null) {
                        System.out.println("Autorul cu id " + idAutor + " nu exista.");
                        break;
                    }

                    System.out.print("Gen: ");
                    String gen = scanner.nextLine();
                    System.out.print("An publicare: ");
                    int an = Integer.parseInt(scanner.nextLine());

                    System.out.print("Id sectiune: ");
                    int idSectiune = Integer.parseInt(scanner.nextLine());
                    Sectiune sectiune = sectiuneService.getSectiuneDupaId(idSectiune);
                    if (sectiune == null) {
                        System.out.println("Sectiunea cu id " + idSectiune + " nu exista.");
                        break;
                    }

                    System.out.print("Id editura: ");
                    int idEditura = Integer.parseInt(scanner.nextLine());
                    Editura editura = edituraService.getEdituraDupaId(idEditura);
                    if (editura == null) {
                        System.out.println("Editura cu id " + idEditura + " nu exista.");
                        break;
                    }
                    System.out.print("Stoc: ");
                    int stoc = Integer.parseInt(scanner.nextLine());

                    Carte c = new Carte(titlu, autor, gen, an, sectiune, editura, stoc);
                    carteService.adaugaCarte(c);
                }
                case 3 -> {
                    System.out.print("ID carte: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    carteService.stergeCarte(id);
                }
                case 4 -> {
                    System.out.print("Titlu cautat: ");
                    String titlu = scanner.nextLine();
                    carteService.cautaCarteDupaTitlu(titlu);
                }
                case 5 -> {
                    System.out.print("ID carte: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Noul titlu: ");
                    String nouTitlu = scanner.nextLine();
                    carteService.actualizeazaTitluCarte(id, nouTitlu);
                }
                case 6 -> autorService.afiseazaTotiAutorii();
                case 7 -> {
                    System.out.print("Nume autor: ");
                    String nume = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Telefon: ");
                    String telefon = scanner.nextLine();
                    System.out.print("Nationalitate: ");
                    String nationalitate = scanner.nextLine();
                    System.out.print("An nastere: ");
                    int an = Integer.parseInt(scanner.nextLine());
                    System.out.print("Numar carti scrise: ");
                    int nr = Integer.parseInt(scanner.nextLine());

                    Autor a = new Autor(nume, email, telefon, nationalitate, an, nr);
                    autorService.adaugaAutor(a);
                }
                case 8 -> {
                    System.out.println("Introdu ID-ul autorului pe care vrei să-l actualizezi:");
                    int idAutor = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Introdu noul nume:");
                    String nume = scanner.nextLine();

                    System.out.println("Introdu noul email:");
                    String email = scanner.nextLine();

                    System.out.println("Introdu noul telefon:");
                    String telefon = scanner.nextLine();

                    System.out.println("Introdu noua nationalitate:");
                    String nationalitate = scanner.nextLine();

                    System.out.println("Introdu noul an de nastere:");
                    int anNastere = scanner.nextInt();

                    System.out.println("Introdu noul numar de carti scrise:");
                    int nrCarti = scanner.nextInt();
                    scanner.nextLine();

                    Autor autorActualizat = new Autor(idAutor, nume, email, telefon, nationalitate, anNastere, nrCarti);

                    autorService.actualizeazaAutor(autorActualizat);

                    System.out.println("Autorul a fost actualizat cu succes.");
                }
                case 9 -> {
                    System.out.print("ID autor: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    autorService.stergeAutor(id);
                }
                case 10 -> edituraService.afiseazaToateEditurile();
                case 11 -> {
                    System.out.print("Nume editura: ");
                    String nume = scanner.nextLine();
                    System.out.print("Oras: ");
                    String oras = scanner.nextLine();
                    System.out.print("An infiintare: ");
                    int an = Integer.parseInt(scanner.nextLine());

                    Editura e = new Editura(nume, oras, an);
                    edituraService.adaugaEditura(e);
                }
                case 12 -> sectiuneService.afiseazaToateSectiunile();
                case 13 -> {
                    System.out.print("Nume sectiune: ");
                    String nume = scanner.nextLine();
                    System.out.print("Locatie: ");
                    String locatie = scanner.nextLine();
                    System.out.print("Capacitate: ");
                    int capacitate = Integer.parseInt(scanner.nextLine());

                    Sectiune s = new Sectiune(nume, locatie, capacitate);
                    sectiuneService.adaugaSectiune(s);
                }
                case 14 -> {
                    System.out.print("ID sectiune: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    sectiuneService.stergeSectiune(id);
                }
                case 0 -> System.out.println("Iesire din meniu JDBC.");
                default -> System.out.println("Optiune invalida.");
            }
        }
    }
}
