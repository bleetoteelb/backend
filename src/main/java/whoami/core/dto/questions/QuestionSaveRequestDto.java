package whoami.core.dto.questions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class QuestionSaveRequestDto {
    private String contents;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate questionDate;

    @Builder
    public QuestionSaveRequestDto(String contents, LocalDate questionDate) {
        this.contents = contents;
        this.questionDate = questionDate;
    }

    public void printAll() {
        System.out.println(this.contents);
        System.out.println(this.questionDate);
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
