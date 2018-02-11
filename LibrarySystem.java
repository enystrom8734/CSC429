// specify the package

// system imports

import event.Event;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Librarian;
import userinterface.MainStageContainer;
import userinterface.WindowPosition;

// project imports


/**
 * The class containing the main program  for the ATM application
 */
//==============================================================
public class LibrarySystem extends Application {


    /**
     * The "main" entry point for the application. Carries out actions to
     * set up the application
     */
    //----------------------------------------------------------
    public static void main(String[] args) {

        launch(args);
    }

    // start method for this class, the main application object
    //----------------------------------------------------------
    public void start(Stage primaryStage) {
        System.out.println("Library System v0.01");
        System.out.println("Cholla Grygotis and Erik Nystrom");

        // Create the top-level container (main frame) and add contents to it.
        MainStageContainer.setStage(primaryStage, "Brockport Library System v0.01");
        // Main frame of the application
        Stage mainStage = MainStageContainer.getInstance();

        // Finish setting up the stage (ENABLE THE GUI TO BE CLOSED USING THE TOP RIGHT
        // 'X' IN THE WINDOW), and show it.
        mainStage.setOnCloseRequest(event -> System.exit(0));
        mainStage.initStyle(StageStyle.UTILITY);

        try {
            new Librarian();
        } catch (Exception exc) {
            System.err.println("Could not create Librarian!");
            new Event(Event.getLeafLevelClassName(this), "LibrarySystem.<init>", "Unable to create Librarian object", Event.ERROR);
            exc.printStackTrace();
        }


        WindowPosition.placeCenter(mainStage);

        mainStage.show();
    }

}
