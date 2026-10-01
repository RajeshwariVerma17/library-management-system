# Library Management System (Core Java OOP)

A modular, console-based library catalog and circulation system written in pure Java (JDK 21 LTS). It models real-world library operations including book registration, member management, borrowing/returning workflows, and title/author searches.

## Features

- **100% Zero External Dependencies / API Keys**: Built with standard Java SE. No external APIs, database drivers, or secrets required. 100% safe to publish publicly on GitHub.
- **Book Circulation Workflow**: Issue and return books with state checks to prevent double-borrowing.
- **Fast Lookups**: Books and members are stored in `HashMap` collections providing $O(1)$ constant time lookup by ID.
- **Search Engine**: Case-insensitive keyword search matching both book title and author names.
- **Member Borrowing Ledger**: Tracks all currently checked-out books per member using `ArrayList`.

---

## Object-Oriented Programming (OOP) Principles Demonstrated

1. **Encapsulation & State Invariants**:
   - `Book` fields (`id`, `title`, `author`, `isIssued`, `issuedToMemberId`) are encapsulated.
   - Borrowing state transitions (`issueTo()`, `returnBook()`) enforce validity checks internally.
2. **Composition & Association**:
   - `Library` aggregates collections of `Book` and `Member` objects.
   - `Member` tracks a list of active book IDs, demonstrating relational modeling without a SQL database.
3. **Defensive Programming**:
   - Unmodifiable views of lists (`Collections.unmodifiableList()`) are exposed to external callers to prevent illegal external mutations.

---

## How to Explain This Project in an Interview / Viva (30 Seconds)

> *"This project is an Object-Oriented Library Management System implemented in Core Java:*
> 1. ***Data Modeling & Encapsulation:*** *I modeled `Book`, `Member`, and `Library` entities with strict encapsulation so a book's circulation status can only change via validated business methods.*
> 2. ***Collections & Time Complexity:*** *I used `HashMap` for book and member storage to guarantee $O(1)$ lookups by ID, and `ArrayList` for managing borrowed book collections.*
> 3. ***Clean Separation of Concerns:*** *The `Main` class handles user interface I/O, while `Library` encapsulates the core business validation logic, keeping the system modular and testable."*

---

## How to Compile & Run

### Prerequisites
- Java Development Kit (JDK 17 or 21+)

### Steps
1. Clone this repository:
   ```bash
   git clone https://github.com/RajeshwariVerma17/library-management-system.git
   cd library-management-system
   ```
2. Compile the source code into the `bin` directory:
   ```bash
   javac -d bin src/*.java
   ```
3. Run the application:
   ```bash
   java -cp bin Main
   ```

---

## Author

- **Rajeshwari Verma**: [github.com/RajeshwariVerma17](https://github.com/RajeshwariVerma17)

## License

MIT License. Free to use and modify.
