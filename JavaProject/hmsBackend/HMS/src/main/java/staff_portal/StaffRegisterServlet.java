package staff_portal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/StaffRegisterServlet")
public class StaffRegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hms";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get form data
        String name = request.getParameter("name");
        String staffId = request.getParameter("staffId");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // DB Connection
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            String sql = "INSERT INTO staff_portal_login_details (staff_id, name, email, password) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, staffId);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, password);

            ps.executeUpdate();

            ps.close();
            con.close();

            // Alert + redirect back to staffPortal.jsp
            out.println("<script type='text/javascript'>");
            out.println("alert('Your data is registered. You can login now');");
            out.println("location='staffPortal.jsp';");
            out.println("</script>");

        } catch (Exception e) {
            out.println("<script type='text/javascript'>");
            out.println("alert('Registration failed: " + e.getMessage() + "');");
            out.println("location='staffPortal.jsp';");
            out.println("</script>");
        }
    }
}
