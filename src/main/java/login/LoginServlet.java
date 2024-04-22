package login;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;
import java.security.MessageDigest;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wk_sql", "root", "575360632b");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE id=? AND password=?");
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            String passwordHash = sb.toString();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id='" + username + "' AND pass_sha2='" + passwordHash + "'");

            if (rs.next()) {
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