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
import userinterface.ViewFactory;

//==============================================================
public class Patron extends EntityBase implements IView
{
	private static final String myTableName = "Patron";

	protected Properties dependencies;

	// GUI Components

	private String updateStatusMessage = "";

	// constructor for this class
	//----------------------------------------------------------
	public Patron(String properties) throws InvalidPrimaryKeyException
	{
		??? = properties;
	}	

	public Patron(String primaryKey) throws InvalidPrimaryKeyException
	{
	
	}

	public void update()
	{
	}

		