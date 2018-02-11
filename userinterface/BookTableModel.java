package userinterface;

import javafx.beans.property.SimpleStringProperty;

import java.util.Vector;

public class BookTableModel {
    private final SimpleStringProperty bookId;
    private final SimpleStringProperty author;
    private final SimpleStringProperty title;
    private final SimpleStringProperty pubYear;
    private final SimpleStringProperty status;

    public BookTableModel(Vector<String> bookInfo) {
        bookId = new SimpleStringProperty(bookInfo.elementAt(0));
        author = new SimpleStringProperty(bookInfo.elementAt(1));
        title = new SimpleStringProperty(bookInfo.elementAt(2));
        pubYear = new SimpleStringProperty(bookInfo.elementAt(3));
        status = new SimpleStringProperty(bookInfo.elementAt(4));
    }

    public String getBookId() {
        return bookId.get();
    }

    public void setBookId(String number) {
        bookId.set(number);
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String aType) {
        author.set(aType);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String bal) {
        title.set(bal);
    }

    public String getPubYear() {
        return pubYear.get();
    }

    public void setPubYear(String charge) {
        pubYear.set(charge);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
