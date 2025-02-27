package model;

import model.User;
import util.Role;

import java.time.LocalDate;

public class Cashier extends Employee {
    public Cashier() {
        super();
    }

    public Cashier(String name, String username, String password, Role role, LocalDate birthday, String phone, String email, double salary) {
        super(name, username, password, role, birthday, phone, email, salary);
    }

    public Cashier(User user, LocalDate birthday, String phone, String email, double salary) {
        this(user.getName(), user.getUsername(), user.getPassword(), user.getRole(), birthday, phone, email, salary);
    }
}
