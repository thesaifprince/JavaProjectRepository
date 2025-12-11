package register_patients;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editAppointment")
public class EditAppointmentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String oldP = req.getParameter("oldPatient");
        String oldD = req.getParameter("oldDoctor");
        String oldDate = req.getParameter("oldDate");

        String newP = req.getParameter("pname");
        String newD = req.getParameter("dname");
        String newDate = req.getParameter("adate");

        try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hms?useSSL=false&allowPublicKeyRetrieval=true",
                "root", "root");

            PreparedStatement ps = con.prepareStatement(
                "update appointments set pname=?, dname=?, appointment_date=? where pname=? and dname=? and appointment_date=?");

            ps.setString(1, newP);
            ps.setString(2, newD);
            ps.setString(3, newDate);
            ps.setString(4, oldP);
            ps.setString(5, oldD);
            ps.setString(6, oldDate);

            ps.executeUpdate();

            resp.sendRedirect("loadAppointments");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

