<%@ page import="java.util.*, register_patients.Patient" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hospital Management System</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <!-- Navigation Bar -->
    <header>
        <h1>Hospital Management System</h1>
        <nav>
            <ul>
                <li><a href="#" onclick="showSection('dashboard')">Dashboard</a></li>
                <li><a href="#" onclick="showSection('patients')">Patients</a></li>
                <li><a href="loadPatients">Patients List</a></li>
                <li><a href="#" onclick="showSection('appointments')">Appointments</a></li>
                <li><a href="loadAppointments">Appointments List</a></li>
                <li><a href="#" onclick="showSection('tokens')">Token System</a></li>
            </ul>
        </nav>
    </header>
   

    <!-- Chatbox -->
<!-- <div class="chatbox" id="chatbox">
    <div class="chat-header" onclick="toggleChat()">💬 Chat with us</div>
    <div class="chat-body" id="chat-body">
        <div class="messages" id="messages"></div>
        <input type="text" id="chat-input" placeholder="Type a message..." />
        <button onclick="sendMessage()">Send</button>
    </div>
</div> -->


    <!-- Dashboard Section -->
   <section id="dashboard" class="section active">
    <h2>Dashboard</h2>
    <p>Welcome to the Hospital Management System! Use the navigation menu to manage patients, appointments, and tokens.</p>

    <!-- Slideshow -->
    <div class="slideshow-container">
        <img class="slide" src="image.jpg" alt="Hospital Image 1">
        <img class="slide" src="image1.jpg" alt="Hospital Image 2">
        <img class="slide" src="image2.jpg" alt="Hospital Image 3">
    </div>
</section>


    <!-- Patient Registration Section -->
    <section id="patients" class="section">
        <h2>Register New Patient</h2>
        <form id="patientForm" action="register" method="post">
            <label>Full Name:</label>
            <input type="text" id="pname" name="pname" required>

            <label>Age:</label>
            <input type="number" id="page" name="page" required>

            <label>Gender:</label>
            <select id="pgender" name="pgender">
                <option  value="male">Male</option>
                <option value="female">Female</option>
                <option value="other">Other</option>
            </select>

            <label>Contact:</label>
            <input type="text" id="pcontact" name="pcontact" required>

            <input type="submit">
        </form>

       
    </section>

    <!-- Appointment Section -->
    <section id="appointments" class="section">
        <h2>Book Appointment</h2>
        <form id="appointmentForm" action="bookappointment" method="post">
            <label>Patient Name:</label>
            <input type="text" id="aname" name="aname" required>

            <label>Doctor:</label>
            <select id="adoctor" name="adoctor">
                <option value="Dr. Sharma (Cardiologist)">Dr. Sharma (Cardiologist)</option>
                <option value="Dr. Patel (Dermatologist)">Dr. Patel (Dermatologist)</option>
                <option value="Dr. Verma (General Physician)">Dr. Verma (General Physician)</option>
            </select>

            <label>Date:</label>
            <input type="date" id="adate" name="adate" required>

            <!-- <button type="submit">Book</button> -->
            <input type="submit" value="Book Appointment">
        </form>

        

    <!-- Token System Section -->
    <section id="tokens" class="section">
        <h2>Token System</h2>
        <p>Click below to generate a token for the next patient.</p>
        <button id="generateToken">Generate Token</button>
        <h3>Current Token: <span id="tokenDisplay">0</span></h3>
    </section>

    <script src="script.js"></script>
</body>
</html>
