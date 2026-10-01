import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * Interactive Console CLI for Library Management System.
 * 100% Zero External Dependencies / API Keys.
 * Author: Rajeshwari Verma (https://github.com/RajeshwariVerma17)
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library("City Central Library");

        // Seed with initial books and members
        library.addBook("Clean Code", "Robert C. Martin");
        library.addBook("Effective Java", "Joshua Bloch");
        library.addBook("Introduction to Algorithms", "Thomas H. Cormen");
        library.addBook("The Pragmatic Programmer", "David Thomas & Andrew Hunt");

        Member m1 = library.registerMember("Rajeshwari Verma");
        Member m2 = library.registerMember("Sarah Jenkins");

        // Demo borrowing
        library.issueBook("B101", m1.getMemberId());

        System.out.println("\n=======================================================");
        System.out.printf("       WELCOME TO %s%n", library.getLibraryName().toUpperCase());
        System.out.println("  Core Java OOP Project &bull; Zero API Keys Required");
        System.out.println("=======================================================");

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("👉 Select an option (1-8): ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    handleListBooks(library.getAllBooks(), "ALL BOOKS IN CATALOG");
                    break;
                case "2":
                    handleSearch(scanner, library);
                    break;
                case "3":
                    handleIssueBook(scanner, library);
                    break;
                case "4":
                    handleReturnBook(scanner, library);
                    break;
                case "5":
                    handleAddNewBook(scanner, library);
                    break;
                case "6":
                    handleRegisterMember(scanner, library);
                    break;
                case "7":
                    handleListBooks(library.getIssuedBooks(), "CURRENTLY ISSUED BOOKS");
                    break;
                case "8":
                    System.out.println("\n👋 Thank you for using the Library Management System. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("⚠️ Invalid option. Please select a number between 1 and 8.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n-------------- LIBRARY MENU --------------");
        System.out.println(" 1. View All Books");
        System.out.println(" 2. Search Books by Title or Author");
        System.out.println(" 3. Issue (Borrow) a Book");
        System.out.println(" 4. Return a Book");
        System.out.println(" 5. Add New Book to Catalog");
        System.out.println(" 6. Register New Member");
        System.out.println(" 7. View Currently Issued Books");
        System.out.println(" 8. Exit");
        System.out.println("------------------------------------------");
    }

    private static void handleListBooks(Collection<Book> books, String header) {
        System.out.println("\n==========================================================================================");
        System.out.printf("                               %s%n", header);
        System.out.println("==========================================================================================");
        if (books.isEmpty()) {
            System.out.println("  (No books to display)");
        } else {
            for (Book b : books) {
                System.out.println("  " + b);
            }
        }
        System.out.println("==========================================================================================\n");
    }

    private static void handleSearch(Scanner scanner, Library library) {
        System.out.print("\nEnter keyword to search (Title or Author): ");
        String query = scanner.nextLine().trim();
        List<Book> matches = library.searchByTitle(query);

        System.out.printf("\nSearch Results for \"%s\" (%d found):%n", query, matches.size());
        for (Book b : matches) {
            System.out.println("  " + b);
        }
    }

    private static void handleIssueBook(Scanner scanner, Library library) {
        System.out.print("\nEnter Book ID (e.g., B101, B102): ");
        String bookId = scanner.nextLine().trim();

        System.out.print("Enter Member ID (e.g., M501, M502): ");
        String memberId = scanner.nextLine().trim();

        library.issueBook(bookId, memberId);
    }

    private static void handleReturnBook(Scanner scanner, Library library) {
        System.out.print("\nEnter Book ID to return (e.g., B101): ");
        String bookId = scanner.nextLine().trim();

        System.out.print("Enter Member ID returning the book (e.g., M501): ");
        String memberId = scanner.nextLine().trim();

        library.returnBook(bookId, memberId);
    }

    private static void handleAddNewBook(Scanner scanner, Library library) {
        System.out.print("\nEnter Book Title: ");
        String title = scanner.nextLine().trim();

        System.out.print("Enter Author Name: ");
        String author = scanner.nextLine().trim();

        library.addBook(title, author);
    }

    private static void handleRegisterMember(Scanner scanner, Library library) {
        System.out.print("\nEnter Member Full Name: ");
        String name = scanner.nextLine().trim();

        library.registerMember(name);
    }
}
