package whoami.core.dto.guestbooks;

import java.time.LocalDateTime;

public class GuestbookSaveRequestDto {
    private String contents;
    private Long ownerId;
    private Long writerId;

    public void printAll() {
        System.out.println(this.contents);
        System.out.println(this.ownerId);
        System.out.println(this.writerId);
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getWriterId() {
        return writerId;
    }

    public void setWriterId(Long writerId) {
        this.writerId = writerId;
    }
}
