package validator;

import model.Category;

public interface CategoryValidator {
    String catExistsMessage = "This category already exists";

    boolean validateCreatedCategory(Category cat);
}
