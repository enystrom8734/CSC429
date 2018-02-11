// specify the package
package model;

// system imports

import event.Event;
import exception.InvalidPrimaryKeyException;
import exception.PasswordMismatchException;
import impresario.IModel;
import impresario.IView;
import impresario.ModelRegistry;
import javafx.scene.Scene;
import javafx.stage.Stage;
import userinterface.MainStageContainer;
import userinterface.View;
import userinterface.ViewFactory;
import userinterface.WindowPosition;

import java.util.Hashtable;
import java.util.Properties;

/**
 * The class containing the Teller  for the ATM application
 */
public class Librarian implements IView, IModel
// This class implements all these interfaces (and does NOT extend 'EntityBase')
// because it does NOT play the role of accessing the back-end database tables.
// It only plays a front-end role. 'EntityBase' objects play both roles.
{
    private ModelRegistry myRegistry;

    private AccountHolder myAccountHolder;

    // GUI Components
    private Hashtable<String, Scene> myViews;
    private Stage myStage;

    private String loginErrorMessage = "";
    private String transactionErrorMessage = "";

    // constructor for this class
    public Librarian() {
        myStage = MainStageContainer.getInstance();
        myViews = new Hashtable<>();

        // STEP 3.1: Create the Registry object - if you inherit from
        // EntityBase, this is done for you. Otherwise, you do it yourself
        myRegistry = new ModelRegistry("Librarian");
        // IDE informs always false. Will never run this code
//        if (myRegistry == null) {
//            new Event(Event.getLeafLevelClassName(this), "Librarian",
//                    "Could not instantiate Registry", Event.ERROR);
//        }

        // STEP 3.2: Be sure to set the dependencies correctly
        setDependencies();

        // Set up the initial view
        createAndShowLibrarianView();
    }

    private void setDependencies() {
        Properties dependencies = new Properties();
        dependencies.setProperty("NewBook", "TransactionError");
        dependencies.setProperty("NewPatron", "TransactionError");
        dependencies.setProperty("SearchBooks", "TransactionError");
        dependencies.setProperty("SearchPatrons", "TransactionError");
        myRegistry.setDependencies(dependencies);
    }

    /**
     * Method called from client to get the value of a particular field
     * held by the objects encapsulated by this object.
     *
     * @param key Name of database column (field) for which the client wants the value
     * @return Value associated with the field
     */
    public Object getState(String key) {
        switch (key) {
            case "LoginError":
                return loginErrorMessage;
            case "TransactionError":
                return transactionErrorMessage;
            case "Name":
                if (myAccountHolder != null) {
                    return myAccountHolder.getState("Name");
                } else
                    return "Undefined";
            default:
                return "";
        }
    }

    public void stateChangeRequest(String key, Object value) {
        // STEP 4: Write the sCR method component for the key you just set up dependencies for
        switch (key) {
            case "NewBook":
                createNewBook();
                break;
            case "SearchBooks":
                searchBooks();
                break;
        }

        myRegistry.updateSubscribers(key, this);
    }

    /**
     * Called via the IView relationship
     */
    public void updateState(String key, Object value) {
        // DEBUG System.out.println("Teller.updateState: key: " + key);
        stateChangeRequest(key, value);
    }

    /**
     * Login AccountHolder corresponding to user name and password.
     */
    private boolean loginAccountHolder(Properties props) {
        try {
            myAccountHolder = new AccountHolder(props);
            // DEBUG System.out.println("Account Holder: " + myAccountHolder.getState("Name") + " successfully logged in");
            return true;
        } catch (InvalidPrimaryKeyException ex) {
            loginErrorMessage = "ERROR: " + ex.getMessage();
            return false;
        } catch (PasswordMismatchException exec) {

            loginErrorMessage = "ERROR: " + exec.getMessage();
            return false;
        }
    }

    private void createNewBook() {
        Book newBook = new Book();
        newBook.createAndShowBookView();
    }

    //    private void createNewPatron() {
//
//    }
    private void searchBooks() {

    }

    //    private void searchPatron() {
//
//    }
    private void createAndShowTransactionChoiceView() {
        Scene currentScene = (Scene) myViews.get("TransactionChoiceView");

        if (currentScene == null) {
            // create our initial view
            View newView = ViewFactory.createView("TransactionChoiceView", this); // USE VIEW FACTORY
            currentScene = new Scene(newView);
            myViews.put("TransactionChoiceView", currentScene);
        }


        // make the view visible by installing it into the frame
        swapToView(currentScene);

    }

    private void createAndShowLibrarianView() {
        Scene currentScene = (Scene) myViews.get("LibrarianView");

        if (currentScene == null) {
            // create our initial view
            View newView = ViewFactory.createView("LibrarianView", this); // USE VIEW FACTORY
            currentScene = new Scene(newView);
            myViews.put("LibrarianView", currentScene);
        }

        swapToView(currentScene);

    }

    /**
     * Register objects to receive state updates.
     */
    public void subscribe(String key, IView subscriber) {
        // DEBUG: System.out.println("Cager[" + myTableName + "].subscribe");
        // forward to our registry
        myRegistry.subscribe(key, subscriber);
    }

    /**
     * Unregister previously registered objects.
     */
    public void unSubscribe(String key, IView subscriber) {
        // DEBUG: System.out.println(".unSubscribe");
        // forward to our registry
        myRegistry.unSubscribe(key, subscriber);
    }

    public void swapToView(Scene newScene) {


        if (newScene == null) {
            System.out.println("Librarian.swapToView(): Missing view for display");
            new Event(Event.getLeafLevelClassName(this), "swapToView",
                    "Missing view for display ", Event.ERROR);
            return;
        }

        myStage.setScene(newScene);
        myStage.sizeToScene();


        //Place in center
        WindowPosition.placeCenter(myStage);
    }
}

