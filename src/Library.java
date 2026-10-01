import java.util.*;

/**
 * Core business engine managing books, members, and borrowing operations.
 * Demonstrates Collections (HashMap), Validation, and Composition.
 */
public class Library {
    private final String libraryName;
    private final Map<String, Book> books;
    private final Map<String, Member> members;
    private int nextBookId;
    private int nextMemberId;

    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.books = new HashMap<>();
        this.members = new HashMap<>();
        this.nextBookId = 101;
        this.nextMemberId = 501;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public Book addBook(String title, String author) {
        if (title == null || title.trim().isEmpty() || author == null || author.trim().isEmpty()) {
            System.out.println("❌ Book title and author cannot be empty.");
            return null;
        }

        String id = "B" + nextBookId++;
        Book book = new Book(id, title.trim(), author.trim());
        books.put(id, book);
        System.out.printf("✅ Book added successfully: [%s] \"%s\" by %s%n", id, title.trim(), author.trim());
        return book;
    }

    public Member registerMember(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("❌ Member name cannot be empty.");
            return null;
        }

        String id = "M" + nextMemberId++;
        Member member = new Member(id, name.trim());
        members.put(id, member);
        System.out.printf("✅ Member registered successfully: [%s] %s%n", id, name.trim());
        return member;
    }

    public boolean issueBook(String bookId, String memberId) {
        Book book = books.get(bookId.toUpperCase().trim());
        Member member = members.get(memberId.toUpperCase().trim());

        if (book == null) {
            System.out.printf("❌ Book with ID '%s' not found.%n", bookId);
            return false;
        }

        if (member == null) {
            System.out.printf("❌ Member with ID '%s' not registered.%n", memberId);
            return false;
        }

        if (book.isIssued()) {
            System.out.printf("❌ Sorry, book \"%s\" is currently checked out by Member %s.%n",
                    book.getTitle(), book.getIssuedToMemberId());
            return false;
        }

        book.issueTo(member.getMemberId());
        member.addBorrowedBook(book.getId());
        System.out.printf("🎉 Success! Book \"%s\" is issued to %s (%s).%n",
                book.getTitle(), member.getName(), member.getMemberId());
        return true;
    }

    public boolean returnBook(String bookId, String memberId) {
        Book book = books.get(bookId.toUpperCase().trim());
        Member member = members.get(memberId.toUpperCase().trim());

        if (book == null) {
            System.out.printf("❌ Book with ID '%s' not found.%n", bookId);
            return false;
        }

        if (member == null) {
            System.out.printf("❌ Member with ID '%s' not registered.%n", memberId);
            return false;
        }

        if (!book.isIssued() || !memberId.equalsIgnoreCase(book.getIssuedToMemberId())) {
            System.out.printf("❌ Book \"%s\" was not borrowed by member %s.%n", book.getTitle(), memberId);
            return false;
        }

        book.returnBook();
        member.removeBorrowedBook(book.getId());
        System.out.printf("✅ Book \"%s\" returned successfully by %s.%n", book.getTitle(), member.getName());
        return true;
    }

    public List<Book> searchByTitle(String query) {
        List<Book> results = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) return results;

        String q = query.trim().toLowerCase();
        for (Book b : books.values()) {
            if (b.getTitle().toLowerCase().contains(q) || b.getAuthor().toLowerCase().contains(q)) {
                results.add(b);
            }
        }
        return results;
    }

    public Collection<Book> getAllBooks() {
        return books.values();
    }

    public List<Book> getIssuedBooks() {
        List<Book> issued = new ArrayList<>();
        for (Book b : books.values()) {
            if (b.isIssued()) issued.add(b);
        }
        return issued;
    }

    public Collection<Member> getAllMembers() {
        return members.values();
    }
}
