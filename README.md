# 📚 Aplicație de Gestiune Bibliotecă

## Descriere proiect 📖

Acest proiect reprezintă o aplicație de gestiune a unei biblioteci, dezvoltată în Java utilizând o bază de date PostgreSQL. Proiectul permite administrarea informațiilor despre cărți, autori, edituri și secțiuni ale bibliotecii si interactiune cu utilizatorul in consola. Interacțiunea cu baza de date se realizează prin JDBC, iar datele persistă în PostgreSQL.

---

## Structura bazei de date 🗄️

Tabele:
- **carte** (id, titlu, autor_id, gen, an, sectiune_id, editura_id, stoc)
- **autor** (id, nume, email, telefon, nationalitate, an_nastere, numar_carti)
- **editura** (id, nume, oras, an_infiintare)
- **sectiune** (id, nume, locatie, capacitate)

Scriptul SQL de creare a tabelelor și inserare a datelor inițiale se găsește în folderul `sql/`, fișierul `database_setup.sql`.

---

## Funcționalități implementate — Etapa a 2-a ✅

✔️ **Conectare la baza de date**:
- Aplicația folosește o conexiune JDBC la PostgreSQL, gestionată centralizat printr-o clasă dedicată (in folderul db).

✔️ **Creare și populare tabele**:
- Tabelele necesare au fost create și populate în pgAdmin, pe baza unui script SQL (fișier separat pentru a putea fi rulat rapid pe o altă instanță de bază de date dacă e nevoie).

✔️ **Interogări și operații CRUD**:
- Aplicația permite adăugarea, ștergerea, modificarea și afișarea de date pentru fiecare entitate.
- Operațiile sunt evidențiate prin opțiunile disponibile în meniul interactiv din consolă.

✔️ **Servicii Singleton si Interogări și operații CRUD** :
- Am structurat serviciile responsabile de operații CRUD pentru fiecare entitate (Carte, Autor, Editura, Sectiune) astfel încât să existe o singură instanță a fiecăruia în aplicație, pentru a gestiona eficient interacțiunile cu baza de date.

✔️ **Logarea acțiunilor**:
- Toate acțiunile efectuate de utilizator prin meniul interactiv sunt înregistrate într-un fișier CSV, împreună cu momentul în care au avut loc. Această funcționalitate ajută la urmărirea activităților din aplicație.

✔️ **Relații între entități**:
- Am asigurat corect relațiile între tabele (cartea are autor, editură și secțiune asociate) atât la nivel de baze de date, cât și în aplicație.

✔️ **Meniu interactiv în consolă** pentru accesarea funcționalităților:
- Afișare, adaugare, stergere cărți
- Cautare carte dupa titlu
- Modificare titlu
- Actualizare autori / edituri / secțiuni etc.

---
