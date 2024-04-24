package login;

import secu.DatabaseConfig;

import java.sql.*;

public class UserDao {
    // 注册用户
    public boolean registerUser(String username, String password) {
        try {
            Class.forName(DatabaseConfig.getProperty("jdbc.driver"));
            Connection conn = DriverManager.getConnection(
                    DatabaseConfig.getProperty("jdbc.url"),
                    DatabaseConfig.getProperty("jdbc.username"),
                    DatabaseConfig.getProperty("jdbc.password")
            );
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Users (username, password) VALUES (?, ?)");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            int rows = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 用户登录
    public boolean loginUser(String username, String password) {
        try {
            Class.forName(DatabaseConfig.getProperty("jdbc.driver"));
            Connection conn = DriverManager.getConnection(
                    DatabaseConfig.getProperty("jdbc.url"),
                    DatabaseConfig.getProperty("jdbc.username"),
                    DatabaseConfig.getProperty("jdbc.password")
            );
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user WHERE id = ? AND password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            boolean result = rs.next();
            rs.close();
            pstmt.close();
            conn.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 重置密码
    public boolean updatePassword(String username, String password) {
        try {
            Class.forName(DatabaseConfig.getProperty("jdbc.driver"));
            Connection conn = DriverManager.getConnection(
                    DatabaseConfig.getProperty("jdbc.url"),
                    DatabaseConfig.getProperty("jdbc.username"),
                    DatabaseConfig.getProperty("jdbc.password")
            );
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Users SET password = ? WHERE username = ?");
            pstmt.setString(1, password);
            pstmt.setString(2, username);
            int rows = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}