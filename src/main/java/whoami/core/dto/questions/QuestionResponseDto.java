package whoami.core.dto.questions;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class QuestionResponseDto {
    private Long questionId;
    private String contents;
    private LocalDate questionDate;
    private LocalDateTime updatedAt;

    @Builder
    public QuestionResponseDto(Long questionId, String contents, LocalDate questionDate, LocalDateTime updatedAt) {
        this.questionId = questionId;
        this.contents = contents;
        this.questionDate = questionDate;
        this.updatedAt = updatedAt;
    }

    @Builder
    public QuestionResponseDto(){}

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
