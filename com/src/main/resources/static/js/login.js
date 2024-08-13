// login.js

// Function to handle login
function handleLogin(username, password) {
    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password })
    })
        .then(response => response.json())
        .then(data => {
            if (data.accessToken) {
                saveToken(data.accessToken);
                // Optionally redirect or update the UI
                window.location.href = '/dashboard'; // Example redirect
            } else {
                // Handle login failure
                alert('Login failed!');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}
