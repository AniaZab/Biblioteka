import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    private String pathToBooks;
    private String pathToReaders;
    protected ArrayList<CopiesOfTheBook> booksInLibrary = new ArrayList<>();


    public Library(String pathToBooks, String pathToReaders) {
        this.pathToBooks = pathToBooks;
        this.pathToReaders = pathToReaders;
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
    protected void writeBooksToFile()
    {
        try {
            FileWriter fileWriter = new FileWriter(pathToBooks);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (CopiesOfTheBook copiesOfTheBook : booksInLibrary) {
                printWriter.print(copiesOfTheBook);
            }
            printWriter.close();
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

}
