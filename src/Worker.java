import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Worker implements IWorker
{
    private LibraryOfReaders libraryOfReaders;
    private LibraryOfBooks libraryOfBooks;

    public Worker(String pathToBooks, String pathToReaders) {
        libraryOfReaders = new LibraryOfReaders(pathToReaders);
        libraryOfBooks = new LibraryOfBooks(pathToBooks);
    }
    public void printAllTheBooks(){
        for (CopiesOfTheBook copiesOfTheBook:libraryOfBooks.booksInLibrary
             ) {
            System.out.println(copiesOfTheBook);
        }
    }
    public boolean logIn(Reader reader){
        return libraryOfReaders.isThereTheReader(reader);
    }
    public Reader register(String name, String lastName){
        return libraryOfReaders.registerReader(name, lastName);
    }
    @Override
    public boolean isThereTheBook(Book book) {
        for (CopiesOfTheBook copiesOfTheBook:libraryOfBooks.booksInLibrary
             ) {
            Book newBook = new Book(copiesOfTheBook.name,copiesOfTheBook.author);
            if(book.equals(newBook) && copiesOfTheBook.numberOfCopies!=0)
                return true;
            }
        return false;
    }
    @Override
    public boolean returnTheBooks(Book book, int numberOfCopies, Reader reader) {
        return libraryOfBooks.returnTheBooks(book, numberOfCopies);

    }
    protected void addNewBook(Book book, int numberOfCopies){
        libraryOfBooks.addNewBook( book, numberOfCopies);
    }

    @Override
    public boolean lendTheBook(Book book, int numberOfCopies, Reader reader) {
        return libraryOfBooks.lendTheBook(book, numberOfCopies);
    }

    protected void CloseTheLibrary(){
        libraryOfBooks.writeBooksToFile();
        libraryOfReaders.writeReadersToFile();
        libraryOfBooks.booksInLibrary = null;
        libraryOfReaders.readersInLibrary = null;
    }

    public class LibraryOfBooks {
        private String pathToBooks;
        private ArrayList<CopiesOfTheBook> booksInLibrary = new ArrayList<>();

        public LibraryOfBooks(String pathToBooks) {
            this.pathToBooks = pathToBooks;
            readBooksFromFile();
        }
        private void readBooksFromFile() {
            try {
                Scanner scanner = new Scanner(new File(pathToBooks));
                scanner.useDelimiter(";");

                while (scanner.hasNext()) {
                    int id = Integer.parseInt(scanner.next());
                    int numberOfCopies = Integer.parseInt(scanner.next());
                    String name = scanner.next();
                    String author = scanner.next();
                    scanner.next();
                    booksInLibrary.add(new CopiesOfTheBook(name, author, id, numberOfCopies));
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                System.err.println("File does not exist");
            }
        }

        private void writeBooksToFile ()
        {
            try {
                FileWriter fileWriter = new FileWriter(pathToBooks);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                for (CopiesOfTheBook copiesOfTheBook : booksInLibrary) {
                    printWriter.println(copiesOfTheBook);
                }
                printWriter.close();
            }
            catch(IOException e){
                System.err.println(e.getMessage());
            }
        }
        private boolean returnTheBooks(Book book, int numberOfCopies) {
            if(isThereTheBook(book)){
                int index = findTheBook(book);
                CopiesOfTheBook copiesOfTheBook = booksInLibrary.get(index);
                copiesOfTheBook.addCopies(numberOfCopies);
                booksInLibrary.set(index, copiesOfTheBook);
                return true;
            }
            return false;
        }
        private boolean lendTheBook(Book book, int numberOfCopies) {
            int index = findTheBook(book);
            if (index > -1){
                CopiesOfTheBook copiesOfTheBook = booksInLibrary.get(index);
                copiesOfTheBook.deleteCopies(numberOfCopies);
                booksInLibrary.set(index, copiesOfTheBook);
                return true;
            }
            return false;
        }
        private int findTheBook(Book book){
            int index = 0;
            for (CopiesOfTheBook copiesOfTheBook:booksInLibrary
            ) {
                Book compareBook = new Book(copiesOfTheBook.name,copiesOfTheBook.author);
                if(book.equals(compareBook)){
                    return index;
                }
                index++;
            }
            return -1;
        }
        private void addNewBook(Book book, int numberOfCopies){
            CopiesOfTheBook copiesOfTheBook = new CopiesOfTheBook(book.name,
                    book.author,booksInLibrary.size(),numberOfCopies);
            booksInLibrary.add(copiesOfTheBook);
        }
    }
    public class LibraryOfReaders{
        private String pathToReaders;
        private ArrayList<Reader> readersInLibrary = new ArrayList<>();

        public LibraryOfReaders(String pathToReaders) {
            this.pathToReaders = pathToReaders;
            readReadersFromFile();
        }
        private void readReadersFromFile() {
            try {
                Scanner scanner = new Scanner(new File(pathToReaders));
                scanner.useDelimiter(";");

                while (scanner.hasNext()) {
                    int id = Integer.parseInt(scanner.next());
                    String name = scanner.next();
                    String lastName = scanner.next();
                    scanner.next();
                    readersInLibrary.add(new Reader(id,name, lastName));
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                System.err.println("File does not exist");
            }
        }
        private void writeReadersToFile()
        {
            try {
                FileWriter fileWriter = new FileWriter(pathToReaders);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                for (Reader reader : readersInLibrary) {
                    printWriter.println(reader);
                }
                printWriter.close();
            }
            catch(IOException e){
                System.err.println(e.getMessage());
            }
        }
        private int findReader(Reader reader) {
            int index = 0;
            for (Reader compareReader:readersInLibrary
            ) {
                if(reader.equals(compareReader)){
                    return index;
                }
                index++;
            }
            return -1;
        }
        private boolean isThereTheReader(Reader reader) {
            for (Reader compareReader:readersInLibrary
            ) {
                if(reader.equals(compareReader)){
                    return true;
                }
            }
            return false;
        }
        private Reader registerReader(String name, String lastName) {
            Reader reader = new Reader(readersInLibrary.size(),name,lastName);
            readersInLibrary.add(reader);
            return reader;
        }
    }
}
