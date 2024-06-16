import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class BalanceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountId = Integer.parseInt(request.getParameter("accountId"));
        response.setContentType("text/plain");
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT Balance FROM Accounts WHERE AccountID = ?");
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                double balance = rs.getDouble("Balance");
                response.getWriter().write(String.format("%.2f", balance));
            } else {
                response.getWriter().write("Account not found");
            }
        } catch (SQLException e) {
            response.getWriter().write("Error in database operation");
            e.printStackTrace();
        }
    }
}
