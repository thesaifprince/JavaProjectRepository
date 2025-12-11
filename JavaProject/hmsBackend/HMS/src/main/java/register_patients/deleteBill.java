package register_patients;



import java.io.IOException;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/deleteBill")
public class deleteBill extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String billId = request.getParameter("bill_id");

        if (billId == null || billId.trim().isEmpty()) {
            response.sendRedirect("editBill.jsp?msg=Invalid Bill ID");
            return;
        }

        Connection con = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hms", "root", "root"
            );

            ps = con.prepareStatement("DELETE FROM billing WHERE bill_id = ?");
            ps.setInt(1, Integer.parseInt(billId));

            int rows = ps.executeUpdate();

            if (rows > 0) {
                response.sendRedirect("editBill.jsp?msg=Bill+Deleted+Successfully");
            } else {
                response.sendRedirect("editBill.jsp?msg=Bill+Not+Found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("editBill.jsp?msg=Error+While+Deleting");
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception ex) {}
            try { if (con != null) con.close(); } catch (Exception ex) {}
        }
    }
}
