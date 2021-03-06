<%@ page import="ge.edu.freeuni.api.model.question.Question" %>
<%@ page import="ge.edu.freeuni.api.model.quiz.Quiz" %>
<%@ page import="ge.edu.freeuni.api.model.user.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: m.ormotsadze
  Date: 7/26/2020
  Time: 2:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>VIEW QUIZ</title>
    <%
        Quiz quiz = (Quiz) request.getAttribute("quiz");
        User creator = quiz.getCreator();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = formatter.format(quiz.getCreationDate());
        List<Question> questions = (List<Question>) request.getAttribute("questions");

    %>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/quizDescription.css"/>
</head>
<body>

<div class="bgimg w3-display-container w3-text-white">

    <div class="w3-display-topleft w3-container w3-xlarge">
        <p>
            <button onclick="document.getElementById('aboutQuiz').style.display='block'" class="w3-button w3-black">
                About this quiz
            </button>
        </p>
        <p>
            <button onclick="document.getElementById('questions').style.display='block'" class="w3-button w3-black">Quiz
                questions
            </button>
        </p>
        <p>
            <button onclick="document.getElementById('aboutAuthor').style.display='block'" class="w3-button w3-black">
                About author
            </button>
        </p>
        <p>
            <button class="w3-button w3-black"><a href="/userhomepage">Home Page</a></button>
        </p>
    </div>

    <div class="w3-display-topright w3-container w3-xlarge">
        <%
            Long quizId = (Long) request.getAttribute("quizId");
            out.print(String.format("<p><button class=\"w3-button w3-black\"><a  href=\"/logOut\">Log Out</a></button></p>"));
        %>
    </div>

</div>

<!-- About Quiz -->
<div id="aboutQuiz" class="w3-modal">
    <div class="w3-modal-content w3-animate-zoom">
        <div class="w3-container w3-black w3-display-container">
            <span onclick="document.getElementById('aboutQuiz').style.display='none'"
                  class="w3-button w3-display-topright w3-large">x</span>
            <h1>About Quiz</h1>
        </div>

        <div class="w3-container">

            <h5>Name: <b><%=quiz.getName()%>
            </b></h5>
            <h5>Description: <b><%=quiz.getDescription()%>
            </b></h5>
            <h5>creation date: <b><%=date%>
            </b></h5>
        </div>
    </div>
</div>

<!-- About Questions -->
<div id="questions" class="w3-modal">
    <div class="w3-modal-content w3-animate-zoom">
        <div class="w3-container w3-black w3-display-container">
            <span onclick="document.getElementById('questions').style.display='none'"
                  class="w3-button w3-display-topright w3-large">x</span>
            <h1>Quiz Questions</h1>
        </div>

        <div class="w3-container">
            <%
                for (int i = 0; i < questions.size(); i++) {
                    Question question = questions.get(i);
                    String questionText = question.getQuestion();
                    String questionType = String.valueOf(question.getType());
                    out.print("<h5><b>Question #" + (i + 1) + ", Question text: '" + questionText + "', Question type: '" + questionType + "'</b></h5>");
                }
            %>
        </div>
    </div>
</div>

<!-- About Author -->
<div id="aboutAuthor" class="w3-modal">
    <div class="w3-modal-content w3-animate-zoom">
        <div class="w3-container w3-black w3-display-container">
            <span onclick="document.getElementById('aboutAuthor').style.display='none'"
                  class="w3-button w3-display-topright w3-large">x</span>
            <h1>About Quiz</h1>
        </div>

        <div class="w3-container">
            <h5>Creator Username: <b><%=creator.getUsername()%>
            </b></h5>
        </div>
    </div>
</div>


<!-- start Quiz -->
<div id="startQuiz" class="button">

</div>


<script>
    window.onclick = function (event) {
        if (event.target == document.getElementById("aboutQuiz")) {
            document.getElementById("aboutQuiz").style.display = "none";
        } else if (event.target == document.getElementById("aboutAuthor")) {
            document.getElementById("aboutQuiz").style.display = "none";
        } else if (event.target == document.getElementById("questions")) {
            document.getElementById("questions").style.display = "none";
        }

    }

</script>

</body>
</html>
