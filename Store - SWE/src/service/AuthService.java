package service;

import model.User;
import repository.UserRepository;
import repository.UsersRepositoryImpl;

import static controller.AuthController.isAuthenticated;

public class AuthService {
    private static final UserRepository userRepository;

    static {
        userRepository = new UsersRepositoryImpl();
    }


    public void authenticateUser(User attemptUser, User actualUser) {
        String attemptPassword = attemptUser.getPassword();
        String actualPassword = actualUser.getPassword();

        if (attemptPassword.equals(actualPassword)) {
            isAuthenticated = true;
        }
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
