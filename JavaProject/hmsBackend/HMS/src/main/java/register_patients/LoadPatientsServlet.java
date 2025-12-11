package register_patients;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/loadPatients")
public class LoadPatientsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String connectionURL = "jdbc:mysql://localhost:3306/hms?useSSL=false&allowPublicKeyRetrieval=true";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(connectionURL, "root", "root");

            // Fetch patient data
            List<Patient> patients = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("SELECT name, age, gender, contact FROM register_patients order by id desc");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                patients.add(new Patient(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("contact")
                ));
            }

            // Store in session
            HttpSession session = request.getSession();
            session.setAttribute("patientsList", patients);

            // ✅ Forward to the correct JSP page
            RequestDispatcher rd = request.getRequestDispatcher("viewPatientList.jsp");
            rd.forward(request, response);

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
        }
    }
}
