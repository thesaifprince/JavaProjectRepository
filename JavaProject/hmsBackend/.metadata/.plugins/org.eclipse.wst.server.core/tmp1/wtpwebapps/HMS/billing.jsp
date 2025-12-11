<%@ page import="java.sql.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<title>Billing System</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>

<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center mb-4">Hospital Billing System</h2>
<!--Navbar start  -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">HMS Billing System</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
        </li>
        
        
        <li class="nav-item">
          <a class="nav-link" href="loadAppointments">Patients-List</a>
        </li>
        
       
        
         <li class="nav-item">
          <a class="nav-link" href="editBill.jsp">Edit Bills</a>
        </li>
        
        <li class="nav-item">
          <a class="nav-link" href="viewBills.jsp">View Bills</a>
        </li>
        
        
      </ul>
     
    </div>
  </div>
</nav>
<!--Navbar End  -->
    <!-- Recent Appointments List -->
    <div class="card mb-4">
        <div class="card-header bg-primary text-white">Recent Appointments</div>
        <div class="card-body">

        <table class="table table-bordered table-hover">
            <thead>
                <tr>
                    <th>Patient</th>
                    <th>Doctor</th>
                    <th>Date</th>
                    <th>Select</th>
                </tr>
            </thead>
            <tbody>
                <%
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms","root","root");

                        PreparedStatement ps = con.prepareStatement("SELECT * FROM appointments ORDER BY id DESC");
                        ResultSet rs = ps.executeQuery();

                        while(rs.next()){
                %>
                <tr>
                    <td><%= rs.getString("pname") %></td>
                    <td><%= rs.getString("dname") %></td>
                    <td><%= rs.getDate("appointment_date") %></td>
                    <td>
                        <button class="btn btn-success btn-sm"
                          onclick="fillForm('<%= rs.getString("pname") %>',
                                            '<%= rs.getString("dname") %>',
                                            '<%= rs.getDate("appointment_date") %>')">
                            Select
                        </button>
                    </td>
                </tr>
                <% } } catch(Exception e){ out.println(e); } %>
            </tbody>
        </table>

        </div>
    </div>

    <!-- Billing Form -->
    <div class="card">
        <div class="card-header bg-dark text-white">Create Bill</div>
        <div class="card-body">

            <form action="processBilling" method="post">

                <div class="mb-3">
                    <label class="form-label">Patient Name</label>
                    <input type="text" id="pname" name="pname" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Doctor Name</label>
                    <input type="text" id="dname" name="dname" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Appointment Date</label>
                    <input type="date" id="adate" name="adate" class="form-control" required>
                </div>

                <div class="mb-3 row">
                    <div class="col-md-4">
                        <label>Consultation Fee</label>
                        <input type="number" step="0.01" name="cfee" class="form-control" required>
                    </div>
                    <div class="col-md-4">
                        <label>Medicine Charges</label>
                        <input type="number" step="0.01" name="mcharge" class="form-control">
                    </div>
                    <div class="col-md-4">
                        <label>Room Charges</label>
                        <input type="number" step="0.01" name="rcharge" class="form-control">
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Store Bill</button>
                <a href="editBill.jsp" class="btn btn-warning">Edit Bill</a>
                

            </form>

        </div>
    </div>

</div>

<script>
    function fillForm(p, d, dt){
        document.getElementById("pname").value = p;
        document.getElementById("dname").value = d;
        document.getElementById("adate").value = dt;
    }
</script>

</body>
</html>
