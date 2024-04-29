package com.hzyj233.dao;

import com.hzyj233.pojo.DatabaseConfig;

import java.sql.*;

public class UserDao {
    // 注册用户
    public boolean registerUser(String username, String password) {
        byte[] pass_sha2 = new byte[16];
        byte[] confirmPassword = new byte[16];


        try {
            Class.forName(DatabaseConfig.getProperty("jdbc.driver"));
            Connection conn = DriverManager.getConnection(
                    DatabaseConfig.getProperty("jdbc.url"),
                    DatabaseConfig.getProperty("jdbc.username"),
                    DatabaseConfig.getProperty("jdbc.password")
            );
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO user (nickname, pass_sha2) VALUES (?, ?)");
            pstmt.setString(1, username);
            pstmt.setBytes(2, pass_sha2);
            pstmt.setBytes(2, confirmPassword);

            if (!password.equals(confirmPassword)) {
                // Handle error: the two passwords do not match
                return false;
            }
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
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user WHERE id = ? AND pass_sha2 = ?");
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
            PreparedStatement pstmt = conn.prepareStatement("UPDATE user SET pass_sha2 = ? WHERE username = ?");
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