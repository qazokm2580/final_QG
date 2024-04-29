package com.hzyj233.Servlet;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.hzyj233.pojo.DatabaseConfig;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.security.MessageDigest;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
        private static final String INSERT_USERS_SQL = "INSERT INTO users" +
                "  (username, nickname, password_salt, password_hash) VALUES " +
                " (?, ?, ?, ?);";
    @Override

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmpassword = request.getParameter("confirmpassword");

        String email = request.getParameter("email");
        PrintWriter out = response.getWriter();
        out.println(username+":"+password+email);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Class.forName(DatabaseConfig.getProperty("jdbc.driver"));
            Connection conn = DriverManager.getConnection(DatabaseConfig.getProperty("jdbc.url"),
                    DatabaseConfig.getProperty("jdbc.username"),
                    DatabaseConfig.getProperty("jdbc.password"));
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO user (username, pass_sha2) VALUES (?, ?)");
            encode(password);
            confirmpassword=password;
            int i = stmt.executeUpdate("INSERT INTO user(username, pass_sha2) VALUES ('" + username + "', '" + password + "')");

            if (i > 0) {
                response.sendRedirect("success.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void encode(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte byteDatum : byteData) {
            sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
        }
        String passwordHash = sb.toString();
    }
}