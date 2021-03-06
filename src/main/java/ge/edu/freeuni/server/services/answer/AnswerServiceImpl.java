package ge.edu.freeuni.server.services.answer;

import ge.edu.freeuni.api.converter.answer.AnswerConverter;
import ge.edu.freeuni.api.model.answer.Answer;
import ge.edu.freeuni.api.model.question.Question;
import ge.edu.freeuni.api.model.question.QuestionType;
import ge.edu.freeuni.server.model.answer.AnswerEntity;
import ge.edu.freeuni.server.model.question.QuestionEntity;
import ge.edu.freeuni.server.repository.answer.AnswerRepository;
import ge.edu.freeuni.server.repository.passedQuiz.PassedQuizRepository;
import ge.edu.freeuni.server.repository.question.QuestionRepository;
import ge.edu.freeuni.server.repository.quiz.QuizRepository;
import ge.edu.freeuni.server.repository.user.UserRepository;
import ge.edu.freeuni.server.services.passedQuiz.PassedQuizService;
import ge.edu.freeuni.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private PassedQuizService passedQuizService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private PassedQuizRepository passedQuizRepository;

    @Override
    public boolean isAnswerCorrect(Answer answer) {

        Question question = answer.getQuestion();

        switch (question.getType()) {
            case QUESTION_RESPONSE:
                return isQuestionResponseCorrect(answer, question);
            case FILL_IN_THE_BLANK:
                return isFillInBlankCorrect(answer, question);
            case MULTIPLE_CHOICE:
                return isMultipleChoiceCorrect(answer, question);
            case TRUE_FALSE:
                return isTrueOrFalseCorrect(answer, question);
            default:
                return isPictureResponseCorrect(answer, question);

        }

    }
        private boolean isPictureResponseCorrect(Answer answer, Question question) {
            return isQuestionResponseCorrect(answer, question);
        }

        private boolean isTrueOrFalseCorrect(Answer answer, Question question) {
            return isFillInBlankCorrect(answer, question);
        }

        private boolean isMultipleChoiceCorrect(Answer answer, Question question) {
            return answer.getUserAnswer().equals(question.getChoices().get((int) question.getCorrectAnswerIndex()));
        }

        private boolean isFillInBlankCorrect(Answer answer, Question question) {
            return answer.getUserAnswer().equals(question.getCorrectAnswer());
        }

        private boolean isQuestionResponseCorrect(Answer answer, Question question){
            String userAnswer = answer.getUserAnswer();
            return StringUtils.stringToList(question.getCorrectAnswer(),',').contains(userAnswer);
        }

    @Override
    public boolean addAnswer(Answer answer) {
        answer.setPassedQuiz(passedQuizService.getActivePassedQuiz());
        return answerRepository.addAnswer(AnswerConverter.AnswerToEntity(answer, quizRepository));
    }

    @Override
    public Answer getAnswerById(int id) {
        return AnswerConverter.AnswerEntityToAnswer(answerRepository
                        .getAnswerById(id),
                questionRepository,
                quizRepository,
                passedQuizRepository,
                userRepository
        );
    }

}
