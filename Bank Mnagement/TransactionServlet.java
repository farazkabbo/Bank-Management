import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class TransactionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountId = Integer.parseInt(request.getParameter("accountId"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        String action = request.getParameter("action");
        response.setContentType("text/plain");

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Start transaction
            PreparedStatement ps;
            
            if ("deposit".equals(action)) {
                ps = conn.prepareStatement("UPDATE Accounts SET Balance = Balance + ? WHERE AccountID = ?");
            } else if ("withdraw".equals(action)) {
                ps = conn.prepareStatement("UPDATE Accounts SET Balance = Balance - ? WHERE AccountID = ?");
            } else {
                response.getWriter().write("Invalid action");
                return;
            }
            
            ps.setDouble(1, amount);
            ps.setInt(2, accountId);
            int affectedRows = ps.executeUpdate();
            
            if (affectedRows > 0) {
                conn.commit(); // Commit transaction
                response.getWriter().write("Transaction successful");
            } else {
                response.getWriter().write("No account affected");
            }
        } catch (SQLException e) {
            response.getWriter().write("Database error");
            e.printStackTrace();
        }
    }
}
