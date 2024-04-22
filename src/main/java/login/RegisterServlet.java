package login;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.security.MessageDigest;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wk_sql", "root", "575360632b");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO user (id, password) VALUES (?, ?)");
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            String passwordHash = sb.toString();
            int i = stmt.executeUpdate("INSERT INTO users(id, pass_sha2) VALUES ('" + username + "', '" + passwordHash + "')");

            if (i > 0) {
                response.sendRedirect("success.html");
            } else {
                response.sendRedirect("error.html");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}