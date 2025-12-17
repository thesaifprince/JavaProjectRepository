<%@page import="java.sql.*, java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<title>All Bills</title>

<link rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>

<body class="bg-light">

<div class="container mt-4">
    <h2 class="mb-4 text-center">Billing Records</h2>
    <!--Navbar start-->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">HMS Billing System</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item"><a class="nav-link active" href="index.jsp">Home</a></li>
        <li class="nav-item"><a class="nav-link" href="loadAppointments">Patients-List</a></li>
        <li class="nav-item"><a class="nav-link" href="billing.jsp">Billing Page</a></li>
        <li class="nav-item">
          <a class="nav-link" href="editBill.jsp">Edit Bills</a>
        </li>
      </ul>
    </div>

  </div>
</nav>
<!--Navbar End-->

    <table class="table table-bordered table-striped">
        <thead class="table-dark">
            <tr>
                <th>Bill ID</th>
                <th>Patient</th>
                <th>Doctor</th>
                <th>Date</th>
                <th>Total Amount</th>
                <th>Print</th>
            </tr>
        </thead>
        <tbody>
        <%
      		  Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms",
                    "root",
                    "root");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM billing ORDER BY bill_id DESC");

            while(rs.next()){
        %>
            <tr>
                <td><%= rs.getInt("bill_id") %></td>
                <td><%= rs.getString("patient_name") %></td>
                <td><%= rs.getString("doctor_name") %></td>
                <td><%= rs.getDate("appointment_date") %></td>
                <td><%= rs.getDouble("total_amount") %></td>

                <td>
                    <a href="PdfPrinter?bill_id=<%= rs.getInt("bill_id") %>"
                       class="btn btn-primary btn-sm">
                       Print Bill
                    </a>
                </td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

</body>
</html>
