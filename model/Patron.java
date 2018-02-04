// specify the package
package model;

// system imports
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import javax.swing.JFrame;

// project imports
import exception.InvalidPrimaryKeyException;
import database.*;

import impresario.IView;

import userinterface.View;
//import userinterface.ViewFactory;

/** The class containing the Patron for the application */
//==============================================================
public class Patron extends EntityBase implements IView
{
	private static final String myTableName = "Account";

	protected Properties dependencies;

	// GUI Components

	private String updateStatusMessage = "";

	// constructor for this class
	//----------------------------------------------------------
	public Patron(String patronId)
			throws InvalidPrimaryKeyException
	{
		super(myTableName);

		setDependencies();
		String query = "SELECT * FROM " + myTableName + " WHERE (patronId = " + patronId + ")";

		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);

		// You must get one account at least
		if (allDataRetrieved != null)
		{
			int size = allDataRetrieved.size();

			// There should be EXACTLY one patron. Else error
			if (size != 1)
			{
				throw new InvalidPrimaryKeyException("Multiple patrons matching id : "
						+ patronId + " found.");
			}
			else
			{
				// copy all the retrieved data into persistent state
				Properties retrievedPatronData = allDataRetrieved.elementAt(0);
				persistentState = new Properties();

				Enumeration allKeys = retrievedPatronData.propertyNames();
				while (allKeys.hasMoreElements() == true)
				{
					String nextKey = (String)allKeys.nextElement();
					String nextValue = retrievedPatronData.getProperty(nextKey);
					// patronId = Integer.parseInt(retrievedPatronData.getProperty("patronId"));

					if (nextValue != null)
					{
						persistentState.setProperty(nextKey, nextValue);
					}
				}

			}
		}
		// If no patron found for this Id, throw an exception
		else
		{
			throw new InvalidPrimaryKeyException("No account matching id : "
					+ patronId + " found.");
		}
	}

	// Can also be used to create a NEW patron (if the system it is part of
	// allows for a new patron to be set up)
	//----------------------------------------------------------
	public Patron(Properties patronInfo)
	{
		super(myTableName);

		setDependencies();
		persistentState = new Properties();
		Enumeration allKeys = patronInfo.propertyNames();
		while (allKeys.hasMoreElements() == true)
		{
			String nextKey = (String)allKeys.nextElement();
			String nextValue = patronInfo.getProperty(nextKey);

			if (nextValue != null)
			{
				persistentState.setProperty(nextKey, nextValue);
			}
		}
	}

	//-----------------------------------------------------------------------------------
	private void setDependencies()
	{
		dependencies = new Properties();

		myRegistry.setDependencies(dependencies);
	}

	//----------------------------------------------------------
	public Object getState(String key)
	{
		if (key.equals("UpdateStatusMessage") == true)
			return updateStatusMessage;

		return persistentState.getProperty(key);
	}

	//----------------------------------------------------------------
	public void stateChangeRequest(String key, Object value)
	{

		myRegistry.updateSubscribers(key, this);
	}

	/** Called via the IView relationship */
	//----------------------------------------------------------
	public void updateState(String key, Object value)
	{
		stateChangeRequest(key, value);
	}



	//-----------------------------------------------------------------------------------
	public void update()
	{
		updateStateInDatabase();
	}

	//-----------------------------------------------------------------------------------
	private void updateStateInDatabase()
	{
		try
		{
			if (persistentState.getProperty("patronId") != null)
			{
				Properties whereClause = new Properties();
				whereClause.setProperty("patronId",
						persistentState.getProperty("patronId"));
				updatePersistentState(mySchema, persistentState, whereClause);
				updateStatusMessage = "Patron data for patronId : " + persistentState.getProperty("PatronId") + " updated successfully in database!";
			}
			else
			{
				Integer patronId =
						insertAutoIncrementalPersistentState(mySchema, persistentState);
				persistentState.setProperty("PatronId", "" + patronId.intValue());
				updateStatusMessage = "Patron data for new patron : " +  persistentState.getProperty("PatronId")
						+ "installed successfully in database!";
			}
		}
		catch (SQLException ex)
		{
			updateStatusMessage = "Error inserting patron data in database!";
		}
		//DEBUG System.out.println("updateStateInDatabase " + updateStatusMessage);
	}


	/**
	 * This method is needed solely to enable the Patron information to be displayable in a table
	 *
	 */
	//--------------------------------------------------------------------------
	public Vector<String> getEntryListView()
	{
		Vector<String> v = new Vector<String>();

		v.addElement(persistentState.getProperty("PatronId"));
		v.addElement(persistentState.getProperty("Name"));
		v.addElement(persistentState.getProperty("Address"));
		v.addElement(persistentState.getProperty("City"));
		v.addElement(persistentState.getProperty("StateCode"));
		v.addElement(persistentState.getProperty("Zip"));
		v.addElement(persistentState.getProperty("Email"));
		v.addElement(persistentState.getProperty("DateOfBirth"));
		v.addElement(persistentState.getProperty("Status"));

		return v;
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





		