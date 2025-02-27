package validator;

import model.Employee;
import model.User;

public interface StaffValidator {
    String usernameTakenErrorMessage = "Username is taken";

    boolean validateCreatedUser(User user);

    boolean validateUpdatedEmployee(Employee user);
}
