<%@  page import="java.util.*,register_patients.AppointmentDetails" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hospital Management System</title>
    <link rel="stylesheet" href="style.css">
    <style>
    	#editBTN {
    background-color: #4CAF50; /* Green */
    color: white;
    border: none;
    padding: 6px 12px;
    border-radius: 5px;
    cursor: pointer;
    transition: 0.3s;
}

#editBTN:hover {
    background-color: #45a049;
}

#deleteBTN {
    background-color: #f44336; /* Red */
    color: white;
    border: none;
    padding: 6px 12px;
    border-radius: 5px;
    cursor: pointer;
    transition: 0.3s;
}

#deleteBTN:hover {
    background-color: #da190b;
}

.popup {
    display: none;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0,0,0,0.5);
}

.popup-content {
    background: #fff;
    padding: 20px;
    width: 300px;
    margin: 10px auto;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0,0,0,0.2);
}

    </style>
</head>
<body>
 <!-- Navigation Bar -->
    <header>
        <h1>Hospital Management System</h1>
        <nav>
            <ul>
                <li><a href="index.jsp" onclick="showSection('dashboard')">Dashboard</a></li>
                <li><a href="index.jsp" onclick="showSection('patients')">Patients</a></li>
                <li><a href="index.jsp" onclick="showSection('appointments')">Appointments</a></li>
                <li><a href="index.jsp" onclick="showSection('tokens')">Token System</a></li>
            </ul>
        </nav>
    </header>
     <h3>Appointment List</h3>
        <table id="appointmentTable">
            <thead>
                <tr>
                    <th>Patient</th>
                    <th>Doctor</th>
                    <th>Date</th>
                    <th>Actions</th>
                </tr>
                
            </thead>
            <tbody>
             <%
            List<AppointmentDetails> apd = (List<AppointmentDetails>) session.getAttribute("appointmentList");
            if (apd != null && !apd.isEmpty()) {
                for (AppointmentDetails p : apd) {
        %>
                  <tr>
                        <td><%= p.getPatientName() %></td>
                        <td><%= p.getDoctorName() %></td>
                        <td><%= p.getAppointmentDate() %></td>
                       	<td>
				        <!-- EDIT BUTTON -->
				        <button id="editBTN" type="button"
				                onclick="openEditPopup(
				                    '<%= p.getPatientName() %>',
				                    '<%= p.getDoctorName() %>',
				                    '<%= p.getAppointmentDate() %>'
				                )">
				            Edit
				        </button>
				        
				        <!-- DELETE BUTTON -->
			        <form action="deleteAppointment" method="post" style="display:inline;">
			            <input type="hidden" name="pname" value="<%= p.getPatientName() %>">
			            <input type="hidden" name="dname" value="<%= p.getDoctorName() %>">
			            <input type="hidden" name="adate" value="<%= p.getAppointmentDate() %>">
			            <button id="deleteBTN"
			                    type="submit"
			                    onclick="return confirm('Are you sure you want to delete this appointment?');">
			                Delete
			            </button>
			        </form>
   				 </td>
                    </tr>
        <%
                }
            } else {
        %>
                <tr>
                    <td colspan="4" style="text-align:center;">No records found</td>
                </tr>
        <%
            }
        %>
        </tbody>
        </table>
      
  		<div id="editPopup" class="popup">
  <div class="popup-content">
    <h3>Edit Appointment</h3>

    <form id="editForm" action="editAppointment" method="post">
      
      <input type="hidden" id="oldPatient" name="oldPatient">
      <input type="hidden" id="oldDoctor" name="oldDoctor">
      <input type="hidden" id="oldDate" name="oldDate">

      <label>Patient:</label>
      <input type="text" id="editPatient" name="pname"><br>

      <label>Doctor:</label>
      <input type="text" id="editDoctor" name="dname"><br>

      <label>Date:</label>
      <input type="date" id="editDate" name="adate"><br>

      <button type="submit" id="editBTN">Save Changes</button>
      <button type="button" onclick="closeEditPopup()">Cancel</button>
    </form>
  </div>
</div>
  		
       <script>
       function openEditPopup(pname, dname, adate) {
    	    document.getElementById("editPopup").style.display = "block";
    	    document.getElementById("editPatient").value = pname;
    	    document.getElementById("editDoctor").value = dname;
    	    document.getElementById("editDate").value = adate;

    	    document.getElementById("oldPatient").value = pname;
    	    document.getElementById("oldDoctor").value = dname;
    	    document.getElementById("oldDate").value = adate;
    	}

    	function closeEditPopup() {
    	    document.getElementById("editPopup").style.display = "none";
    	}

</script>
       
</body>
</html>