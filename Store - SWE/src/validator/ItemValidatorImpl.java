package validator;

import model.Item;
import validator.ItemValidator;

public class ItemValidatorImpl implements ItemValidator {
    @Override
    public boolean validateCreatedItem(Item item) {
        return item != null;
    }

    @Override
    public boolean validateUpdatedItem(Item item) {
        return item != null;
    }
}
