package validator;

import model.Item;

public interface ItemValidator {
    String itemsExistsErrorMessage = "This item already exists";

    boolean validateCreatedItem(Item item);

    boolean validateUpdatedItem(Item item);
}
