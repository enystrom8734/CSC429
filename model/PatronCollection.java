// specify the package
package model;

// system imports
import java.util.Properties;
import java.util.Vector;
import javafx.scene.Scene;

// project imports
import exception.InvalidPrimaryKeyException;
import event.Event;
import database.*;

import impresario.IView;

import userinterface.View;
import userinterface.ViewFactory;

/** The class containing the AccountCollection for the ATM application */
//==============================================================
public class PatronCollection  extends EntityBase implements IView
{
	private static final String myTableName = "Patron";

	private Vector<Account> accounts;
	// GUI Components

	// constructor for this class
	//----------------------------------------------------------
	public PatronCollection( ) 
	{
		patronList = new Vector(); // new Vector<Patron>();
	}

	public void findPatronsOlderThan(String date)
	{
		String query = "SELECT * FROM + myTableName + " WHERE (dateOfBirth > " + date + ");";
	}

	public void findPatronsYoungerThan(String date)
	{
		String query = "SELECT * FROM + myTableName + "WHERE (dateOfBirth < " + date + ");";
	}

	pubic void findPatronsAtZipCode(String zip)
	{
		String query = "SELECT * FROM + myTableName + "WHERE (zipcode = " + zip + ");";
	}

	public Void findPatronsWithNameLike(String name)
	{
		String query = "SELECT * FROM + myTableName + "WHERE (name LIKE '%" + name + "%');"
	}