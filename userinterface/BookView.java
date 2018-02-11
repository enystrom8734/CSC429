// specify the package
package userinterface;

// system imports

import impresario.IModel;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Properties;
import java.util.regex.Pattern;

// project imports

/**
 * The class containing the Account View  for the ATM application
 */
public class BookView extends View {

    // GUI components
    protected TextField authorField;
    protected TextField titleField;
    protected TextField pubYearField;
    protected TextField serviceCharge;

    ComboBox statusCombo;

    protected Button doneButton;
    protected Button submitButton;

    // For showing error message
    protected MessageView statusLog;

    // constructor for this class -- takes a model object
    public BookView(IModel book) {
        super(book, "BookView");

        // create a container for showing the contents
        VBox container = new VBox(10);
        container.setPadding(new Insets(15, 5, 5, 5));

        // Add a title for this panel
        container.getChildren().add(createTitle());

        // create our GUI components, add them to this Container
        container.getChildren().add(createFormContent());

        container.getChildren().add(createStatusLog("             "));

        getChildren().add(container);

//        populateFields();

        myModel.subscribe("ServiceCharge", this);
        myModel.subscribe("UpdateStatusMessage", this);
    }


    // Create the title container
    private Node createTitle() {
        HBox container = new HBox();
        container.setAlignment(Pos.CENTER);

        Text titleText = new Text(" Brockport Library System ");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleText.setWrappingWidth(300);
        titleText.setTextAlignment(TextAlignment.CENTER);
        titleText.setFill(Color.DARKGREEN);
        container.getChildren().add(titleText);

        return container;
    }

    // Create the main form content
    private VBox createFormContent() {
        VBox vbox = new VBox(10);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 0, 25));

        Text prompt = new Text("Enter New Book Information");
        prompt.setWrappingWidth(400);
        prompt.setTextAlignment(TextAlignment.CENTER);
        prompt.setFill(Color.BLACK);
        grid.add(prompt, 0, 0, 2, 1);

        Text authorLabel = new Text(" Author : ");
        Font myFont = Font.font("Helvetica", FontWeight.BOLD, 12);
        authorLabel.setFont(myFont);
        authorLabel.setWrappingWidth(150);
        authorLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(authorLabel, 0, 1);

        authorField = new TextField();
        authorField.setOnAction(e -> processAction(e));
        grid.add(authorField, 1, 1);

        Text titleLabel = new Text(" Title : ");
        titleLabel.setFont(myFont);
        titleLabel.setWrappingWidth(150);
        titleLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(titleLabel, 0, 2);

        titleField = new TextField();
        titleField.setOnAction(e -> processAction(e));

        grid.add(titleField, 1, 2);

        Text pubYearLabel = new Text(" Publication Year : ");
        pubYearLabel.setFont(myFont);
        pubYearLabel.setWrappingWidth(150);
        pubYearLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(pubYearLabel, 0, 3);

        pubYearField = new TextField();
        pubYearField.setOnAction(e -> processAction(e));
        pubYearField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,4}")) {
                pubYearField.setText(oldValue);
            }
        });
        grid.add(pubYearField, 1, 3);
// Combo box creation
//        ObservableList<String> options = FXCollections.observableArrayList(
//                "Active",
//                "Inactive"
//        );
        Text statusLabel = new Text(" Status : ");
        statusLabel.setFont(myFont);
        statusLabel.setWrappingWidth(150);
        statusLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(statusLabel, 0, 4);

        statusCombo = new ComboBox();
        statusCombo.getItems().addAll("Active", "Inactive");
        statusCombo.setValue("Active");
        grid.add(statusCombo, 1, 4);


        HBox doneCont = new HBox(10);
        doneCont.setAlignment(Pos.CENTER);
        submitButton = new Button("Submit");
        submitButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        submitButton.setOnAction(this::processAction);

        doneButton = new Button("Back");
        doneButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        doneButton.setOnAction(e -> {
            clearErrorMessage();
            myModel.stateChangeRequest("CancelAddBook", null);
        });
        doneCont.getChildren().add(submitButton);
        doneCont.getChildren().add(doneButton);

        vbox.getChildren().add(grid);
        vbox.getChildren().add(doneCont);

        return vbox;
    }


    // Create the status log field
    protected MessageView createStatusLog(String initialMessage) {
        statusLog = new MessageView(initialMessage);

        return statusLog;
    }

//    public void populateFields() {
//        authorField.setText((String) myModel.getState("author"));
//        titleField.setText((String) myModel.getState("title"));
//        pubYearField.setText((String) myModel.getState("pubYear"));
//        serviceCharge.setText((String) myModel.getState("status"));
//    }

    public void processAction(Event evt)
    {
        // DEBUG: System.out.println("TellerView.actionPerformed()");

        clearErrorMessage();

        String authorEntered = authorField.getText();
        String titleEntered = titleField.getText();
        String pubYearEntered = pubYearField.getText();
        String statusSelected = (String) statusCombo.getValue();

        Pattern pubYearValidation = Pattern.compile("^(18\\d\\d|19\\d\\d|200\\d|201[0-8])$");

        if ((authorEntered == null) || (authorEntered.length() == 0)) {
            displayErrorMessage("Please enter an author!");
            authorField.requestFocus();
        } else if ((titleEntered == null) || (titleEntered.length() == 0)) {
            displayErrorMessage("Please enter a title!");
            titleField.requestFocus();
        } else if (!pubYearValidation.matcher(pubYearEntered).matches()) {
            displayErrorMessage("Year must be between 1800 and 2018");
            pubYearField.requestFocus();
        } else {
            Properties props = new Properties();
            props.setProperty("author", authorEntered);
            props.setProperty("title", titleEntered);
            props.setProperty("pubYear", pubYearEntered);
            props.setProperty("status", statusSelected.toLowerCase());
            myModel.stateChangeRequest("InsertBook", props);
            displayMessage("Success!");
        }

    }

    /**
     * Update method
     */
    //---------------------------------------------------------
    public void updateState(String key, Object value) {
        clearErrorMessage();

        if (key.equals("ServiceCharge")) {
            String val = (String) value;
            serviceCharge.setText(val);
            displayMessage("Service Charge Imposed: $ " + val);
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
     * Display info message
     */
    //----------------------------------------------------------
    public void displayMessage(String message) {
        statusLog.displayMessage(message);
    }

    /**
     * Clear error message
     */
    //----------------------------------------------------------
    public void clearErrorMessage() {
        statusLog.clearErrorMessage();
    }

}

//---------------------------------------------------------------
//	Revision History:
//


