package register_patients;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/storePDFLog")
public class StorePDFLog extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String patient = req.getParameter("patient_name");
        String remarks = req.getParameter("remarks");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hms","root","root");

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO pdf_log(patient_name, remarks, created_at) VALUES (?, ?, NOW())");

            ps.setString(1, patient);
            ps.setString(2, remarks);
            ps.executeUpdate();

            con.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}

