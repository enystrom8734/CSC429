package userinterface;

import impresario.IModel;

public class ViewFactory {

    public static View createView(String viewName, IModel model) {
        switch (viewName) {
            case "LibrarianView":
                return new LibrarianView(model);
            case "BookView":
                return new BookView(model);
            case "BookCollectionView":
                return new BookCollectionView(model);
            case "BookSearchView":
                return new BookSearchView(model);
            case "PatronView":
                return new BookView(model);
            case "PatronCollectionView":
                return new BookCollectionView(model);
            case "PatronSearchView":
                return new BookSearchView(model);
            default:
                return null;
        }
    }
}
