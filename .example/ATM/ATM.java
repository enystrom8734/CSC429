
// specify the package

// system imports

import event.Event;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Teller;
import userinterface.MainStageContainer;
import userinterface.WindowPosition;

// project imports


/** The class containing the main program  for the ATM application */
//==============================================================
public class ATM extends Application
{

	private Teller myTeller;		// the main behavior for the application

	/** Main frame of the application */
	private Stage mainStage;


	// start method for this class, the main application object
	//----------------------------------------------------------
	public void start(Stage primaryStage)
	{
	   System.out.println("ATM Version 3.00");
	   System.out.println("Copyright 2004/2015 Sandeep Mitra and T M Rao");

           // Create the top-level container (main frame) and add contents to it.
	   MainStageContainer.setStage(primaryStage, "Brockport Bank ATM Version 3.00");
	   mainStage = MainStageContainer.getInstance();

	   // Finish setting up the stage (ENABLE THE GUI TO BE CLOSED USING THE TOP RIGHT
	   // 'X' IN THE WINDOW), and show it.
           mainStage.setOnCloseRequest(event -> System.exit(0));

           try
	   {
		myTeller = new Teller();
	   }
	   catch(Exception exc)
	   {
		System.err.println("ATM.ATM - could not create Teller!");
		new Event(Event.getLeafLevelClassName(this), "ATM.<init>", "Unable to create Teller object", Event.ERROR);
		exc.printStackTrace();
	   }


  	   WindowPosition.placeCenter(mainStage);

           mainStage.show();
	}


	/** 
	 * The "main" entry point for the application. Carries out actions to
	 * set up the application
	 */
	//----------------------------------------------------------
    	public static void main(String[] args)
	{

		launch(args);
	}

}
