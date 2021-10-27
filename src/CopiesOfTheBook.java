import java.util.Objects;

public class CopiesOfTheBook extends Book{
    int bookId;
    int numberOfCopies;

    public CopiesOfTheBook(String name, String author, int id, int numberOfCopies) {
        super(name, author);
        this.bookId = id;
        this.numberOfCopies = numberOfCopies;
    }

    void addCopies(int numberOfNewCopies){
        numberOfCopies+=numberOfNewCopies;
    }
    void deleteCopies(int numberOfCopiesToDelete){
        if (numberOfCopiesToDelete > numberOfCopies){
            numberOfCopies = 0;
        }
        else{
            numberOfCopies-=numberOfCopiesToDelete;
        }
    }
    boolean isItTaken(){
        return numberOfCopies==0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CopiesOfTheBook that = (CopiesOfTheBook) o;
        return bookId == that.bookId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bookId);
    }

    @Override
    public String toString() {
        return  ";" + bookId +
                ";" + numberOfCopies +
                ";" + name  +
                ";" + author+
                ";";
    }
}
