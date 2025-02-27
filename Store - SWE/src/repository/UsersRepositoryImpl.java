package repository;

import start.Main;
import util.Role;
import model.User;
import repository.UserRepository;



import java.io.*;
import java.util.HashSet;
import java.util.Set;

import static util.Constant.baseLocation;

public class UsersRepositoryImpl implements UserRepository {
    //        Test
    private static final String dataLocation = "Store - SWE/static/data/users.dat";
    ;

    //        Production
    //private static String dataLocation = baseLocation + "/data/users.dat";
    private static ObjectOutputStream usersOutput;

    private static Set<User> users;
    static {
        initializeData();
    }

    private static void initializeData() {
        users = tryToInitializeData();
    }

    private static Set<User> tryToInitializeData() {
        Set<User> users = new HashSet<>();

        try {
            ObjectInputStream usersInput = new ObjectInputStream(new FileInputStream(dataLocation));
            users = (HashSet<User>) usersInput.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to find users data.");
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            if (! (e instanceof EOFException)) {
            	System.out.println("Failed to read users data.");
                e.printStackTrace();
            }
        }

        return users;
    }

    private static void initializeOutput() {
        try {
            usersOutput = new ObjectOutputStream(new FileOutputStream(dataLocation));
        } catch (FileNotFoundException e) {
        	System.out.println("Failed to find users data.");
            e.printStackTrace();
        } catch (IOException e) {
        	System.out.println("Failed to read users data.");
            e.printStackTrace();
        }
    }


    @Override
    public User findByUsername(String username) {
        for (User user: users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }


    @Override
    public User create(User user) {
        User created = tryToCreateUser(user);
        return created;
    }

    public User tryToCreateUser(User user) {
        initializeOutput();
        Role role = Role.ADMIN;
        //System.out.print("test");
        //users = new HashSet<>(); 
        //user = new User("admin","admin", "admin",role);
        //users.add(user);
        try {
            boolean unique = users.add(user);
            if (unique) {
                usersOutput.writeObject(users);
                usersOutput.flush();
                return user;
            }
        } catch (IOException e) {
        	System.out.println("Failed to create user.");
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public User findById(String id) {
        User found = null;

        for(User user: users) {
            if (user.getId().equals(id)) {
                found = user;
            }
        }

        return found;
    }


    @Override
    public Set<User> findAll() {
        return users;
    }


    @Override
    public User update(User user) {
        initializeOutput();
        User before = findById(user.getId());

        User updated = tryToUpdateUser(before, user, users);
        return updated;
    }

    private static User tryToUpdateUser(User before, User after, Set<User> users) {
        try {
            users.remove(before);
            boolean unique = users.add(after);
            if (unique) {
                usersOutput.writeObject(users);
                usersOutput.flush();
                return after;
            }
        } catch (IOException e) {
            System.out.println("Failed to update user.");
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public User delete(User user) {
        initializeOutput();
        user = findById(user.getId());

        User deleted = tryToDeleteUser(user, users);
        return deleted;
    }

    private static User tryToDeleteUser(User user, Set<User> users) {
        try {
            users.remove(user);
            usersOutput.writeObject(users);
            usersOutput.flush();
            return user;
        } catch (IOException e) {
        	System.out.println("Failed to delete user.");
            e.printStackTrace();
        }

        return null;
    }
}
