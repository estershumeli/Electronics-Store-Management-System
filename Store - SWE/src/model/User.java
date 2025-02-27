package model;

import util.Role;
import view.View;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class User implements Cloneable, Serializable {

    protected String id;
    protected String name;
    protected String username;
    protected String password;
    protected Role role;

    {
        id = UUID.randomUUID().toString();
    }

    public User() {
    }

    public User(String name, String username, String password, Role role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public Object clone() {
        Object object = null;

        try {
            object =  super.clone();
        } catch (CloneNotSupportedException e) {
        	System.out.println("Failed to clone object.");
            e.printStackTrace();
        }

        return object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
