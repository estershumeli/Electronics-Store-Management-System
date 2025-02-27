package model;

import model.User;
import util.Role;

import java.time.LocalDate;

public class Employee extends User {
    protected LocalDate birthday;
    protected String phone;
    protected String email;
    protected double salary;

    public Employee() {
        super();
    }

    public Employee(String name, String username, String password, Role role, LocalDate birthday, String phone, String email, double salary) {
        super(name, username, password, role);
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.salary = salary;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
