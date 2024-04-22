package login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/LoginAPI")
public class LoginAPI extends HttpServlet {
    public static boolean login(String username, String password) {
        try {
            // 创建SHA-2哈希值
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] byteData = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : byteData) {
                sb.append(String.format("%02x", b));
            }
            String hashedPassword = sb.toString();

            // 创建数据库连接
            String url = "jdbc:mysql://localhost:3306/wk_sql";
            String user = "root";
            String dbPassword = "575360632b";
            Connection conn = DriverManager.getConnection(url, user, dbPassword);

            // 在数据库中查找与用户名和哈希值匹配的用户
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);
            ResultSet rs = pstmt.executeQuery();

            // 如果找到匹配的用户，则登录成功
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 如果没有找到匹配的用户，则登录失败
        return false;
    }
}