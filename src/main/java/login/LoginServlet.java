package login;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

import jakarta.servlet.annotation.WebServlet;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        String passwordHash = request.getParameter("passwordHash");
            PrintWriter out = response.getWriter();
            out.println(username+":"+passwordHash+email);


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wk_sql", "root", "575360632b");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE id=? AND password=?");
            ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE id='" + username + "' AND password='" + password + "'" );

            if (rs.next()) {
                response.sendRedirect("success.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}