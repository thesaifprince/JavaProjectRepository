package register_patients;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/bookappointment")
public class BookAppointmentServlet  extends HttpServlet{

	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException , IOException{
		String apptPatientName = request.getParameter("aname");
		String apptDoctorName = request.getParameter("adoctor");
		String apptDate = request.getParameter("adate");
		
		
		
		System.out.println("---------------------------------------------------");
		
		
		//String mysqlString = "com.mysql.cj.jdbc.Driver";
		String connectionURL = "jdbc:mysql://localhost:3306/hms"+
				"?useSSL=false&allowPublicKeyRetrieval=true";
		
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(connectionURL,"root","root");
		PreparedStatement ps = con.prepareStatement("insert into appointments"
				+"(pname,dname,appointment_date)"+ " values(?,?,?)");
		ps.setString(1,apptPatientName);
		ps.setString(2, apptDoctorName);
		ps.setString(3,apptDate);
		
		int i = ps.executeUpdate();
//		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
//		rd.forward(request, response);
		
		if(i>0) {
			System.out.println("Successful input");
		}
		//retrival
		 List<AppointmentDetails> appointments = new ArrayList<>();
		 ps = con.prepareStatement("Select pname,dname,appointment_date from appointments order by id desc");
		 ResultSet rs = ps.executeQuery();
		
		
		while(rs.next()) {
			
			appointments.add(new AppointmentDetails(
                    rs.getString("pname"),
                    rs.getString("dname"),
                    rs.getString("appointment_date")
            
                ));
			
		}
		HttpSession session = request.getSession();
		session.setAttribute("appointmentList", appointments);
		response.sendRedirect("loadAppointments");
		
		
		}catch(ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
	}

}
