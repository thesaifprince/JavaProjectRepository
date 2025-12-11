package register_patients;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/editRecord")
public class EditRecordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get updated values from the form
        String originalContact = request.getParameter("originalContact"); // old contact (identifier)
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String contact = request.getParameter("contact");
        int age = Integer.parseInt(request.getParameter("age"));

        String connectionURL = "jdbc:mysql://localhost:3306/hms?useSSL=false&allowPublicKeyRetrieval=true";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(connectionURL, "root", "root");

            // ✅ Update the patient record by matching the old contact number
            String sql = "UPDATE register_patients SET name=?, age=?, gender=?, contact=? WHERE contact=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, contact);
            ps.setString(5, originalContact);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Record updated successfully for contact: " + originalContact);
            } else {
                System.out.println("⚠ No matching record found to update for contact: " + originalContact);
            }

            ps.close();
            con.close();

            // Reload the updated patient list
            response.sendRedirect("loadPatients");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
        }
    }
}
