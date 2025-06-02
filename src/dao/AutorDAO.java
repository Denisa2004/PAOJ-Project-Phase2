package dao;

import db.DatabaseConnection;
import models.Autor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    public void adaugaAutor(Autor autor) {
        String sql = "INSERT INTO autor(nume, email, telefon, nationalitate, an_nastere, numar_carti) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, autor.getNume());
            stmt.setString(2, autor.getEmail());
            stmt.setString(3, autor.getTelefon());
            stmt.setString(4, autor.getNationalitate());
            stmt.setInt(5, autor.getAnNastere());
            stmt.setInt(6, autor.getNrCartiScrise());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                autor.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Autor getAutorDupaId(int id) {
        String sql = "SELECT * FROM autor WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Autor(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("email"),
                        rs.getString("telefon"),
                        rs.getString("nationalitate"),
                        rs.getInt("an_nastere"),
                        rs.getInt("numar_carti")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Autor> getTotiAutorii() {
        List<Autor> autori = new ArrayList<>();
        String sql = "SELECT * FROM autor";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Autor a = new Autor(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("email"),
                        rs.getString("telefon"),
                        rs.getString("nationalitate"),
                        rs.getInt("an_nastere"),
                        rs.getInt("numar_carti")
                );
                autori.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autori;
    }

    public void actualizeazaAutor(Autor autor) {
        String sql = "UPDATE autor SET nume=?, email=?, telefon=?, nationalitate=?, an_nastere=?, numar_carti=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, autor.getNume());
            stmt.setString(2, autor.getEmail());
            stmt.setString(3, autor.getTelefon());
            stmt.setString(4, autor.getNationalitate());
            stmt.setInt(5, autor.getAnNastere());
            stmt.setInt(6, autor.getNrCartiScrise());
            stmt.setInt(7, autor.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void stergeAutor(int id) {
        String sql = "DELETE FROM autor WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
