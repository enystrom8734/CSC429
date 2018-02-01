import java.util.Scanner;
import java.util.Properties;
import java.util.Vector;

import model.Book;
import model.BookCollection;
//import model.PatronCollection;
//import model.Patron;

public class TestAssign1 {

    private static Scanner scan = new Scanner(System.in).useDelimiter("\n");

    public static void main(String args[]){
        initialPrompt();
        mainMenu();
    }

    private static void initialPrompt(){
        System.out.println("+==========================================+");
        System.out.println("| CSC 429 - Assigment 1 - Pair Programming |");
        System.out.println("| Authors: Cholla Grygotis & Erik Nystrom  |");
        System.out.println("+==========================================+");
        System.out.println("+==========================================+");
    }
    private static void mainMenu(){
        System.out.println("+==========================================+");
        System.out.println("| Enter option to select. Then press ENTER |");
        System.out.println("| 1. Create a new Patron or Book           |");
        System.out.println("| 2. Pull up an existing entry             |");
        System.out.println("| 3. Run Test Script                       |");
        System.out.println("| 0. Exit Program                          |");
        System.out.println("+==========================================+");
        System.out.print("Option: ");
        int i = scan.nextInt();
        switch(i) {
            case 1:
                createMenu();
                break;
            case 2:
                findMenu();
                break;
            case 3:
                runTestScript();
                break;
            case 0:
                System.exit(0);
            default:
                mainMenu();
                break;
        }
    }
    private static void createMenu(){
        System.out.println("+==========================================+");
        System.out.println("| Enter option to select. Then press ENTER |");
        System.out.println("| 1. Create a new Book                     |");
        System.out.println("| 2. Create a new Patron                   |");
        System.out.println("| 5. Return to previous menu               |");
        System.out.println("+==========================================+");
        System.out.print("Option: ");
        int i = scan.nextInt();
        switch(i){
            case 1:
                createBook();
                break;
            case 2:
//                createPatronPrompt();
                break;
            default:
                mainMenu();
                break;
        }
    }
    private static void findMenu(){
        System.out.println("+==========================================+");
        System.out.println("| Enter option to select. Then press ENTER |");
        System.out.println("| 1. Find a Book                           |");
        System.out.println("| 2. Find a new Patron                     |");
        System.out.println("| 5. Return to previous menu               |");
        System.out.println("+==========================================+");
        System.out.print("Option: ");
        int i = scan.nextInt();
        switch(i){
            case 1:
                findBook();
                break;
            case 2:
                findPatron();
                break;
            case 5:
                mainMenu();
                break;
        }
    }
    private static void findBook(){
        System.out.println("+==========================================+");
        System.out.println("| Enter option to select. Then press ENTER |");
        System.out.println("| 1. Find all Books older than a date      |");
        System.out.println("| 2. Find all Books newer than a date      |");
        System.out.println("| 3. Find all Books with a title like      |");
        System.out.println("| 4. Find all Books with a author like     |");
        System.out.println("| 5. Return to previous menu               |");
        System.out.println("+==========================================+");
        System.out.print("Option: ");
        int i = scan.nextInt();
        switch(i){
            case 1:
                findBooksOlderThan();
                break;
            case 2:
                findBooksNewerThan();
                break;
            case 3:
                findBooksWithTitleLike();
                break;
            case 4:
                findBooksWithAuthorLike();
                break;
            case 5:
                findMenu();
                break;
        }
    }
    private static void findPatron(){
        System.out.println("+==========================================+");
        System.out.println("| Enter option to select. Then press ENTER |");
        System.out.println("| 1. Find all Patrons older than a date    |");
        System.out.println("| 2. Find all Patrons younger than a date  |");
        System.out.println("| 3. Find all Patrons in a ZIP Code        |");
        System.out.println("| 4. Find all Patrons with names like      |");
        System.out.println("| 5. Return to previous menu               |");
        System.out.println("+==========================================+");
        System.out.print("Option: ");
        int i = scan.nextInt();
        switch(i){
            case 1:
                findPatronOlderThan();
                break;
            case 2:
                findPatronNewerThan();
                break;
            case 3:
                findPatronInZip();
                break;
            case 4:
                findPatronNameLike();
                break;
            case 5:
                findMenu();
                break;
        }
    }

    private static void continueMenu() {
        System.out.println("\n+==========================================+");
        System.out.println("| Return to main menu?                     |");
        System.out.println("| Any key to continue, N/n to exit         |");
        System.out.println("+==========================================+");
        String s = scan.next();
        if (s.equals("N") || s.equals("n")) {
            System.exit(0);
        }
        System.out.println();
        mainMenu();
    }

