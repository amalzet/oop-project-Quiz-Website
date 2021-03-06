<!DOCTYPE html>
<html>
<head>

    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/createQuiz.css"/>
    <title>Create Message</title>

</head>
<body>

<div class="bgimg w3-display-container w3-text-white">

    <div class="w3-display-topleft w3-container w3-xlarge">
        <p><button onclick="document.getElementById('createMessage').style.display='block'" class="w3-button w3-black">Create Message</button></p>

    </div>


    <div class="w3-display-topright w3-container w3-xlarge">
        <p><button class="w3-button w3-black"><a  href="/userhomepage">Home Page</a></button></p>
        <p><button class="w3-button w3-black"><a  href="/">Log Out</a></button></p>
    </div>

</div>

<!-- Log In Form -->
<div id="createMessage" class="w3-modal">
    <div class="w3-modal-content w3-animate-zoom">
        <div class="w3-container w3-black w3-display-container">
            <span onclick="document.getElementById('createMessage').style.display='none'" class="w3-button w3-display-topright w3-large">x</span>
            <h1>Create Message</h1>
        </div>
        <div class="login-item">
            <form action="/sendMessage" method="post">
                <%
                    boolean valid = (boolean) request.getAttribute("valid");
                    if(!valid){
                        out.print("<label> Username is incorrect! </label>");
                    }
                %>
                <label for = "mailContext">Receiver Username:</label>
                <input type = "text" id = "receiverUsername" name = "receiverUsername" required placeholder="Enter receiver username"><br>

                <label for = "mailContext">Message Context:</label>
                <textarea id="mailContext" name = "mailContext" required placeholder = "Enter Message Context" cols = 60></textarea><br>

                <input type = "submit" id = "submitButton" value = "Send Mail">
            </form>


        </div>
    </div>
</div>


<script>
    window.onclick = function(event) {
        if (event.target == document.getElementById("createMessage")) {
            document.getElementById("createMessage").style.display = "none";
        }
    }

</script>
</body>
</html>

