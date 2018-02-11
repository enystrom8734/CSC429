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
                return new BookCollectionView(model);
            case "TellerView":
                return new TellerView(model);
            case "TransactionChoiceView":
                return new TransactionChoiceView(model);
            case "AccountCollectionView":
                return new AccountCollectionView(model);
            case "AccountView":
                return new AccountView(model);
            case "AccountHolderIDEntryView":
                return new AccountHolderIDEntryView(model);
            case "DepositTransactionView":
                return new DepositTransactionView(model);
            case "DepositAmountView":
                return new DepositAmountView(model);
            case "WithdrawTransactionView":
                return new WithdrawTransactionView(model);
            case "TransferTransactionView":
                return new TransferTransactionView(model);
            case "BalanceInquiryTransactionView":
                return new BalanceInquiryTransactionView(model);
            case "BalanceInquiryReceipt":
                return new BalanceInquiryReceipt(model);
            case "WithdrawReceipt":
                return new WithdrawReceipt(model);
            case "DepositReceipt":
                return new DepositReceipt(model);
            case "TransferReceipt":
                return new TransferReceipt(model);
            default:
                return null;
        }
    }
}
