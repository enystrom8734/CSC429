// specify the package
package model;

// system imports
import java.util.Properties;
import java.util.Vector;

// project imports

import impresario.IView;

/** The class containing the BookCollection for the Assignment 1 application */
//==============================================================
public class BookCollection extends EntityBase implements IView
{
	private static final String myTableName = "Book";
    private static final String queryTemplate = "SELECT * FROM " + myTableName + " WHERE ";

	private Vector<Book> bookList;

	// constructor for this class
	//----------------------------------------------------------
	public BookCollection() // throws Exception
	{
		super(myTableName);
		bookList = new Vector<>();
	}
	public Vector findBooksOlderThanDate(String year) {
        String query = String.format("%s (pubYear < %s) ORDER BY author ASC",
                queryTemplate, year);
		return doQuery(query);
	}
	public Vector findBooksNewerThanDate(String year) {
        String query = String.format("%s (pubYear > %s) ORDER BY author ASC",
                queryTemplate, year);
		return doQuery(query);
	}

	public Vector findBooksWithTitleLike(String title) {
        String query = String.format("%s title LIKE '%%%s%%' ORDER BY author ASC",
                queryTemplate, title);
		return doQuery(query);
	}

	public Vector findBooksWithAuthorLike(String author) {
        String query = String.format("%s author LIKE '%%%s%%' ORDER BY author ASC",
                queryTemplate, author);
		return doQuery(query);
	}

    private Vector doQuery(String query) {
        try {
            Vector allDataRetrieved = getSelectQueryResult(query);
            if (allDataRetrieved != null) {
//                bookList = new Vector<Book>();
                for (int index = 0; index < allDataRetrieved.size(); index++) {
                    Properties data = (Properties) allDataRetrieved.elementAt(index);
                    Book book = new Book(data);
                    bookList.add(book);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return bookList;
    }

	//----------------------------------------------------------
	public Object getState(String key)
	{
		if (key.equals("books"))
			return bookList;
		else
		    if (key.equals("bookList"))
		        return this;
		return null;
	}

	//----------------------------------------------------------------
	public void stateChangeRequest(String key, Object value)
	{
		
		myRegistry.updateSubscribers(key,this);
	}

	/** Called via the IView relationship */
	//----------------------------------------------------------
	public void updateState(String key, Object value)
	{
		stateChangeRequest(key, value);
	}

	//-----------------------------------------------------------------------------------
	protected void initializeSchema(String tableName)
	{
		if (mySchema == null)
		{
			mySchema = getSchemaInfo(tableName);
		}
	}
}
