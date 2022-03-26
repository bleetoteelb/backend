package whoami.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import whoami.core.dto.questions.*;
import whoami.core.service.QuestionService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(value = "/{questionId}")
    public QuestionResponseDto getQuestion(@PathVariable Long questionId) {
        return questionService.findByQuestionId(questionId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/list")
    public Page<QuestionResponseDto> getQuestionList(
            @PageableDefault(size=5) Pageable pageable){
        return questionService.findAll(pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
        questionService.deleteByQuestionId(questionId);
        return ResponseEntity.ok().body("Success");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "")
    public QuestionResponseDto createQuestion(@RequestBody QuestionSaveRequestDto questionSaveRequestDto) {
        questionSaveRequestDto.printAll();
        return questionService.createQuestion(questionSaveRequestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(value = "")
    public QuestionResponseDto updateQuestion(@RequestBody QuestionUpdateRequestDto questionUpdateRequestDto) {
        questionUpdateRequestDto.printAll();
        return questionService.updateQuestion(questionUpdateRequestDto);
    }
}
