document.getElementById('login-form').addEventListener('submit', function(e) {
    e.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const requestData = {
        username: username,
        password: password
    };
    console.log(requestData)


    fetch('http://localhost:8888/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestData)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            document.getElementById("message").innerText = "Login successful!";
            document.getElementById("message").classList.add("success");
            window.location = "http://localhost:8888/dictionary.html";
            // Handle login success (e.g., redirect to a dashboard)
        })
        .catch((error) => {
            console.error('Error:', error);
            // Handle errors here (e.g., invalid credentials)
        });
});
const passwordInput = document.getElementById("password");
const passwordToggle = document.getElementById("password-toggle");

passwordToggle.addEventListener("click", () => {
    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        passwordToggle.classList.remove("fa-eye");
        passwordToggle.classList.add("fa-eye-slash");
    } else {
        passwordInput.type = "password";
        passwordToggle.classList.remove("fa-eye-slash");
        passwordToggle.classList.add("fa-eye");
    }
});