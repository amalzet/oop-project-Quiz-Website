package ge.edu.freeuni.server.repository.question;

import ge.edu.freeuni.server.model.question.QuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<QuestionEntity> questionRawMapper = (ResultSet result, int numRow) ->
    {
        QuestionEntity entity1 = new QuestionEntity();
        entity1.setId(result.getLong("id"));
        entity1.setQuizId(result.getLong("quiz_id"));
        entity1.setCategory(result.getString("category"));
        entity1.setQuestion(result.getString("question"));
        entity1.setType(result.getString("type"));
        entity1.setAnswers(result.getString("correct_answer"));
        entity1.setCorrectAnswerIndex(result.getLong("correct_answer_index"));
        entity1.setPictureURL(result.getString("picture_url"));
        return entity1;
    };

    @Override
    public boolean addQuestion(QuestionEntity questionEntity) {
        String query = String.format("INSERT INTO question (quiz_id, category, question, type, " +
                        "correct_answer, correct_answer_index, picture_url)" +
                "values (\'%d\', \'%s\', \'%s\', \'%s\', \'%s\', \'%d\', \'%s\');",

                questionEntity.getQuizId(),
                questionEntity.getCategory(),
                questionEntity.getQuestion(),
                questionEntity.getType(),
                questionEntity.getAnswers(),
                questionEntity.getCorrectAnswerIndex(),
                questionEntity.getPictureURL());

        try {
            jdbcTemplate.execute(query);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public QuestionEntity getQuestionById(long id) {
        String query = String.format("select * from question where id = \'%d\';", id);
        return jdbcTemplate.queryForObject(query, questionRawMapper);
    }

}
