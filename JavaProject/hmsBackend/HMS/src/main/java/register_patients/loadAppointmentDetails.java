package register_patients;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


@WebServlet("/loadAppointments")
public class loadAppointmentDetails extends HttpServlet {

	 @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        String connectionURL = "jdbc:mysql://localhost:3306/hms?useSSL=false&allowPublicKeyRetrieval=true";

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection(connectionURL, "root", "root");

	            // Fetch Appointment data
	          //retrival
	   		 List<AppointmentDetails> appointments = new ArrayList<>();
	   		 PreparedStatement ps = con.prepareStatement("Select pname,dname,appointment_date from appointments order by id desc");
	   		 ResultSet rs = ps.executeQuery();
	   		
	   		
	   		while(rs.next()) {
	   			
	   			appointments.add(new AppointmentDetails(
	                       rs.getString("pname"),
	                       rs.getString("dname"),
	                       rs.getString("appointment_date")
	               
	                   ));
	   			
	   		}

	            // Store in session
	            HttpSession session = request.getSession();
	            session.setAttribute("appointmentList", appointments);

	            // ✅ Forward to the correct JSP page
	            RequestDispatcher rd = request.getRequestDispatcher("viewAppointments.jsp");
	            rd.forward(request, response);

	            con.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.getWriter().println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
	        }
	    }
}
