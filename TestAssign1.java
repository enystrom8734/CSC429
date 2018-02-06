import java.util.Scanner;
import java.util.Properties;
import java.util.Vector;

import model.Book;
import model.BookCollection;
import model.PatronCollection;
import model.Patron;

public class TestAssign1 {
    private static Scanner scan = new Scanner(System.in).useDelimiter("\n");

    public static void main(String args[]){
        initialPrompt();
        mainMenu();
    }

    private static void initialPrompt(){
        System.out.println("+==========================================+");
        System.out.println("| CSC 429 - Assignment 1 - Pair Programming |");
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
                createPatron();
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
                findPatronsOlderThan();
                break;
            case 2:
                findPatronsNewerThan();
                break;
            case 3:
                findPatronsInZip();
                break;
            case 4:
                findPatronsNameLike();
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
    private static void findPatronsOlderThan(){
        String input;
        System.out.print("Date to search before: ");
        input = scan.next();
        System.out.println("Results: ");
        continueMenu();

    }
    private static void findPatronsNewerThan(){
        String input;
        System.out.print("Date to search after: ");
        input = scan.next();
        System.out.println("Results: ");
        continueMenu();

    }
    private static void findPatronsInZip(){
        String input;
        System.out.print("ZIP Code: ");
        input = scan.next();
        System.out.println("Results: ");
        continueMenu();

    }
    private static void findPatronsNameLike(){
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

    private static void createPatron(){
        String input = "";
        Properties props = new Properties();
        System.out.println("Name of Patron: ");
        input = scan.next();
        props.setProperty("name", input);
        System.out.println("Address: ");
        input = scan.next();
        props.setProperty("address", input);
        System.out.println("City: ");
        input = scan.next();
        props.setProperty("city", input);
        System.out.println("State code: ");
        input = scan.next();
        props.setProperty("stateCode", input);
        System.out.println("Zip code: ");
        input = scan.next();
        props.setProperty("zip", input);
        System.out.println("Email Address: ");
        input = scan.next();
        props.setProperty("email", input);
        System.out.println("Date of Birth(yyyy-mm-dd): ");
        input = scan.next();
        props.setProperty("dateOfBirth", input);
        System.out.print("Status (active/inactive): ");
        input = scan.next();
        props.setProperty("status", input);
        Patron newPatron = new Patron(props);
        newPatron.update();
        continueMenu();

    }

    // Test script to run the various commands without manually typing all options
    private static void runTestScript() {

        System.out.println("Test Script initiate");
        Properties p = new Properties();
        p.setProperty("title", "Fake Book Title");
        p.setProperty("author", "Fake Author Name");
        p.setProperty("pubYear", "2018");
        p.setProperty("status", "active");
        Book b = new Book(p);
        b.update();
        System.out.println("Test book added, search for book");
        System.out.println("End Test script");
        continueMenu();
    }
    // Create book and patron method for testing
    private static void createBook(int count) {
        Properties props = new Properties();
        System.out.print("Book Author: ");
        props.setProperty("author", "Name " + count);
        System.out.print("Book Title: ");
        props.setProperty("title", "Title " + count);
        System.out.print("Publication year (yyyy): ");
        int year = 1950 + count;
        props.setProperty("pubYear", String.valueOf(year));
        System.out.print("Status (active/inactive): ");
        props.setProperty("status", "active");
        Book newBook = new Book(props);
        newBook.update();
    }

    private static void createPatron(int count) {
        Properties props = new Properties();
        System.out.println("Name of Patron: ");
        props.setProperty("name", "FName LName" + count);
        System.out.println("Address: ");
        int address = 1 + count;
        props.setProperty("address", String.valueOf(address) + " Evergreen Terrace");
        System.out.println("City: ");
        props.setProperty("city", "City " + count);
        System.out.println("State code: ");
        props.setProperty("stateCode", "NY");
        System.out.println("Zip code: ");
        int zip = 14400 + count;
        props.setProperty("zip", String.valueOf(zip));
        System.out.println("Email Address: ");
        props.setProperty("email", "email" + count + "@gmail.com");
        System.out.println("Date of Birth(yyyy-mm-dd): ");
        int year = 1950 + count;
        props.setProperty("dateOfBirth", String.valueOf(year)+"-01-01");
        props.setProperty("status", "active");
        Patron newPatron = new Patron(props);
        newPatron.update();
    }

    // Find book methods using input from method
    private static void findBooksOlderThan(String input){
        System.out.print("Date to search before: " + input);
        System.out.println("Results: ");
        BookCollection bookCol = new BookCollection();
        Vector<Book> books = bookCol.findBooksOlderThanDate(input);
        for (Book book : books) {
            System.out.println(book.getEntryListView());
        }
    }
    private static void findBooksNewerThan(String input){
        System.out.print("Date to search after: " + input);
        System.out.println("Results: ");
        BookCollection bookCol = new BookCollection();
        Vector<Book> books = bookCol.findBooksNewerThanDate(input);
        for (Book book : books) {
            System.out.println(book.getEntryListView());
        }
    }
    private static void findBooksWithTitleLike(String input){
        System.out.print("Book Title: " + input);
        System.out.println("Results: ");
        BookCollection bookCol = new BookCollection();
        Vector<Book> books = bookCol.findBooksWithTitleLike(input);
        for (Book book : books) {
            System.out.println(book.getEntryListView());
        }
    }
    private static void findBooksWithAuthorLike(String input){
        System.out.print("Author's Name: " + input);
        System.out.println("Results: ");
        BookCollection bookCol = new BookCollection();
        Vector<Book> books = bookCol.findBooksWithAuthorLike(input);
        for (Book book : books) {
            System.out.println(book.getEntryListView());
        }
    }

    // Find patron methods using input from method
    private static void findPatronsOlderThan(String input){
        System.out.print("Date to search before: " + input);
        System.out.println("Results: ");
        PatronCollection patronCol = new PatronCollection();
        Vector<Patron> patrons = patronCol.findPatronsOlderThan(input);
        for (Patron patron : patrons) {
            System.out.println(patron.getEntryListView());
        }
    }
    private static void findPatronsYoungerThan(String input){
        System.out.print("Date to search after: " + input);
        System.out.println("Results: ");
        PatronCollection patronCol = new PatronCollection();
        Vector<Patron> patrons = patronCol.findPatronsYoungerThan(input);
        for (Patron patron : patrons) {
            System.out.println(patron.getEntryListView());
        }
    }
    private static void findPatronsInZip(String input){
        System.out.print("ZIP Code: " + input);
        System.out.println("Results: ");
        PatronCollection patronCol = new PatronCollection();
        Vector<Patron> patrons = patronCol.findPatronsAtZipCode(input);
        for (Patron patron : patrons) {
            System.out.println(patron.getEntryListView());
        }
    }
    private static void findPatronsNameLike(String input){
        System.out.print("Patron name: " + input);
        System.out.println("Results: ");
        PatronCollection patronCol = new PatronCollection();
        Vector<Patron> patrons = patronCol.findPatronsWithNameLike(input);
        for (Patron patron : patrons) {
            System.out.println(patron.getEntryListView());
        }
    }
}
