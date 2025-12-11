package register_patients;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deletePatient")
public class DeletePatientServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String contact = request.getParameter("contact"); // or use "id" if you have it
        String connectionURL = "jdbc:mysql://localhost:3306/hms?useSSL=false&allowPublicKeyRetrieval=true";

        if (contact == null || contact.trim().isEmpty()) {
            response.sendRedirect("loadPatients");
            return;
        }

        Connection con = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connectionURL, "root", "root");

            // Delete by contact. If you have a numeric id column, prefer WHERE id=? instead.
            String sql = "DELETE FROM register_patients WHERE contact = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, contact);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Deleted patient with contact: " + contact);
            } else {
                System.out.println("No record found to delete for contact: " + contact);
            }

        } catch (Exception e) {
            e.printStackTrace();
            // optional: set an error message in session/request to show on the JSP
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        // Reload updated list
        response.sendRedirect("loadPatients");
    }
}
