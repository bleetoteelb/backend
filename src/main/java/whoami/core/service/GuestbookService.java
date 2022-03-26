package whoami.core.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import whoami.core.domain.guestbooks.GuestbookDomain;
import whoami.core.domain.questions.QuestionDomain;
import whoami.core.dto.guestbooks.GuestbookDto;
import whoami.core.dto.guestbooks.GuestbookResponseDto;
import whoami.core.dto.guestbooks.GuestbookSaveRequestDto;
import whoami.core.dto.questions.QuestionResponseDto;
import whoami.core.dto.questions.QuestionSaveRequestDto;
import whoami.core.exception.CustomMessage;
import whoami.core.exception.RecordNotFoundException;
import whoami.core.domain.guestbooks.GuestbookRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestbookService {
    @Autowired
    private GuestbookRepository guestbookRepository;

    @Autowired
    ModelMapper modelMapper;

    public Page<GuestbookResponseDto> findAll(Pageable pageable){
        Page<GuestbookDomain> guestbookDomains = guestbookRepository.findAll(pageable);
        return guestbookDomains.map(this::mapQuestionDomainToResponseDto);
    }

    public List<GuestbookDto> findByCreateDateBetween(LocalDate startDate, LocalDate endDate){
        List<GuestbookDomain> guestbookDomainList =
                guestbookRepository.findByCreatedAtBetween(startDate.atStartOfDay(), endDate.atStartOfDay());
        return guestbookDomainList.stream()
                .map(this::copyGuestbookDomainToDto)
                .collect(Collectors.toList());
    }

    public GuestbookDto findByGuestbookId(Long guestbookId){
        if (guestbookRepository.existsById(guestbookId)) {
            GuestbookDomain guestbookDomain = guestbookRepository.findByGuestbookId(guestbookId);
            return copyGuestbookDomainToDto(guestbookDomain);
        } else {
            // TODO: Throw some exception
            throw new RecordNotFoundException(CustomMessage.DOESNOT_EXIT + guestbookId);
        }
    }
    /*
    public QuestionResponseDto createQuestion(QuestionSaveRequestDto questionSaveRequestDto) {
        try {
            questionSaveRequestDto.printAll();
            QuestionDomain questionDomain = modelMapper.map(questionSaveRequestDto,QuestionDomain.class);
            questionDomain = questionRepository.save(questionDomain);
            return modelMapper.map(questionDomain,QuestionResponseDto.class);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException(ex.getCause().getCause().getMessage());
        }
    }*/
    public GuestbookResponseDto createGuestbook(GuestbookSaveRequestDto guestbookSaveRequestDto) {
        try {
            GuestbookDomain guestbookDomain = modelMapper.map(guestbookSaveRequestDto,GuestbookDomain.class);
            guestbookDomain = guestbookRepository.save(guestbookDomain);
            return modelMapper.map(guestbookDomain,GuestbookResponseDto.class);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException(ex.getCause().getCause().getMessage());
        }
    }

    private GuestbookResponseDto mapGuestbookDomainToResponseDto(GuestbookDomain guestbookDomain) {
        return modelMapper.map(guestbookDomain,GuestbookResponseDto.Class);
    }
}
