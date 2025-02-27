package validator;

import model.Bill;
import model.Item;

public interface CashierValidator {
    String itemBalanceErrorMessage = "Out of stock";

    boolean validateItemBalance(Item item, double quantityToSell);

    boolean validateBill(Bill bill);
}
