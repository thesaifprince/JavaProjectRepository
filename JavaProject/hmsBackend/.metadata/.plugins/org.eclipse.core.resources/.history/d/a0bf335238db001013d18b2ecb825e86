<%@ page import="java.util.*,register_patients.Patient" %>
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
                <li><a href="billing.jsp">Billing System</a></li>
                <li><a href="index.jsp" onclick="showSection('tokens')">Token System</a></li>
            </ul>
        </nav>
    </header>
     <h3>Patient List</h3>
        <table id="patientTable">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Gender</th>
                    <th>Contact</th>
                    <th>Actions</th>
                </tr>
                
            </thead>
            <tbody>
             <%
            List<Patient> patient = (List<Patient>) session.getAttribute("patientsList");
            if (patient != null && !patient.isEmpty()) {
                for (Patient p : patient) {
        %>
                    <tr>
                        <td><%= p.getName() %></td>
                        <td><%= p.getAge() %></td>
                        <td><%= p.getGender() %></td>
                        <td><%= p.getContact() %></td>
                        <td>
    				<form action="editPatient" method="post" style="display:inline;">
       					 <input type="hidden" name="contact" value="<%= p.getContact() %>">
       					 <button id="editBTN" type="button"
						    onclick="openEditPopup('<%= p.getName() %>', 
						    '<%= p.getAge() %>', 
						    '<%= p.getGender() %>',
						     '<%= p.getContact() %>')">
						    Edit
						 </button>
   					 </form>
	
				    <form action="deletePatient" method="post" style="display:inline;">
				        <input type="hidden" name="contact" value="<%= p.getContact() %>">
				        <button id="deleteBTN" type="submit" onclick="return confirm('Are you sure you want to delete this patient?');">Delete</button>
						
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
    <h3>Edit Patient</h3>
    <form id="editForm" action="editRecord" method="post">
      <input type="hidden" id="originalContact" name="originalContact">

      <label>Name:</label>
      <input type="text" id="editName" name="name" required><br>

      <label>Age:</label>
      <input type="number" id="editAge" name="age" required><br>

      <label>Gender:</label>
      <select id="editGender" name="gender">
        <option value="male">Male</option>
        <option value="female">Female</option>
        <option value="other">Other</option>
      </select><br>

      <label>Contact:</label>
      <input type="text" id="editContact" name="contact" required><br>

      <button type="submit" id="editBTN">Save Changes</button>
      <button type="button" onclick="closeEditPopup()">Cancel</button>
    </form>
  </div>
</div>
       <script>
function openEditPopup(name, age, gender, contact) {
    document.getElementById("editPopup").style.display = "block";
    document.getElementById("editName").value = name;
    document.getElementById("editAge").value = age;
    document.getElementById("editGender").value = gender;
    document.getElementById("editContact").value = contact;
    document.getElementById("originalContact").value = contact;
}

function closeEditPopup() {
    document.getElementById("editPopup").style.display = "none";
}
</script>
       
</body>
</html>