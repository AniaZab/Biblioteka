import com.sun.management.UnixOperatingSystemMXBean;

import java.util.Objects;

public class Reader implements IReader{
    private int readerId;
    private String name;
    private String lastName;
    public Worker worker;

    public Reader(int readerId, String name, String lastName,Worker worker) {
        this.readerId = readerId;
        this.name = name;
        this.lastName = lastName;
        this.worker = worker;
    }
    public Reader(int readerId, String name, String lastName) {
        this.readerId = readerId;
        this.name = name;
        this.lastName = lastName;
    }
    public int ReaderId()
    {
        return readerId;
    }
    @Override
    public boolean isThereTheBook(Book book) {

        return worker.isThereTheBook(book);
    }

    @Override
    public boolean returnAFewBooks(Book book, int numberOfCopies) {
        if(numberOfCopies == 0)
            return false;
        return worker.returnTheBooks(book,numberOfCopies, this);
    }

    @Override
    public Book[] returnListOfBorrowedBooks() {

        return new Book[0];
    }

    @Override
    public boolean reserveBook() {
        return false;
    }

    @Override
    public boolean borrowTheBook(Book book, int numberOfCopies){
        return worker.lendTheBook(book, numberOfCopies, this);
    }

    @Override
    public String toString() {
        return ";" + readerId +
                ";" + name  +
                ";" + lastName +
                ';';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return readerId == reader.readerId && Objects.equals(name, reader.name) && Objects.equals(lastName, reader.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(readerId, name, lastName);
    }
}
