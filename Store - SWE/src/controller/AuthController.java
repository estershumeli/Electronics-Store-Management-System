package controller;

import model.*;
import service.AuthService;
import service.CashierService;
import util.Role;
import view.*;
import view.View;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static util.Constant.appTitle;

public class AuthController {
    public static Boolean isAuthenticated;
    private static final AuthService authService;

    static {
        isAuthenticated = false;
        authService = new AuthService();
    }

    public static void signIn(ActionEvent actionEvent) {
        View view = new SignInView();

        SignInView signInView = (SignInView) ((Node) actionEvent.getSource()).getScene().getRoot();
        String username = signInView.getUsernameField().getText();
        String password = signInView.getPasswordField().getText();

        User user = authService.findByUsername(username);
        if (user != null) {
            User attemptUser = (User) user.clone();
            attemptUser.setPassword(password);

            authService.authenticateUser(attemptUser, user);

            if (isAuthenticated) {
                HomeController.setUser(user);
                HomeController.self(actionEvent);
            } else {
                Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(view, 1000, 600);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    public static void signOut(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        isAuthenticated = false;
        HomeView homeView = new HomeView();
        Scene scene = new Scene(homeView, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }
}
