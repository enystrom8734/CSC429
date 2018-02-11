// specify the package
package userinterface;

// system imports

import impresario.IModel;
import javafx.event.Event;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Properties;

// project imports

/**
 * The class containing the Teller View  for the ATM application
 */
//==============================================================
public class LibrarianView extends View {

    // GUI stuff
//    private TextField userid;
//    private PasswordField password;
    private Button insertNewBook;
    private Button insertNewPatron;
    private Button searchBooks;
    private Button searchPatrons;
    private Button doneButton;

    // For showing error message
    private MessageView statusLog;
    private Event e;

    // constructor for this class -- takes a model object
    //----------------------------------------------------------
    public LibrarianView(IModel librarian) {

        super(librarian, "LibrarianView");

        // create a container for showing the contents
        VBox container = new VBox(10);

        container.setPadding(new Insets(15, 5, 5, 5));

        // create a Node (Text) for showing the title
        container.getChildren().add(createTitle());

        // create a Node (GridPane) for showing data entry fields
        container.getChildren().add(createFormContents());

        // Error message area
        container.getChildren().add(createStatusLog("                          "));

        getChildren().add(container);

        populateFields();

        // STEP 0: Be sure you tell your model what keys you are interested in
        myModel.subscribe("TransactionError", this);
    }

    // Create the label (Text) for the title of the screen
    //-------------------------------------------------------------
    private Node createTitle() {

        Text titleText = new Text("  Brockport Library   \n System");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleText.setTextAlignment(TextAlignment.CENTER);
        titleText.setFill(Color.BLACK);

        return titleText;
    }

    // Create the main form contents
    //-------------------------------------------------------------
    private GridPane createFormContents() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        insertNewBook = new Button("Insert New Book");
        insertNewBook.setOnAction(
                e -> myModel.stateChangeRequest("NewBook", ""));
        grid.add(insertNewBook, 0,0);
        GridPane.setHalignment(insertNewBook, HPos.CENTER);

        insertNewPatron = new Button("Insert New Patron");
        insertNewPatron.setOnAction(this::processAction);
        grid.add(insertNewPatron, 0,1);
        GridPane.setHalignment(insertNewPatron, HPos.CENTER);

        searchBooks = new Button("Search Books");
        searchBooks.setOnAction(
                e -> myModel.stateChangeRequest("SearchBooks", ""));
        grid.add(searchBooks, 0,2);
        GridPane.setHalignment(searchBooks, HPos.CENTER);


        searchPatrons = new Button("Search Patrons");
        searchPatrons.setOnAction(this::processAction);
        grid.add(searchPatrons, 0,3);
        GridPane.setHalignment(searchPatrons, HPos.CENTER);


        doneButton = new Button("Done");
        doneButton.setOnAction(e -> System.exit(1));

//        HBox btnContainer = new HBox(10);
//        btnContainer.setAlignment(Pos.BOTTOM_RIGHT);
//        btnContainer.getChildren().add(doneButton);
//        grid.add(btnContainer, 0, 4);
        grid.add(doneButton, 0, 6);
        GridPane.setHalignment(doneButton, HPos.CENTER);


        return grid;
    }


    // Create the status log field
    //-------------------------------------------------------------
    private MessageView createStatusLog(String initialMessage) {

        statusLog = new MessageView(initialMessage);

        return statusLog;
    }

    //-------------------------------------------------------------
    public void populateFields() {
//        userid.setText("");
//        password.setText("");
    }

    // This method processes events generated from our GUI components.
    // Make the ActionListeners delegate to this method
    //-------------------------------------------------------------
    public void processAction(Event e) {
//        this.e = e;
        System.out.println("TellerView.actionPerformed() " + e);
        System.out.println(e.getTarget());
        clearErrorMessage();

//        String useridEntered = userid.getText();
//
//        if ((useridEntered == null) && (useridEntered.length() == 0)) {
//            displayErrorMessage("Please enter a user id!");
//            userid.requestFocus();
//        } else {
//            String passwordEntered = password.getText();
//            processUserIDAndPassword(useridEntered, passwordEntered);
//        }

    }

    /**
     * Process userid and pwd supplied when Submit button is hit.
     * Action is to pass this info on to the teller object
     */
    //----------------------------------------------------------
    private void processUserIDAndPassword(String useridString, String passwordString) {
        Properties props = new Properties();
        props.setProperty("ID", useridString);
        props.setProperty("Password", passwordString);

        // clear fields for next time around
//        userid.setText("a");
//        password.setText("b");

        myModel.stateChangeRequest("Login", props);
    }

    //---------------------------------------------------------
    public void updateState(String key, Object value) {
        // STEP 6: Be sure to finish the end of the 'perturbation'
        // by indicating how the view state gets updated.
        if (key.equals("LoginError")) {
            // display the passed text
            displayErrorMessage((String) value);
        }

    }

    /**
     * Display error message
     */
    //----------------------------------------------------------
    public void displayErrorMessage(String message) {
        statusLog.displayErrorMessage(message);
    }

    /**
     * Clear error message
     */
    //----------------------------------------------------------
    public void clearErrorMessage() {
        statusLog.clearErrorMessage();
    }

}

