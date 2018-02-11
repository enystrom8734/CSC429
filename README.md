# CSC 429: Object-Oriented Software Development
## Pair Programming 1: Database access from Java
### Department of Computing Sciences
### The College at Brockport, Brockport, NY 

Due Date: 02/06/2018

You have a database with the following tables:
```
TABLE: Book	
Fields:
bookId      INT         /* Primary key, auto-increment */
author      VARCHAR(30) /* LENGTHS OF THESE FIELDS MAY BE
title       VARCHAR(50)    CHANGED, DEPENDING ON ACTUAL 
pubYear     CHAR(4)        DATA VALUES USED */
status      VARCHAR(10)
```

```
TABLE : Patron
Fields:
patronId    INT             /* Primary key, auto-increment  */
name        VARCHAR(30)	
address     VARCHAR(50)	     
city        VARCHAR(20)	     
stateCode   CHAR(2)
zip         CHAR(5)
email       VARCHAR(30)
dateOfBirth CHAR(12)        /* Should be entered in form yyyy-MM-dd – i.e. 2017-01-27 */
status      VARCHAR(10)
```

```
TABLE: Transaction
Fields:
transId     INT         /* Primary key, auto-increment */
bookId      INT
patronId    INT
transType   VARCHAR(10) /* ONLY 2 Possible values: “Rent” and “Return” */
dateOfTrans CHAR(12)    /* Should be entered in form 2017-01-27 */
```
You first need to set up these database tables on the ‘csdb.brockport.edu’ server. In order to do this, log in to the server (http://csdb.brockport.edu/phpmyadmin) using your NetID and password (which you should have changed). Thereafter, in the individual database available to you (what you see when you log in), create these tables (making sure to set their types, and each table’s primary key properly). After that, you may consider populating these tables with some suitable data.

Next, you need to write the following classes that inherit from the “EntityBase” class as provided in the case study/example:

1.	Book
2.	Patron
3.	BookCollection (* This should hold a java.util.Vector of ‘Book’ objects *)
4.	PatronCollection (* Similarly, this should hold a Vector of ‘Patron’ objects *)

Note that in the classes Book and Patron, you should have constructors that allow the creation of a new object (with new user-supplied data – i.e., a constructor that takes in a ‘Properties’ object as parameter), as well as a constructor that takes the primary key value of this object and instantiates the object from the appropriate database table. In other words, write the code for these classes on the lines of the code for the ‘Account’ class discussed in class. Also, these classes should have an update method that either inserts a new object into the database or updates an existing object in the database, depending on whether the auto-generated primary key is present or not.

For the BookCollection class, you should have a constructor that does nothing, but set up a ‘blank’ Vector (call it ‘bookList’ – this should hold the collection of Book objects encapsulated by this class). In other words, begin by noting the code for the ‘AccountCollection’ class we discussed. This class has a constructor that takes in an ‘AccountHolder’ object and creates and holds a list of ‘Account’ objects owned by this ‘AccountHolder’. But for this ‘BookCollection’ class, the constructor should be parameter-less, and do something like this:
```java
public class BookCollection{ 
    // some code
    Vector bookList = new Vector(); // new Vector<Book>();
}
```
Recall that the constructor of the ‘AccountCollection’ class created and executed a query that retrieved a number of rows from the ‘Account’ table and processed them. That kind of code should now not be in the constructor, but be in each of the methods mentioned below, with different queries used by each method, of course.

This class should have the following other methods:

1.	findBooksOlderThanDate(String year)
2.	findBooksNewerThanDate(String year)
3.	findBooksWithTitleLike(String title)
4.	findBooksWithAuthorLike(String author)

The first two methods are self-explanatory. The other two methods are to find books that match using the “like” technique – for example, if I provide the parameter string “Harry” it should match “Dirty Harry”, “Harry Potter and the ….”, “When Harry Met Sally”, etc. Each of these methods should execute by going to the database and getting the matching Book objects. It should then populate the encapsulated ‘Vector’ to contain these objects – i.e., it should populate ‘bookList’.

For the PatronCollection class, have a constructor similar to BookCollection. In addition, have the following methods:

1.	findPatronsOlderThan(String date)
2.	findPatronsYoungerThan(String date)
3.	findPatronsAtZipCode(String zip)
4.	findPatronsWithNameLike(String name)

The methods should be coded similar to the way you coded the methods for BookCollection.

You should test your program with the requests outlined below (The instructor will ask you to demo these). You should write a separate main program to execute these tests (the input for these tests may be read from the command line):

1.	Insert a new book into the database. Read all the relevant data needed to populate a book from the command line and ensure that the new book is inserted into the database at the point the request is complete.
2.	Insert a new patron into the database. Read all the relevant data needed to populate a patron from the command line and ensure that the new patron is inserted into the database at the point the request is complete.
3.	Given a part of a title of a book, print all book data for books that match this title.
4.	Given a year, print all book data for books that are published before that year.
5.	Given a date, print all patron data for patrons that are younger than that date.
6.	Given a zip, print all patron data for patrons that live at that zip.

For output, showing the results on standard output, using “System.out.println(…)” is fine.

Project structure: You are advised to re-create the ‘project directory structure’ exactly as done for the ‘ATM’ case study. That is, under your main working directory (lets call it ‘429Assgn1’), have sub-directories entitled ‘classes’, ‘common’, ‘database’, ‘exception’, ‘impresario’ and ‘event’. Copy the code in ‘common’, ‘database’, ‘exception’, ‘impresario’ and ‘event’ sub-directories of the ‘ATM’ directory into the corresponding sub-directories under ‘429Assgn1’. Be sure to copy the Connector/J JAR file over to ‘429Assgn1’. Create a ‘model’ sub-directory under ‘429Assgn1’and be sure to put all the above classes in the ‘model’ sub-directory (ensure that they are in package ‘model’). Write your main program that does the testing at the ‘429Assgn1’ directory level (not in any package).

In order to compile the Java files in any directory into the ‘classes’ directory, use the following command at the command line:

C:…429Assgn1> javac –d classes –classpath classes;. model\*.java
Compile the main program (let me call it ‘TestAssgn1.java’ – note that this is not in any package, and therefore it is in the ‘default’ package) using this command:

C:…429Assgn1> javac  –classpath classes;. TestAssgn1.java

In order to run the program, use the following command (which you can put into a .bat file):

C:…429Assgn1> java -cp mysql-connector-java-5.1.7-bin.jar;classes;. TestAssgn1

NOTE THAT THE ABOVE PROJECT SETUP STRUCTURE IS ESSENTIAL IF YOU ARE USING THE COMMAND LINE. IF YOU USE YOUR OWN FAVORITE IDE LIKE Eclipse, etc. YOU SHOULD SETUP YOUR PROJECT AS REQUIRED BY THE IDE 

A HINT: 
1.	As shown in the example, you might want to use the Persistable class method: getSelectQueryResult(String sqlSelectStatement) extensively for this. This will need you to construct SQL queries well. If you don’t construct the queries correctly, you will have problems. I am not giving the queries to you right away because writing them is an important skill to develop.

I will provide you with a sample query that gets all books that use the ‘like’ strategy and match part of the author name:

SELECT * FROM Book WHERE author LIKE ‘%Rowl%’;

In a Java program, you will need to set this query up as a String object. Also, possibly, (part of) the name of the author (i.e., a value like “Rowl”) will come in via a parameter, or some other mechanism – in a Java String object. Let us call this String object ‘authorNamePart’.

So, to set up this query, we use code like this:

String query = “SELECT * FROM Book WHERE author LIKE ‘%” + 
	authorNamePart +  “%’;”;

Alternatively, as shown in the ‘Account’ class, if the table name ‘Book’ is held in a String variable called ‘myTableName’, your query code will look like this:

String query = “SELECT * FROM ” + myTableName + “ WHERE author LIKE ‘%” + 	authorNamePart +  “%’;”;

NOTE: Within the string literals used in the construction of the query, the blanks are important. For example, there must be a blank before the word WHERE (figure out why).

	This String object, ‘query’, is then passed to the appropriate method.
