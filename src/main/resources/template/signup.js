document.getElementById('signupForm').addEventListener('submit', function(e) {
    e.preventDefault();

    const formData = new FormData(e.target);
    const data = Object.fromEntries(formData.entries());

    fetch('http://localhost:8888/api/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            // Handle signup success (e.g., display a success message or redirect)
        })
        .catch((error) => {
            console.error('Error:', error);
            // Handle errors here (e.g., username already taken)
        });
});
