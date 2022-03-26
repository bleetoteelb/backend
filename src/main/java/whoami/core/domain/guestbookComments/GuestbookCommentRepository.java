package whoami.core.domain.guestbookComments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whoami.core.domain.guestbookComments.GuestbookCommentDomain;

@Repository
public interface GuestbookCommentRepository extends JpaRepository<GuestbookCommentDomain, Long> {
    public GuestbookCommentDomain findByGuestbookCommentId(Long guestbook_comment_id);
}