    // Find book methods
    private static void findBooksOlderThan(){
        String input;
        System.out.print("Date to search before: ");
        input = scan.next();
        System.out.println("Results: ");
        BookCollection bookCol = new BookCollection();
        Vector<Book> books = bookCol.findBooksOlderThanDate(input);
        for (Book book : books) {
            System.out.println(book.getEntryListView());
        }
        continueMenu();
    }
    private static void findBooksNewerThan(){
        String input;
        System.out.print("Date to search after: ");
        input = scan.next();
        System.out.println("Results: ");
        BookCollection bookCol = new BookCollection();
        Vector<Book> books = bookCol.findBooksNewerThanDate(input);
        for (Book book : books) {
            System.out.println(book.getEntryListView());
        }
        continueMenu();
    }
    private static void findBooksWithTitleLike(){
        String input;
        System.out.print("Book Title: ");
        input = scan.next();
        System.out.println("Results: ");
        BookCollection bookCol = new BookCollection();
        Vector<Book> books = bookCol.findBooksWithTitleLike(input);
        for (Book book : books) {
            System.out.println(book.getEntryListView());
        }
        continueMenu();
    }
    private static void findBooksWithAuthorLike(){
        String input;
        System.out.print("Author's Name: ");
        input = scan.next();
        System.out.println("Results: ");
        BookCollection bookCol = new BookCollection();
        Vector<Book> books = bookCol.findBooksWithAuthorLike(input);
        for (Book book : books) {
            System.out.println(book.getEntryListView());
        }
        continueMenu();
    }

    // Find patron methods
    private static void findPatronOlderThan(){
        String input;
        System.out.print("Date to search before: ");
        input = scan.next();
        System.out.println("Results: ");
        continueMenu();

    }
    private static void findPatronNewerThan(){
        String input;
        System.out.print("Date to search after: ");
        input = scan.next();
        System.out.println("Results: ");
        continueMenu();

    }
    private static void findPatronInZip(){
        String input;
        System.out.print("ZIP Code: ");
        input = scan.next();
        System.out.println("Results: ");
        continueMenu();

    }
    private static void findPatronNameLike(){
        String input;
        System.out.print("Patron name: ");
        input = scan.next();
        System.out.println("Results: ");
        continueMenu();

    }

    private static void createBook(){
        System.out.println();
        String input = "";
        Properties props = new Properties();
        System.out.print("Book Author: ");
        input = scan.next();
        props.setProperty("author", input);
        System.out.print("Book Title: ");
        input = scan.next();
        props.setProperty("title", input);
        System.out.print("Publication year (yyyy): ");
        input = scan.next();
        props.setProperty("pubYear", input);
        System.out.print("Status (active/inactive): ");
        input = scan.next();
        props.setProperty("status", input);
        Book newBook = new Book(props);
        newBook.update();
        continueMenu();

    }

    //    private static static void createPatronPrompt(){
//        String s = "";
//        Properties p = new Properties();
//        System.out.println("Name of Patron: ");
//        s = scan.next();
//        p.setProperty("name", s);
//        System.out.println("Address: ");
//        s = scan.next();
//        p.setProperty("address", s);
//        System.out.println("City: ");
//        s = scan.next();
//        p.setProperty("city", s);
//        System.out.println("State code: ");
//        s = scan.next();
//        p.setProperty("stateCode", s);
//        System.out.println("Zip code: ");
//        s = scan.next();
//        p.setProperty("zip", s);
//        System.out.println("Email Address: ");
//        s = scan.next();
//        p.setProperty("email", s);
//        System.out.println("Date of Birth(yyyy-mm-dd): ");
//        s = scan.next();
//        p.setProperty("dateOfBirth", s);
//        p.setProperty("status", "new");
//        Patron pat = new Patron(p);
//        pat.update();
//    continueMenu();

//    }

    // Test script to run the various commands without manually typing all options
    private static void runTestScript(){

        System.out.println("Test Script initiate");
        Properties p = new Properties();
        //p.setProperty("bookID", "1011");
        p.setProperty("title","manualinsert");
        p.setProperty("author","fakename");
        p.setProperty("pubYear","1976");
        p.setProperty("status","new");
        Book b = new Book(p);
        b.update();
        System.out.println("End Test script");
        continueMenu();
//        getPer();

        // search for a book by a title like
        // bc = new BookCollection("Title LIKE ", "apple");

        // given a year, print all book data for books before that year
        //   bc = new BookCollection("Date >= ", "2000-01-01");

      /*
      // given a date, print all patron data for patrons before that date
      pc = new PatronCollection("Date > ", "1990-01-01");

      // given a zip, print all patron data in that area
      pc = new PatronCollection("Zip = ", "14534");

      // given a bookid and dateOfTrans, show all transactions that relate
      tc = new BookCollection("BookID = ", "1001') AND dateOfTrans >= ('2010-01-01");      //SELECT * FROM Transactions WHERE BookId =('1001') AND dateOfTrans >= ('2010-01-01');
      */




    }
}
