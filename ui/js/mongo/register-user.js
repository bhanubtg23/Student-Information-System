function registerUser(){
            var result = document.querySelector('.result');
            var userId = document.querySelector('#userid');
            var firstName = document.querySelector('#firstname');
            var lastName = document.querySelector('#lastname');
            var email = document.querySelector('#email');
            var password = document.querySelector('#password');

            // Creating a XHR object
            var xhr = new XMLHttpRequest();
            var url = "http://localhost:8080/auth/v1/register";

            // open a connection
            xhr.open("POST", url, true);

            // Set the request header i.e. which type of content you are sending
            xhr.setRequestHeader("Content-Type", "application/json");

            // Create a state change callback
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {

                    // Print received data from server
                    result.innerHTML = this.responseText;

                }
            };

            // Converting JSON data to string
            var data = JSON.stringify({"userId": userId.value,"email": email.value,"firstName": firstName.value,"lastName": lastName.value,"password": password.;value,"roles":{"id": 1,"role": "USER"}});

            // Sending data with the request
            xhr.send(data);
        }