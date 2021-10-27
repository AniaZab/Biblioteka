public interface IWorker extends IPerson{
    boolean lendTheBook(Book book, int numberOfCopies, Reader reader);
    boolean returnTheBooks(Book book, int numberOfCopies, Reader reader);
}
