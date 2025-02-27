package service;

import model.Employee;
import repository.UserRepository;
import repository.UsersRepositoryImpl;
import util.Role;
import validator.StaffValidator;
import validator.StaffValidatorImpl;
import view.EditStaffView;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static util.Alerter.showError;
import static validator.StaffValidator.usernameTakenErrorMessage;

public class StaffService {
    private static final UserRepository userRepository;
    private static final StaffValidator staffValidator;

    static {
        userRepository = new UsersRepositoryImpl();
        staffValidator = new StaffValidatorImpl();
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = userRepository.findAll()
                .stream()
                .filter(user -> user.getRole() == Role.MANAGER || user.getRole() == Role.CASHIER)
                .map(user -> (Employee) user)
                .collect(Collectors.toList());

        return employees;
    }

    public Employee findById(String id) {
        return (Employee) userRepository.findById(id);
    }

    public boolean update(EditStaffView view, String id) {
        String name = view.getName().getText();
        String username = view.getUsername().getText();
        String password = view.getPassword().getText();
        Role role = (Role) view.getRoles().getValue();
        LocalDate birthday = view.getBirthday().getValue();
        String phone = view.getPhone().getText();
        String email = view.getEmail().getText();
        double salary = Double.parseDouble(view.getSalary().getText());

        Employee newEmployee = new Employee(name, username, password, role, birthday, phone, email, salary);
        newEmployee.setId(id);

        Employee updated = (Employee) userRepository.update(newEmployee);
        boolean validUpdatedCd = staffValidator.validateUpdatedEmployee(updated);

        if (!validUpdatedCd) {
            showError(usernameTakenErrorMessage);
            return false;
        }

        return true;
    }

    public boolean delete(String id) {
        Employee employee = new Employee();
        employee.setId(id);
        Employee deleted = (Employee) userRepository.delete(employee);

        return deleted != null;
    }
}
