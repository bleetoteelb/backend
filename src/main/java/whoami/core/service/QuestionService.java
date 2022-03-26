package whoami.core.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import whoami.core.domain.questions.QuestionDomain;
import whoami.core.dto.questions.*;
import whoami.core.exception.CustomMessage;
import whoami.core.exception.RecordNotFoundException;
import whoami.core.domain.questions.QuestionRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    ModelMapper modelMapper;

    public Page<QuestionResponseDto> findAll(Pageable pageable){
        Page<QuestionDomain> questionDomains = questionRepository.findAll(pageable);
        return questionDomains.map(this::mapQuestionDomainToResponseDto);
    }

    public QuestionResponseDto findByQuestionId(Long questionId){
        if (questionRepository.existsById(questionId)) {
            QuestionDomain questionDomain = questionRepository.findByQuestionId(questionId);
            return modelMapper.map(questionDomain,QuestionResponseDto.class);
        } else {
            // TODO: Throw some exception
            throw new RecordNotFoundException(CustomMessage.DOESNOT_EXIT + questionId);
        }
    }

    public void deleteByQuestionId(Long questionId) {
        if (questionRepository.existsById(questionId)) {
            questionRepository.deleteById(questionId);
            return;
        } else {
            // TODO: Throw some exception
            throw new RecordNotFoundException(CustomMessage.DOESNOT_EXIT + questionId);
        }
    }

    public QuestionResponseDto createQuestion(QuestionSaveRequestDto questionSaveRequestDto) {
        try {
            questionSaveRequestDto.printAll();
            QuestionDomain questionDomain = modelMapper.map(questionSaveRequestDto,QuestionDomain.class);
            questionDomain = questionRepository.save(questionDomain);
            return modelMapper.map(questionDomain,QuestionResponseDto.class);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException(ex.getCause().getCause().getMessage());
        }
    }

    public QuestionResponseDto updateQuestion(QuestionUpdateRequestDto questionUpdateRequestDto) {
        if (questionRepository.existsById(questionUpdateRequestDto.getQuestionId())) {
            QuestionDomain questionDomain = modelMapper.map(questionUpdateRequestDto, QuestionDomain.class);
            questionDomain = questionRepository.save(questionDomain);
            return modelMapper.map(questionDomain, QuestionResponseDto.class);
        } else {
            // TODO: Throw some exception
            throw new RecordNotFoundException(CustomMessage.DOESNOT_EXIT + questionUpdateRequestDto.getQuestionId());
        }
    }

    private QuestionResponseDto mapQuestionDomainToResponseDto(QuestionDomain questionDomain) {
        return modelMapper.map(questionDomain,QuestionResponseDto.class);
    }
}
