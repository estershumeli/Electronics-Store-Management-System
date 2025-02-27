package view;

import controller.AuthController;
import controller.HomeController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import static controller.AuthController.isAuthenticated;

public class HomeView extends View {
    private AnchorPane anchorPane;
    private Button home;
    private Button signIn;
    private Button signOut;
    private Button exitButton;

    public HomeView() {
        anchorPane = new AnchorPane();
        home = new Button();
        signIn = new Button();
        signOut = new Button();
        exitButton = new Button();

        setPrefHeight(600.0);
        setPrefWidth(1000.0);
        getStyleClass().add("main");

        VBox.setVgrow(anchorPane, javafx.scene.layout.Priority.ALWAYS);
        anchorPane.setPrefWidth(1000.0);

        // Sign In Button
        signIn.setLayoutX(400.0);
        signIn.setLayoutY(450.0);
        signIn.setOnAction(HomeController::signIn);
        signIn.setPrefHeight(40.0);
        signIn.setPrefWidth(200.0);
        signIn.getStyleClass().add("button-secondary");
        signIn.setText("Sign In");

        // Sign Out Button
        signOut.setLayoutX(400.0);
        signOut.setLayoutY(375.0);
        signOut.setOnAction(AuthController::signOut);
        signOut.setPrefHeight(40.0);
        signOut.setPrefWidth(200.0);
        signOut.getStyleClass().add("button-danger");
        signOut.setText("Sign Out");

        // Exit Button
        exitButton.setLayoutX(400.0);
        exitButton.setLayoutY(525.0);
        exitButton.setOnAction(HomeController::exit);
        exitButton.setPrefHeight(40.0);
        exitButton.setPrefWidth(200.0);
        exitButton.getStyleClass().add("button-danger");
        exitButton.setText("Exit");

        // Home Button
        home.setLayoutX(400.0);
        home.setLayoutY(450.0);
        home.setOnAction(HomeController::self);
        home.setPrefHeight(40.0);
        home.setPrefWidth(200.0);
        home.getStyleClass().add("button-primary");
        home.setText("Home");

        // Add signIn or signOut based on authentication status
        if (isAuthenticated) {
            anchorPane.getChildren().remove(signIn);
            anchorPane.getChildren().add(signOut);
        } else {
            anchorPane.getChildren().remove(signOut);
            anchorPane.getChildren().add(signIn);
        }

        anchorPane.getChildren().add(exitButton);

        if (isAuthenticated) {
            anchorPane.getChildren().add(home);
        }

        getChildren().add(anchorPane);
    }
}
