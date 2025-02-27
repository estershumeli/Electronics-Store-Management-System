package validator;

import model.Bill;
import model.Item;
import validator.CashierValidator;

public class CashierValidatorImpl implements CashierValidator {
    @Override
    public boolean validateItemBalance(Item item, double quantityToSell) {
        double boughtQuantity = item.getBoughtQuantity();
        double soldQuantity = item.getSoldQuantity();
        double balance = boughtQuantity - soldQuantity - quantityToSell;

        return balance >= 0;
    }

    @Override
    public boolean validateBill(Bill bill) {
        return bill.getItems().size() > 0;
    }
}
