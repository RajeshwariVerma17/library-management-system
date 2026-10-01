/**
 * Represents a book in the library catalog.
 * Demonstrates OOP state encapsulation.
 * 100% Zero External Dependencies / API Keys.
 */
public class Book {
    private final String id;
    private final String title;
    private final String author;
    private boolean isIssued;
    private String issuedToMemberId;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
        this.issuedToMemberId = null;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public String getIssuedToMemberId() {
        return issuedToMemberId;
    }

    public boolean issueTo(String memberId) {
        if (this.isIssued) {
            return false;
        }
        this.isIssued = true;
        this.issuedToMemberId = memberId;
        return true;
    }

    public boolean returnBook() {
        if (!this.isIssued) {
            return false;
        }
        this.isIssued = false;
        this.issuedToMemberId = null;
        return true;
    }

    @Override
    public String toString() {
        String status = isIssued ? "ISSUED (Member ID: " + issuedToMemberId + ")" : "AVAILABLE";
        return String.format("[%s] \"%-28s\" by %-18s | Status: %s", id, title, author, status);
    }
}
