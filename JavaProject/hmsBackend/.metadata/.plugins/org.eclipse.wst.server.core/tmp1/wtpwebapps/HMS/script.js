// Navigation Handling
function showSection(id) {
    document.querySelectorAll('.section').forEach(sec => sec.classList.remove('active'));
    document.getElementById(id).classList.add('active');
}

// --------------------- PATIENT MANAGEMENT ---------------------- //

let patients = JSON.parse(localStorage.getItem("patients")) || [];
const patientForm = document.getElementById("patientForm");
const patientTable = document.querySelector("#patientTable tbody");

function savePatients() {
    localStorage.setItem("patients", JSON.stringify(patients));
}

function renderPatients() {
    patientTable.innerHTML = "";
    patients.forEach((patient, index) => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${patient.name}</td>
            <td>${patient.age}</td>
            <td>${patient.gender}</td>
            <td>${patient.contact}</td>
            <td>
                <button class="edit-btn" onclick="editPatient(${index})">Edit</button>
                <button class="delete-btn" onclick="deletePatient(${index})">Delete</button>
            </td>
        `;
        patientTable.appendChild(row);
    });
}

// patientForm.addEventListener("submit", (e) => {
//     e.preventDefault();
//     const name = document.getElementById("pname").value.trim();
//     const age = document.getElementById("page").value;
//     const gender = document.getElementById("pgender").value;
//     const contact = document.getElementById("pcontact").value.trim();

//     if (!name || !age || !contact) {
//         alert("Please fill all fields!");
//         return;
//     }

//     patients.push({ name, age, gender, contact });
//     savePatients();
//     renderPatients();
//     patientForm.reset();
// });



function editPatient(index) {
    const patient = patients[index];
    document.getElementById("pname").value = patient.name;
    document.getElementById("page").value = patient.age;
    document.getElementById("pgender").value = patient.gender;
    document.getElementById("pcontact").value = patient.contact;

    // When updating existing record
    patients.splice(index, 1);
    savePatients();
    renderPatients();
}

function deletePatient(index) {
    if (confirm("Are you sure you want to delete this patient?")) {
        patients.splice(index, 1);
        savePatients();
        renderPatients();
    }
}

// Load data when page opens
window.onload = renderPatients;


// Appointment Booking
/*const appointmentForm = document.getElementById('appointmentForm');
const appointmentTable = document.querySelector('#appointmentTable tbody');

appointmentForm.addEventListener('submit', e => {
    e.preventDefault();

    const name = document.getElementById('aname').value;
    const doctor = document.getElementById('adoctor').value;
    const date = document.getElementById('adate').value;

    const row = `<tr><td>${name}</td><td>${doctor}</td><td>${date}</td></tr>`;
    appointmentTable.insertAdjacentHTML('beforeend', row);

    appointmentForm.reset();
});*/

// Token System
let tokenNumber = 0;
document.getElementById("generateToken").addEventListener("click", function() {
    tokenNumber++;
    document.getElementById("tokenDisplay").innerText = tokenNumber;
});

// Slideshow for Dashboard
let slideIndex = 0;
function showSlides() {
    let slides = document.getElementsByClassName("slide");
    for (let i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";  // hide all slides
    }
    slideIndex++;
    if (slideIndex > slides.length) { slideIndex = 1 }
    slides[slideIndex - 1].style.display = "block"; // show current slide
    setTimeout(showSlides, 3000); // Change image every 3 seconds
}

// Initialize slideshow when page loads
window.onload = function() {
    showSlides();
};

// Toggle chatbox visibility
function toggleChat() {
    const chatBody = document.getElementById("chat-body");
    chatBody.style.display = chatBody.style.display === "flex" ? "none" : "flex";
}

// Send message
function sendMessage() {
    const input = document.getElementById("chat-input");
    const messages = document.getElementById("messages");
    const msg = input.value.trim();
    if (msg === "") return;

    // Add user message
    const userMsg = document.createElement("div");
    userMsg.textContent = "You: " + msg;
    userMsg.style.marginBottom = "10px";
    userMsg.style.fontWeight = "bold";
    messages.appendChild(userMsg);

    // Auto-reply from "system"
    const botMsg = document.createElement("div");
    botMsg.textContent = "Hospital Bot: Thank you for your message!";
    botMsg.style.marginBottom = "10px";
    messages.appendChild(botMsg);

    // Scroll to bottom
    messages.scrollTop = messages.scrollHeight;

    input.value = "";
}

// Optional: allow Enter key to send message
document.getElementById("chat-input").addEventListener("keypress", function(e) {
    if (e.key === "Enter") sendMessage();
});





