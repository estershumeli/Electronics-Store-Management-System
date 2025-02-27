package service;

import model.Admin;
import model.Cashier;
import model.Manager;
import model.User;
import repository.UserRepository;
import repository.UsersRepositoryImpl;
import util.Role;
import validator.StaffValidator;
import validator.StaffValidatorImpl;
import view.CreateStaffView;

import java.time.LocalDate;

import static util.Alerter.showError;
import static validator.StaffValidator.usernameTakenErrorMessage;

public class CreateStaffService {
    private static final UserRepository userRepository;
    private static final StaffValidator STAFF_VALIDATOR;

    static {
        userRepository = new UsersRepositoryImpl();
        STAFF_VALIDATOR = new StaffValidatorImpl();
    }

    public boolean createUser(CreateStaffView createStaffView) {
        String name = createStaffView.getName().getText();
        String username = createStaffView.getUsername().getText();
        String password = createStaffView.getPassword().getText();
        Role role = (Role) createStaffView.getRoles().getValue();

        User user = new User(name, username, password, role);

        if (user.getRole() == Role.ADMIN) {
            user = new Admin(user);
        } else {
            LocalDate birthday = createStaffView.getBirthday().getValue();
            String phone = createStaffView.getPhone().getText();
            String email = createStaffView.getEmail().getText();
            double salary = Double.parseDouble(createStaffView.getSalary().getText());

            if (user.getRole() == Role.MANAGER) {
                user = new Manager(user, birthday, phone, email, salary);
            } else {
                user = new Cashier(user, birthday, phone, email, salary);
            }
        }

        User created = userRepository.create(user);
        boolean validCreatedUser = STAFF_VALIDATOR.validateCreatedUser(created);

        if (!validCreatedUser) {
            showError(usernameTakenErrorMessage);
            return false;
        }

        return true;
    }
}
