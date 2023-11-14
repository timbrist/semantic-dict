document.getElementById('signupForm').addEventListener('submit', function(e) {
    e.preventDefault();

    const name = document.getElementById('name').value;
    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const requestData = {
        name: name,
        username: username,
        email: email,
        password: password
    };
    console.log(requestData)
    fetch('http://localhost:8888/api/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestData)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            document.getElementById("message").innerText = "Sign up successful!";
            document.getElementById("message").classList.add("success");
            // Handle signup success (e.g., display a success message or redirect)
        })
        .catch((error) => {
            console.error('Error:', error);
            // Handle errors here (e.g., username already taken)
        });
});
