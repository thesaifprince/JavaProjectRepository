<%@ page import="java.sql.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<title>Billing Records</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>

<style>
    body { background: #f2f4f8; }
    .bill-card { border-radius: 15px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); }
    .table thead { background: #0d6efd; color: white; }
    .table-hover tbody tr:hover { background: #eef3ff; }
    .search-box { border-radius: 30px; padding-left: 20px; }
</style>

</head>
<body>

<div class="container mt-5">

    <div class="text-center mb-4">
        <h2 class="fw-bold">Hospital Billing Records</h2>
    </div>
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
    <div class="card bill-card">
        <div class="card-header bg-dark text-white d-flex justify-content-between align-items-center">
            <span class="fw-bold">All Billing Records</span>
            <a href="billing.jsp" class="btn btn-sm btn-light">+ Create New Bill</a>
        </div>

        <div class="card-body p-0">
            <table class="table table-bordered table-hover mb-0">
                <thead>
                    <tr>
                        <th>Bill ID</th>
                        <th>Patient</th>
                        <th>Doctor</th>
                        <th>Date</th>
                        <th>Consultation</th>
                        <th>Medicine</th>
                        <th>Room</th>
                        <th>Total (RS)</th>
                        <th style="width:130px;">Actions</th>
                    </tr>
                </thead>

                <tbody>
                    <%
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "root");

                        String search = request.getParameter("search");
                        PreparedStatement ps;

                        if(search != null && !search.trim().isEmpty()){
                            ps = con.prepareStatement(
                                "SELECT * FROM billing WHERE patient_name LIKE ? OR doctor_name LIKE ? OR bill_id LIKE ? ORDER BY bill_id DESC"
                            );
                            ps.setString(1, "%" + search + "%");
                            ps.setString(2, "%" + search + "%");
                            ps.setString(3, "%" + search + "%");
                        } else {
                            ps = con.prepareStatement("SELECT * FROM billing ORDER BY bill_id DESC");
                        }

                        ResultSet rs = ps.executeQuery();
                        boolean empty = true;

                        while(rs.next()){
                            empty = false;
                    %>

                    <tr>
                        <td><strong>#<%= rs.getInt("bill_id") %></strong></td>
                        <td id="pname<%= rs.getInt("bill_id") %>"><%= rs.getString("patient_name") %></td>
                        <td id="dname<%= rs.getInt("bill_id") %>"><%= rs.getString("doctor_name") %></td>
                        <td id="adate<%= rs.getInt("bill_id") %>"><%= rs.getDate("appointment_date") %></td>
                        <td id="cfee<%= rs.getInt("bill_id") %>"><%= rs.getDouble("consultation_fee") %></td>
                        <td id="mcharge<%= rs.getInt("bill_id") %>"><%= rs.getDouble("medicine_charges") %></td>
                        <td id="rcharge<%= rs.getInt("bill_id") %>"><%= rs.getDouble("room_charges") %></td>
                        <td class="fw-bold text-success" id="total<%= rs.getInt("bill_id") %>">
                            Rs. <%= rs.getDouble("total_amount") %>
                        </td>
                        <td>
                            <button class="btn btn-danger btn-sm w-100" 
                                    onclick="openRemarksModal(<%= rs.getInt("bill_id") %>)">Print PDF</button>
                        </td>
                    </tr>

                    <% } %>

                    <% if(empty){ %>
                    <tr>
                        <td colspan="9" class="text-center text-muted py-4">No billing records found.</td>
                    </tr>
                    <% } %>

                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Remarks Modal -->
<div class="modal fade" id="remarksModal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Add Remarks Before Printing</h5>
        <button class="btn-close" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body">
        <textarea id="remarksText" class="form-control" rows="4"
                  placeholder="Enter remarks that will appear in the PDF"></textarea>
      </div>
      <div class="modal-footer">
        <button class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
        <button class="btn btn-primary" onclick="generatePDF()">Generate PDF</button>
      </div>
    </div>
  </div>
</div>

<script>
let selectedBillData = {};

function openRemarksModal(button){
    selectedBillData.patient = button.getAttribute("data-patient");
    selectedBillData.doctor = button.getAttribute("data-doctor");
    selectedBillData.date = button.getAttribute("data-date");
    selectedBillData.cfee = parseFloat(button.getAttribute("data-cfee")) || 0;
    selectedBillData.mcharge = parseFloat(button.getAttribute("data-mcharge")) || 0;
    selectedBillData.rcharge = parseFloat(button.getAttribute("data-rcharge")) || 0;

    document.getElementById('remarksText').value = "";
    let modal = new bootstrap.Modal(document.getElementById('remarksModal'));
    modal.show();
}

function generatePDF(){
    const pdf = new jsPDF({ orientation: "portrait", unit: "mm", format: "a4" });

    // HEADER
    pdf.setFontSize(20);
    pdf.text("HMS - Hospital Bill", 105, 20, { align: "center" });

    // PATIENT DETAILS
    pdf.setFontSize(12);
    pdf.text(`Patient Name: ${selectedBillData.patient}`, 20, 40);
    pdf.text(`Doctor: ${selectedBillData.doctor}`, 20, 50);
    pdf.text(`Appointment Date: ${selectedBillData.date}`, 20, 60);
    pdf.text(`Consultation Fee: Rs ${selectedBillData.cfee}`, 20, 80);
    pdf.text(`Medicine Charges: Rs ${selectedBillData.mcharge}`, 20, 90);
    pdf.text(`Room Charges: Rs ${selectedBillData.rcharge}`, 20, 100);
    const total = (selectedBillData.cfee + selectedBillData.mcharge + selectedBillData.rcharge).toFixed(2);
    pdf.text(`Total Amount: Rs ${total}`, 20, 115);

    // REMARKS
    const remarks = document.getElementById('remarksText').value || "No remarks provided.";
    pdf.setFontSize(14);
    pdf.text("Remarks:", 20, 140);
    pdf.setFontSize(12);
    pdf.text(remarks, 20, 150);

    pdf.save(`Bill_${selectedBillData.patient}.pdf`);

    // Store into DB
    savePDFLog(selectedBillData.patient, remarks);

    // Close modal
    bootstrap.Modal.getInstance(document.getElementById('remarksModal')).hide();
}

function savePDFLog(patientName, remarks){
    fetch("storePDFLog", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: "patient_name=" + encodeURIComponent(patientName) +
              "&remarks=" + encodeURIComponent(remarks)
    });
}

</script>

</body>
</html>
