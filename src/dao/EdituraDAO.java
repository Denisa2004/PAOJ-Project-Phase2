package dao;

import db.DatabaseConnection;
import models.Editura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EdituraDAO {

    public void adaugaEditura(Editura editura) {
        String sql = "INSERT INTO editura(nume, oras, an_infiintare) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, editura.getNume());
            stmt.setString(2, editura.getAdresa());
            stmt.setInt(3, editura.getAnInfiintare());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                editura.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Editura getEdituraDupaId(int id) {
        String sql = "SELECT * FROM editura WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Editura(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("oras"),
                        rs.getInt("an_infiintare")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Editura> getToateEditurile() {
        List<Editura> edituri = new ArrayList<>();
        String sql = "SELECT * FROM editura";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Editura e = new Editura(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("oras"),
                        rs.getInt("an_infiintare")
                );
                edituri.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return edituri;
    }

    public void actualizeazaEditura(Editura editura) {
        String sql = "UPDATE editura SET nume=?, oras=?, an_infiintare=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, editura.getNume());
            stmt.setString(2, editura.getAdresa());
            stmt.setInt(3, editura.getAnInfiintare());
            stmt.setInt(4, editura.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void stergeEditura(int id) {
        String sql = "DELETE FROM editura WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
