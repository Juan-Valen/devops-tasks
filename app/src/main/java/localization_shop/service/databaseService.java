package localization_shop.service;

import java.sql.*;
import java.util.Map;
import java.util.HashMap;

public class databaseService {
    private static databaseService instance = null;
    private static final String URL = "jdbc:postgresql://localhost:5432/shopping_cart_localization";
    private static final String USER = "carcas";
    private static final String PASSWORD = "carcassonne1";

    private databaseService() {
    }

    public static databaseService getInstance() {
        if (instance == null)
            instance = new databaseService();
        return instance;
    }

    // private void setSaves(int game_id) {
    // String sql = "INSERT INTO saves(game_id, user_id) VALUES (?, ?)";
    //
    // try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
    // PreparedStatement stmt = conn.prepareStatement(sql)) {
    // stmt.setInt(1, game_id);
    // stmt.executeUpdate();
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }

    public Map<String, String> getTranslations(String language) {
        Map<String, String> dictionary = new HashMap<>();

        String sql = "SELECT key, translation FROM translations WHERE language = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, language);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String key = rs.getString("key");
                    String value = rs.getString("translation");
                    dictionary.put(key, value);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dictionary;
    }
}
