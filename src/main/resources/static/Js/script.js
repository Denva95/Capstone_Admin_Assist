// Simple alert when the page loads
document.addEventListener("DOMContentLoaded", () => {
    alert("Welcome to the registration page!");

    // Select the HTML form where users input their data
    const form = document.getElementById('registerForm');

    // Select the result div, which will later display the result
    const resultDiv = document.getElementById('result');

    // Add an Event Listener to the form that triggers on submit
    form.addEventListener('submit', (e) => {
        e.preventDefault(); // Prevent the default form submission behavior

        // Extracting and Validating Input Values
        const userName = document.getElementById('userName').value.trim(); // Remove whitespace
        const email = document.getElementById('email').value.trim();
        const password = document.getElementById('password').value;

        // Regex patterns for validation
        const userNameRegex = /^[A-Za-z\s]{3,50}$/; // Allows letters and spaces, 3-50 characters
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // Basic email format
        const passwordRegex = /^[A-Za-z0-9@#$%^&+=]{5,}$/; // At least 5 characters, letters, numbers, special characters

        // Validate username
        if (!userNameRegex.test(userName)) {
            alert('Invalid username. Please use letters only (3-50 characters).');
            return;
        }

        // Validate email
        if (!emailRegex.test(email)) {
            alert('Invalid email format.');
            return;
        }

        // Validate password
        if (!passwordRegex.test(password)) {
            alert('Password must be at least 5 characters long and include letters, numbers, or special characters.');
            return;
        }

        // Generating and Displaying the Result
        resultDiv.innerHTML = `
            <p><strong>Username:</strong> ${userName}</p>
            <p><strong>Email:</strong> ${email}</p>
            <h3>Thank You for Registering!</h3>
        `;

        // Styling the Result Div
        resultDiv.style.display = 'flex'; // Turns the resultDiv into a Flexbox container
        resultDiv.style.flexDirection = 'column'; // Stack child elements (video, text) vertically
        resultDiv.style.justifyContent = 'center'; // Center the content vertically
        resultDiv.style.alignItems = 'center'; // Center the content horizontally
        resultDiv.style.height = '100vh'; // Make the div take full viewport height
        resultDiv.style.textAlign = 'center'; // Center-align text content

        // Remove the form from the page
        form.remove();
    });
});