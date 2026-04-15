package localization_shop.service;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Map;
import java.util.HashMap;

public class databaseService {
    private static databaseService instance = null;
    private static final String URL = "jdbc:postgresql://localhost:5432/shopping_cart_localization";
    private static final String USER = "carcas";
    private static final String PASSWORD = System.getenv("APP_PASSWORD");

    private databaseService() {
    }

    public static databaseService getInstance() {
        if (instance == null)
            instance = new databaseService();
        return instance;
    }

    public void insertRecord(int total_items, double total_cost, String language) {
        String sql = "INSERT INTO cart_records(total_items, total_cost, language) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, total_items);
            stmt.setBigDecimal(2, new BigDecimal(total_cost));
            stmt.setString(3, language);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertItem(int cart_record_id, int item_number, double price, int quantity, double subtotal) {
        String sql = "INSERT INTO cart_items(cart_record_id, item_number, price, quantity, subtotal) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cart_record_id);
            stmt.setInt(2, item_number);
            stmt.setBigDecimal(3, new BigDecimal(price));
            stmt.setInt(4, quantity);
            stmt.setBigDecimal(5, new BigDecimal(price * quantity));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getTranslations(String language) {
        Map<String, String> dictionary = new HashMap<>();

        String sql = "SELECT key, value FROM translations WHERE language = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, language);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String key = rs.getString("key");
                    String value = rs.getString("value");
                    dictionary.put(key, value);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dictionary;
    }
}
