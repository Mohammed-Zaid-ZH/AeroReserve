// ✅ GLOBAL API BASE
const API = "http://localhost:8080";
// ✅ Check admin access
function checkAdmin() {
  const user = JSON.parse(localStorage.getItem("user"));
  if (!user || user.role !== "ADMIN") {
    alert("Admin access only!");
    window.location.href = "login.html";
  }
}

// ✅ Redirect user if not logged in when page requires login
function checkLogin() {
  const user = JSON.parse(localStorage.getItem("user"));
  if (!user) {
    window.location.href = "login.html";
  }
}


// ✅ Register User
async function registerUser(e) {
  e.preventDefault();
  const user = {
    username: document.getElementById("username").value,
    password: document.getElementById("password").value,
    email: document.getElementById("email").value,
    role: "USER"
  };

  const res = await fetch(`${API}/users/register`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(user)
  });

  if (res.ok) {
    alert("Registration Successful!");
    window.location.href = "login.html";
  } else alert("Registration Failed");
}

// ✅ Login User
async function loginUser(e) {
  e.preventDefault();
  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;

  const res = await fetch(`${API}/users`);
  const users = await res.json();

  const user = users.find(u => u.username === username && u.password === password);

  if (!user) {
    alert("Invalid Credentials");
    return;
  }

  localStorage.setItem("user", JSON.stringify(user));

  alert("Login Successful!");

  if (user.role === "ADMIN")
      window.location.href = "admin.html";
  else
      window.location.href = "index.html";
}

// ✅ Logout
function logout() {
  localStorage.removeItem("user");
  alert("Logged out");
  window.location.href = "login.html";
}

// ✅ Load flights (public page)
async function loadFlights() {
  const res = await fetch(`${API}/flights`);
  const flights = await res.json();

  const list = document.getElementById("flightsList");
  if (!list) return;

  list.innerHTML = flights.map(f => `
    <div class="col-md-4 mb-3">
      <div class="card p-3">
        <h5>${f.name}</h5>
        <p>${f.source} → ${f.destination}</p>
        <p>Seats Available: ${f.seats}</p>
        <button class="btn btn-primary w-100" onclick="bookFlight(${f.id})">Book</button>
      </div>
    </div>
  `).join('');
}

// ✅ Book a flight
async function bookFlight(flightId) {
  const user = JSON.parse(localStorage.getItem("user"));
  if (!user) {
    alert("Login to book flight!");
    window.location.href = "login.html";
    return;
  }

  const seats = prompt("Enter number of seats:");
  if (!seats || seats <= 0) return;

  const body = {
    user: { id: user.id },
    flight: { id: flightId },
    seatsBooked: seats
  };

  const res = await fetch(`${API}/bookings/book`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(body)
  });

  alert(res.ok ? "✅ Booking successful!" : "❌ Booking failed");
  loadFlights();
}

// ✅ Add flight (Admin Only)
async function addFlight(e) {
  e.preventDefault();
  checkAdmin();

  const flight = {
    name: document.getElementById("name").value,
    source: document.getElementById("source").value,
    destination: document.getElementById("destination").value,
    seats: parseInt(document.getElementById("seats").value)
  };

  const res = await fetch("http://localhost:8080/flights/add", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(flight)
  });

  if (res.ok) {
    alert("✈️ Flight Added Successfully!");
    e.target.reset();
    loadFlights();
    loadAdminBookings(); // refresh booking list
  } else {
    const msg = await res.text();
    alert("⚠️ Failed to add flight: " + msg);
  }
}

// ✅ Load bookings for user
async function loadBookings() {
  checkLogin();
  const user = JSON.parse(localStorage.getItem("user"));

  const res = await fetch(`${API}/bookings/user/${user.id}`);
  const bookings = await res.json();

  const div = document.getElementById("bookingsList");
  div.innerHTML = bookings.length
      ? bookings.map(b => `
        <div class="card mb-2 p-3">
          <strong>${b.flight.name}</strong><br>
          ${b.flight.source} → ${b.flight.destination}<br>
          Seats: ${b.seatsBooked}
        </div>
      `).join('')
      : `<p>No bookings yet.</p>`;
}

async function loadAdminBookings() {
  const res = await fetch("http://localhost:8080/bookings/all");
  const bookings = await res.json();
  const div = document.getElementById("adminBookings");

  if (!bookings.length) {
    div.innerHTML = "<p>No bookings yet.</p>";
    return;
  }

  div.innerHTML = bookings.map(b => `
    <div class="card p-3 mb-2">
      <strong>User:</strong> ${b.user.username} (${b.user.email})<br>
      <strong>Flight:</strong> ${b.flight.name} — ${b.flight.source} → ${b.flight.destination}<br>
      <strong>Seats Booked:</strong> ${b.seatsBooked}
    </div>
  `).join('');
}
