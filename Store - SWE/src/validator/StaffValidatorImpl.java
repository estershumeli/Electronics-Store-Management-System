package validator;

import model.Employee;
import model.User;
import validator.StaffValidator;

public class StaffValidatorImpl implements StaffValidator {
    @Override
    public boolean validateCreatedUser(User user) {
        return user != null;
    }

    @Override
    public boolean validateUpdatedEmployee(Employee user) {
        return user != null;
    }
}
