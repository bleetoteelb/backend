package whoami.core.dto.questions;

import lombok.Builder;

import java.time.LocalDate;

public class QuestionRequestDto {
    private Long questionId;
    private String contents;
    private LocalDate questionDate;

    @Builder
    public QuestionRequestDto(Long questionId, String contents, LocalDate questionDate) {
        this.questionId = questionId;
        this.contents = contents;
        this.questionDate = questionDate;
    }
}
