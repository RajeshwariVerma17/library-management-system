import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a registered library member.
 * Tracks borrowed books using Java Collections (ArrayList).
 */
public class Member {
    private final String memberId;
    private final String name;
    private final List<String> borrowedBookIds;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBookIds = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public List<String> getBorrowedBookIds() {
        return Collections.unmodifiableList(borrowedBookIds);
    }

    public void addBorrowedBook(String bookId) {
        borrowedBookIds.add(bookId);
    }

    public boolean removeBorrowedBook(String bookId) {
        return borrowedBookIds.remove(bookId);
    }

    @Override
    public String toString() {
        return String.format("[%s] %-20s | Borrowed Books: %d", memberId, name, borrowedBookIds.size());
    }
}
