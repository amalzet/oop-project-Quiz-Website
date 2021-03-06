<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/createQuiz.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/makeQuestions.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/multipleChoice.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/multipleChoiceSubmitted.css"/>
    <title>Make Questions</title>

</head>
<body>

<div class="bgimg w3-display-container w3-text-white">


    <h1>Make Questions</h1>~
    <form class="select">
        <label>Choose question type:</label>
        <select id="questionType" name="questionType">
            <option>Select</option>
            <option>Multiple Choice</option>
            <option>True/False</option>
            <option>Fill In Blank</option>
            <option>Image Answers</option>
            <option>Question/Response</option>
        </select>

        <div class="select_arrow">
        </div>
        <br>
        <br>
    </form>

    <form action="${pageContext.request.contextPath}/addMultipleChoice" method="post">

        <label>Enter question:</label>
        <textarea id="questionBox" name = "question" required placeholder = "Ask anything" cols = 60></textarea><br>
        <%
            int choice = Integer.parseInt((String) request.getAttribute("choiceCount"));
            List<Character> chars = new ArrayList<>();
            char ch = 'a';
            int id = 1;
            for (int i = 0; i < choice; i++, ch++, id++){
                out.print("<label>Enter choice " + ch + ":  </label>");
                out.print("<input type=\"text\" name=\"choice" + id + "\" required placeholder=\"Enter Choice\" id=\"choice\"/>");
                out.print("<br>");
                chars.add(ch);
            }

            String toShow = "<label>Enter if correct answer is either ";
            for(int i = 0; i < chars.size() - 1; i++){
                toShow += chars.get(i) +  ", ";
            }
            toShow += "or " + chars.get(chars.size() - 1);
            toShow += "</label>";
            out.print(toShow);
        %>

        <input type="text" name="correctAnswer" required placeholder="Enter correct answer" id = "correctAnswer"/>
        <input type="submit" value = "Add question" id="submitButton">
    </form>

</div>

<script>

    window.onclick = function (event) {
        if (event.target == document.getElementById("questionType")) {
            if (document.getElementById("questionType").value == "True/False") {
                window.location.href = '/trueFalse';
            } else if (document.getElementById("questionType").value == "Fill In Blank") {
                window.location.href = '/fillBlank';
            } else if(document.getElementById("questionType").value == "Fill In Multiple Blanks") {
                window.location.href = '/fillMultipleBlanks';
            } else if (document.getElementById("questionType").value == "Multiple Answers") {
                window.location.href = '/multipleAnswers';
            } else if (document.getElementById("questionType").value == "Image Answers") {
                window.location.href = '/imageAnswers';
            } else if (document.getElementById("questionType").value == "Matching") {
                window.location.href = '/matching';
            } else if(document.getElementById("questionResponse").value == "Question/Response") {
                window.location.href = '/questionResponse';
            }
        }
        // else if(event.target == document.getElementById("about")){
        //     document.getElementById("about").style.display = "none";
        // } else if(event.target == document.getElementById("topUsers")){
        //     document.getElementById("topUsers").style.display = "none";
        // } else if(event.target == document.getElementById("topQuizzes")){
        //     document.getElementById("topQuizzes").style.display = "none";
        // }
    }


</script>
</body>
</html>

