package hotelmanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.io.InputStream;
import javax.swing.JOptionPane;

public class Database {
    private static String DB_URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try (InputStream input = Database.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            DB_URL = prop.getProperty("db.url");
            USER = prop.getProperty("db.user");
            PASSWORD = prop.getProperty("db.password");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading database configuration: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Save bill to database 
    public boolean saveBill(double total, double cgst, double sgst, double finalTotal, ArrayList<MenuItem> items) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;

        try {
            // Connect to database
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            // Insert bill
            String sql = "INSERT INTO bills (total_amount, cgst, sgst, final_amount) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, total);
            stmt.setDouble(2, cgst);
            stmt.setDouble(3, sgst);
            stmt.setDouble(4, finalTotal);

            int result = stmt.executeUpdate();

            if (result > 0) {
                success = true;
            }

        } catch (SQLException e) {
            if (e.getMessage().contains("Unknown database")) {
                JOptionPane.showMessageDialog(null, 
                    "Database doesn't exist. Please create it first.");
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Database error: " + e.getMessage());
            }
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return success;
    }
}
