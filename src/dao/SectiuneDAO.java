package dao;

import db.DatabaseConnection;
import models.Sectiune;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SectiuneDAO {

    public void adaugaSectiune(Sectiune sectiune) {
        String sql = "INSERT INTO sectiune(nume, locatie, capacitate) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, sectiune.getNume());
            stmt.setString(2, sectiune.getLocatie());
            stmt.setInt(3, sectiune.getCapacitate());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                sectiune.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Sectiune getSectiuneDupaId(int id) {
        String sql = "SELECT * FROM sectiune WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Sectiune(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("locatie"),
                        rs.getInt("capacitate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Sectiune> getToateSectiunile() {
        List<Sectiune> sectiuni = new ArrayList<>();
        String sql = "SELECT * FROM sectiune";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Sectiune s = new Sectiune(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("locatie"),
                        rs.getInt("capacitate")
                );
                sectiuni.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sectiuni;
    }

    public void actualizeazaSectiune(Sectiune sectiune) {
        String sql = "UPDATE sectiune SET nume=?, locatie=?, capacitate=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sectiune.getNume());
            stmt.setString(2, sectiune.getLocatie());
            stmt.setInt(3, sectiune.getCapacitate());
            stmt.setInt(4, sectiune.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void stergeSectiune(int id) {
        String sql = "DELETE FROM sectiune WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
