import java.io.*;
import java.util.Scanner;

public class Main {
    public static void leadingUserToCheckIFThereIsABook(Worker worker) throws IOException {

        if(worker.isThereTheBook(bookGetDateFromUser(worker))){
            System.out.println("There is the book.");
        }
        else
            System.out.println("There is no such book.");
    }
    public static void borrowABook(Worker worker, Reader reader) throws IOException {
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("How many copies would you like to borrow of the book?");
        int numberOfCopies = Integer.parseInt(buffReader.readLine());
        if(reader.borrowTheBook(bookGetDateFromUser(worker),numberOfCopies)){
            System.out.println("Here you go, take your book and enjoy reading :)");
        }
        else
            System.out.println("We are so sorry but the book is not available");
    }
    public static Book bookGetDateFromUser(Worker worker) throws IOException {
            //worker.printAllTheBooks();
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Write name of the book.");
            String name = buffReader.readLine();
            System.out.println("Write author of the book.");
            String author = buffReader.readLine();
            return new Book(name,author);
    }
    public static void addNewBook(Worker worker) throws IOException{
        Book book = bookGetDateFromUser(worker);
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Write number of copies of the book.");
        int numberOfCopies = Integer.parseInt(buffReader.readLine());
        worker.addNewBook(book,numberOfCopies);
    }
    public static Reader logIn(Worker worker) throws IOException {
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Write your name: ");
        String name = buffReader.readLine();
        System.out.print("Write your lastname: ");
        String lastname = buffReader.readLine();
        System.out.print("Write your library id number: ");
        int id = Integer.parseInt(buffReader.readLine());
        Reader reader = new Reader(id,name,lastname,worker);
        if(worker.logIn(reader)){
            return reader;
        }
        else{
            System.out.print("You haven't been found");
            return returnReader(worker);
        }
    }
    public static Reader registerReader(Worker worker) throws IOException {
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Write your name: ");
        String name = buffReader.readLine();
        System.out.print("Write your lastname: ");
        String lastname = buffReader.readLine();
        Reader reader = worker.register(name,lastname);
        reader.worker=worker;
        return reader;
    }
    public static Reader returnReader(Worker worker) throws IOException {
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);
        System.out.println("If you want to Log in then type 'l' \nif you don't " +
                        "have account you have to register - type 'r'");
        String letter = scanner.next().toLowerCase();
        Reader reader;
        switch(letter){
            case "l":
                reader = logIn(worker);
                break;
            case "r":
                reader = registerReader(worker);
                System.out.println("You are sucessfuly registered, " +
                        "your ID is: "+ reader.ReaderId());
                break;
            default:
                System.out.println("Wrong symbol. There are only allowed this" +
                        " letters: 'l' or 'r'");
                return returnReader(worker);
        }
        return reader;
    }
    public static void returnAFewBooks(Worker worker, Reader reader) throws IOException {
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        Book book = bookGetDateFromUser(worker);
        System.out.println("How many copies do you have of this book?");
        int numberOfCopies = Integer.parseInt(buffReader.readLine());
        if(reader.returnAFewBooks(book,numberOfCopies))
            System.out.print("Thank you for returning the book");
        else
            System.out.print("The book hasn't been sucessfuly returned");
    }
    public static void ideWithWorker(Worker worker) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        while(number!=2){
            System.out.println("\nYou have a few things you can do without a reader. You can:\n" +
                    "1) Check if there is a book in library\n" +
                    "2) Close the Library.\n" +
                    "3) Print all the Books\n" +
                    "4) Add a book to the library\n" +
                    "Write the number (1/2/3/4) depending on what you want to do");
            number = scanner.nextInt();
            switch (number) {
                case 1:
                    leadingUserToCheckIFThereIsABook(worker);
                    break;
                case 2:
                    break;
                case 3:
                    worker.printAllTheBooks();
                    break;
                case 4:
                    addNewBook(worker);
                    break;
                default:
                    System.out.println("Podałeś zły napis");
            }
        }
    }
    public static void ideWithReader(Worker worker) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Reader reader = returnReader(worker);
        int number = 0;
        while(number!=5){
            System.out.println("\nYou have a few things you can do. Your options are:\n" +
                    "1) Check if there is a book in library\n" +
                    "2) Borrow a book.\n" +
                    "3) Return a book.\n" +
                    "4) Print all the Books\n" +
                    "5) Leave the Library\n" +
                    "Write the number (1/2/3/4/5) depending on what you want to do");
            number = scanner.nextInt();
            switch (number){
                case 1:
                    leadingUserToCheckIFThereIsABook(worker);
                    break;
                case 2:
                    borrowABook(worker, reader);
                    break;
                case 3:
                    returnAFewBooks(worker, reader);
                    break;
                case 4:
                    worker.printAllTheBooks();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Podałeś zły napis");
            }
        }
    }

    public static void main(String [] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        Worker worker = new Worker("src/books.txt","src/readers.txt");

        System.out.println("Are you worker or reader?\nWrite first letter w-worker or r-reader depending on who you are.");
        String letter = scanner.next().toLowerCase();
        switch (letter){
            case "w":
                ideWithWorker(worker);
                break;
            case "r":
                ideWithReader(worker);
                break;
            default:
                System.out.println("Podałeś zły napis");
        }
        worker.CloseTheLibrary();













        /*
        ;0;1;Zwiadowcy;John Flanangan;

        Scanner sc = new Scanner(System.in);
        String nameSurname = sc.nextLine();
        int age = sc.nextInt();
        System.out.println("Your name is: " + nameSurname+  " age: " + age);
        while (sc.hasNextInt()) // ta metoda sprawdza czy sc.nextInt() pobral inta
        {
            int nmbr = sc.nextInt();
            System.out.println("Your name is: " + nameSurname+  " age: " + nmbr);
        }

        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        int i = Integer.parseInt(buffReader.readLine());
        System.out.println("Your name is: " + nameSurname+  " age: " + i);

        Console con = System.console();
        String progLanguauge = con.readLine("Enter your favourite programming language: ");
        char[] pass = con.readPassword("To finish, enter password: ");
        con.printf(progLanguauge + " is very interesting!");
         */
    }

}
