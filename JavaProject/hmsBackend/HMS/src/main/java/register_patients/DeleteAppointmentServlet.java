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

@WebServlet("/deleteAppointment")
public class DeleteAppointmentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String pname = req.getParameter("pname");
        String dname = req.getParameter("dname");
        String adate = req.getParameter("adate");

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hms?useSSL=false&allowPublicKeyRetrieval=true",
                    "root", "root");

            PreparedStatement ps = con.prepareStatement(
                    "delete from appointments where pname=? and dname=? and appointment_date=?");

            ps.setString(1, pname);
            ps.setString(2, dname);
            ps.setString(3, adate);

            ps.executeUpdate();

            resp.sendRedirect("loadAppointments");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
