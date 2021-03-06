package ge.edu.freeuni.api;

import ge.edu.freeuni.api.model.passedQuiz.PassedQuiz;
import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.services.authentication.AuthenticationService;
import ge.edu.freeuni.server.services.passedQuiz.PassedQuizService;
import ge.edu.freeuni.server.services.question.QuestionService;
import ge.edu.freeuni.server.services.quiz.QuizService;
import ge.edu.freeuni.server.services.user.UserServiceImpl;
import ge.edu.freeuni.utils.Wyvili;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;


    @RequestMapping("/createNewQuiz")
    public String createQuiz(Map<String, Object> model) {
        return "createQuiz";
    }

    @RequestMapping("/preQuiz/{quizId}")
    public String startQuiz(@PathVariable Long quizId, Map<String, Object> model) {

        model.put("index", 0);
        model.put("quizId", quizId);

        return "preQuizPage";
    }

    @RequestMapping("/quizDescriptionPage/{quizId}")
    public String aboutQuiz(@PathVariable Long quizId, Map<String, Object> model) {
        Quiz toAdd = quizService.getQuizById(quizId);
        List<Wyvili<User, Long>> topRatedUsersByQuiz = quizService.getTopRatedUsersByQuiz(toAdd);
        model.put("quiz", toAdd);
        model.put("quizId", quizId);
        model.put("topRatedUsersByQuiz",topRatedUsersByQuiz);
        return "quizDescription";
    }

    @RequestMapping("/makeQuestions")
    public String makeQuestions(Map<String, Object> model) {
        return "makeQuestions";
    }

    @RequestMapping("/startQuizMaking")
    public String startQuizMaking(@RequestParam String quizName, @RequestParam String description,
                                  Map<String, Object> model) {
        quizService.startMakingQuiz(new Quiz(quizName, description));
        return "makeQuestions";
    }

    @RequestMapping("/multipleChoice")
    public String multipleChoice(Map<String, Object> model) {
        return "questionTypes/multipleChoice";
    }

    @RequestMapping("/multipleChoiceSubmitted")
    public String multipleChoiceSubmitted(@RequestParam String choiceCount, Map<String, Object> model) {
        model.put("choiceCount", choiceCount);
        return "questionTypes/multipleChoiceSubmitted";
    }

    @RequestMapping("/trueFalse")
    public String trueFalse(Map<String, Object> model) {
        return "questionTypes/trueFalse";
    }

    @RequestMapping("/fillBlank")
    public String fillBlank(Map<String, Object> model) {
        return "questionTypes/fillBlank";
    }

    @RequestMapping("/questionResponse")
    public String questionResponse(Map<String, Object> model) {
        return "questionTypes/questionResponse";
    }


    @RequestMapping("/questionResponseSubmitted")
    public String questionResponseSubmitted(@RequestParam String choiceCount, Map<String, Object> model) {
        model.put("choiceCount", choiceCount);
        return "questionTypes/questionResponseSubmitted";
    }

    @RequestMapping("/imageAnswers")
    public String imageAnswers(Map<String, Object> model) {
        return "questionTypes/imageAnswers";
    }

    @RequestMapping("/imageAnswersSubmitted")
    public String imageAnswersSubmitted(@RequestParam String choiceCount, Map<String, Object> model) {
        model.put("choiceCount", choiceCount);
        return "questionTypes/imageAnswersSubmitted";
    }

    @RequestMapping("/viewQuiz")
    public String viewQuiz(Map<String, Object> model) {
        Quiz quiz = quizService.finishMakingQuiz();
        model.put("quiz", quiz);
        model.put("questions", questionService.getAllQuestionsByQuiz(quiz));
        return "viewQuiz";
    }

    @RequestMapping("/allAvailableQuizzes")
    public String allAvailableQuizzes(Map<String, Object> model){
        List<Quiz> available = quizService.getAllQuizzes();
        model.put("available", available);
        return "availableQuizes";
    }

}
