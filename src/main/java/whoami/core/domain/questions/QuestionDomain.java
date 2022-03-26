package whoami.core.domain.questions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@EnableJpaAuditing
@Entity(name="question")
public class QuestionDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false, name = "question_date")
    private LocalDate questionDate;

    @CreationTimestamp
    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public QuestionDomain() {
        this.updatedAt = LocalDateTime.now();
    }

    public void printAll() {
        System.out.println(this.questionId);
        System.out.println(this.contents);
        System.out.println(this.questionDate);
        System.out.println(this.updatedAt);
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public LocalDate getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(LocalDate questionDate) {
        this.questionDate = questionDate;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
