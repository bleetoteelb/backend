package whoami.core.domain.questions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whoami.core.domain.questions.QuestionDomain;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionDomain, Long> {
    public QuestionDomain findByQuestionId(Long question_id);
    public void deleteById(Long question_id);
}
