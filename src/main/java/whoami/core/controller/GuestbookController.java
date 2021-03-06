package whoami.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import whoami.core.dto.guestbooks.GuestbookDto;
import whoami.core.dto.guestbooks.GuestbookResponseDto;
import whoami.core.dto.questions.QuestionResponseDto;
import whoami.core.service.GuestbookService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/guestbook")
public class GuestbookController {
    private GuestbookService guestbookService;

    @Autowired
    public GuestbookController(GuestbookService guestbookService) {
        this.guestbookService = guestbookService;
    }
    /*
    @GetMapping(value = "")
    public ResponseEntity<List<GuestbookDto>> getGuestbook(
            @RequestParam(value="startDate") LocalDate startDate,
            @RequestParam(value="endDate") LocalDate endDate) {
        List<GuestbookDto> guestbookList = guestbookService.findByCreateDateBetween(startDate, endDate);
        return new ResponseEntity<List<GuestbookDto>>(guestbookList, HttpStatus.OK);
    }
*/
    @GetMapping(value = "")
    public Page<GuestbookResponseDto> getQuestion(
            @PageableDefault(size=5) Pageable pageable){
        return guestbookService.findAll(pageable);
    }

    @GetMapping(value = "/{guestbookId}")
    public ResponseEntity<GuestbookDto> getGuestbook(@PathVariable Long guestbookId) {
        GuestbookDto guestbook = guestbookService.findByGuestbookId(guestbookId);
        return new ResponseEntity<GuestbookDto>(guestbook, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<GuestbookDto> createGuestbook(@RequestBody GuestbookDto guestbookDto) {
        GuestbookDto guestbook = guestbookService.createGuestbook(guestbookDto);
        return new ResponseEntity<GuestbookDto>(guestbook, HttpStatus.OK);
    }
}
