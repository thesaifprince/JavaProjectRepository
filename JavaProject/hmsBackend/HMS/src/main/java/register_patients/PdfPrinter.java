package register_patients;


import java.io.*;

import java.sql.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/PdfPrinter")
public class PdfPrinter extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int billId = Integer.parseInt(req.getParameter("bill_id"));

        resp.setContentType("application/pdf");
        resp.setHeader("Content-Disposition",
                       "attachment; filename=BILL_" + billId + ".pdf");

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
           Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hms",
                    "root",
                    "root"
            );
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM billing WHERE bill_id = ?"
            );
            ps.setInt(1, billId);
            ResultSet rs = ps.executeQuery();

            Document doc = new Document();
            PdfWriter.getInstance(doc, resp.getOutputStream());

            doc.open();

            if(rs.next()) {
                doc.add(new Paragraph("HOSPITAL BILL", 
                          FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
                doc.add(new Paragraph(" "));
                doc.add(new Paragraph("Bill ID: " + billId));
                doc.add(new Paragraph("Patient Name: " + rs.getString("patient_name")));
                doc.add(new Paragraph("Doctor Name: " + rs.getString("doctor_name")));
                doc.add(new Paragraph("Appointment Date: " + rs.getDate("appointment_date")));
                doc.add(new Paragraph("Consultation Fee: " + rs.getDouble("consultation_fee")));
                doc.add(new Paragraph("Medicine Charges: " + rs.getDouble("medicine_charges")));
                doc.add(new Paragraph("Room Charges: " + rs.getDouble("room_charges")));
                doc.add(new Paragraph("Total Amount: " + rs.getDouble("total_amount")));
                doc.add(new Paragraph(" "));
                doc.add(new Paragraph("Generated At: " + new java.util.Date()));
            }

            doc.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

