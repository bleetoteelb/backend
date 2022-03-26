package whoami.core.dto.questions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;

public class QuestionUpdateRequestDto {
    private Long questionId;
    private String contents;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate questionDate;

    @Builder
    public QuestionUpdateRequestDto(Long questionId, String contents, LocalDate questionDate) {
        this.questionId = questionId;
        this.contents = contents;
        this.questionDate = questionDate;
    }

    public void printAll() {
        System.out.println(this.questionId);
        System.out.println(this.contents);
        System.out.println(this.questionDate);
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

}
