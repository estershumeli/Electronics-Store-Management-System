package validator;

import model.Category;
import validator.CategoryValidator;

public class CategoryValidatorImpl implements CategoryValidator {
    public boolean validateCreatedCategory(Category cat) {
        return cat != null;
    }
}
