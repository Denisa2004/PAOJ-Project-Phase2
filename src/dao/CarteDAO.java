package dao;

import db.DatabaseConnection;
import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarteDAO {

    public void adaugaCarte(Carte carte) {
        String sql = "INSERT INTO carte(titlu, autor_id, gen, an, sectiune_id, editura_id, stoc) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, carte.getTitlu());
            stmt.setInt(2, carte.getAutor().getId());
            stmt.setString(3, carte.getGen());
            stmt.setInt(4, carte.getAn());
            stmt.setInt(5, carte.getSectiune().getId());
            stmt.setInt(6, carte.getEditura().getId());
            stmt.setInt(7, carte.getStoc());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                carte.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Carte> getToateCartile() {
        List<Carte> carti = new ArrayList<>();
        String sql = "SELECT c.id, c.titlu, c.gen, c.an, c.stoc, " +
                "a.id as autor_id, a.nume as autor_nume, a.email as autor_email, a.telefon as autor_tel, a.nationalitate, a.an_nastere, a.numar_carti, " +
                "s.id as sectiune_id, s.nume as sectiune_nume, s.locatie, s.capacitate, " +
                "e.id as editura_id, e.nume as editura_nume, e.oras, e.an_infiintare " +
                "FROM carte c " +
                "JOIN autor a ON c.autor_id = a.id " +
                "JOIN sectiune s ON c.sectiune_id = s.id " +
                "JOIN editura e ON c.editura_id = e.id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Autor autor = new Autor(
                        rs.getInt("autor_id"),
                        rs.getString("autor_nume"),
                        rs.getString("autor_email"),
                        rs.getString("autor_tel"),
                        rs.getString("nationalitate"),
                        rs.getInt("an_nastere"),
                        rs.getInt("numar_carti")
                );

                Sectiune sectiune = new Sectiune(
                        rs.getInt("sectiune_id"),
                        rs.getString("sectiune_nume"),
                        rs.getString("locatie"),
                        rs.getInt("capacitate")
                );

                Editura editura = new Editura(
                        rs.getInt("editura_id"),
                        rs.getString("editura_nume"),
                        rs.getString("oras"),
                        rs.getInt("an_infiintare")
                );

                Carte carte = new Carte(
                        rs.getInt("id"),
                        rs.getString("titlu"),
                        autor,
                        rs.getString("gen"),
                        rs.getInt("an"),
                        sectiune,
                        editura,
                        rs.getInt("stoc")
                );
                carti.add(carte);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carti;
    }

    public void stergeCarte(int id) {
        String sql = "DELETE FROM carte WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Carte> cautaCarteDupaTitlu(String titlu) {
        List<Carte> carti = new ArrayList<>();
        String query = "SELECT * FROM carte WHERE titlu ILIKE ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + titlu + "%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int idAutor = rs.getInt("autor_id");
                int idSectiune = rs.getInt("sectiune_id");
                int idEditura = rs.getInt("editura_id");

                Autor autor = new AutorDAO().getAutorDupaId(idAutor);
                Sectiune sectiune = new SectiuneDAO().getSectiuneDupaId(idSectiune);
                Editura editura = new EdituraDAO().getEdituraDupaId(idEditura);

                Carte carte = new Carte(
                        rs.getInt("id"),
                        rs.getString("titlu"),
                        autor,
                        rs.getString("gen"),
                        rs.getInt("an"),
                        sectiune,
                        editura,
                        rs.getInt("stoc")
                );
                carti.add(carte);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carti;
    }

    public void actualizeazaTitluCarte(int id, String nouTitlu) {
        String query = "UPDATE carte SET titlu = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, nouTitlu);
            statement.setInt(2, id);
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Titlul a fost actualizat.");
            } else {
                System.out.println("Nu s-a gasit cartea cu ID-ul respectiv.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
