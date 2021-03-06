package ge.edu.freeuni.api.converter.quiz;

import ge.edu.freeuni.api.converter.user.UserConverter;
import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.server.model.quiz.QuizEntity;
import ge.edu.freeuni.server.repository.user.UserRepository;
import ge.edu.freeuni.utils.Wyvili;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public final class QuizConverter {

	public static Quiz entityToQuiz(UserRepository userRepository,
									QuizEntity quizEntity) {
		return Quiz.builder()
				.id(quizEntity.getId())
				.name(quizEntity.getName())
				.creator(UserConverter.entityToUser(userRepository.getUserById(quizEntity.getCreatorId())))
				.description(quizEntity.getDescription())
				.creationDate(quizEntity.getCreationDate())
				.build();
	}

	public static QuizEntity quizToEntity(Quiz quiz) {

		return QuizEntity.builder()
				.id(quiz.getId())
				.creatorId(quiz.getCreator().getId())
				.name(quiz.getName())
				.creationDate(quiz.getCreationDate())
				.description(quiz.getDescription())
				.build();
	}

	public static List<Quiz> entityToQuizList(UserRepository userRepository,
											  List<QuizEntity> allQuizzes) {
		List<Quiz> res = new ArrayList<>();
		for (QuizEntity quizEntity :
				allQuizzes) {
			res.add(QuizConverter.entityToQuiz(userRepository, quizEntity));
		}
		return res;
	}

	public static List<Wyvili<Quiz, Long>> entityToQuizPairList(UserRepository userRepository,
																List<Wyvili<QuizEntity, Long>> topRatedQuizzes) {
		List<Wyvili<Quiz, Long>> res = new ArrayList<>();

		for (Wyvili<QuizEntity, Long> wyvili :
				topRatedQuizzes) {
			res.add(new Wyvili<>(QuizConverter.entityToQuiz(userRepository, wyvili.first), wyvili.second));
		}

		return res;
	}
}
