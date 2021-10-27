public interface IReader extends IPerson {
    Book[] returnListOfBorrowedBooks();
    boolean reserveBook();
    boolean borrowTheBook(Book book, int numberOfCopies);
    boolean returnAFewBooks(Book book, int numberOfCopies);
}
