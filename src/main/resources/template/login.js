document.getElementById('loginForm').addEventListener('submit', function(e) {
    e.preventDefault();

    const formData = new FormData(e.target);
    const data = Object.fromEntries(formData.entries());

    fetch('http://localhost:8888/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            // Handle login success (e.g., redirect to a dashboard)
        })
        .catch((error) => {
            console.error('Error:', error);
            // Handle errors here (e.g., invalid credentials)
        });
});
