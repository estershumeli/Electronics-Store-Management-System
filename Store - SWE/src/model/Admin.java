package model;


import model.User;
import util.Role;
import view.AdminView;

public class Admin extends User {
    public Admin() {
        super();
    }

    public Admin(String name, String username, String password, Role role) {
        super(name, username, password, role);
    }

    public Admin(User user) {
        this(user.getName(), user.getUsername(), user.getPassword(), user.getRole());
    }
}
