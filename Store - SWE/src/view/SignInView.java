package view;

import controller.HomeController;
import controller.AuthController;
import view.View;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class SignInView extends View {
    private AnchorPane anchorPane;
    private Button signInButton;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button back;

    public SignInView() {
        anchorPane = new AnchorPane();
        signInButton = new Button();
        usernameField = new TextField();
        passwordField = new PasswordField();
        back = new Button();

        setPrefHeight(600.0);
        setPrefWidth(1000.0);

        VBox.setVgrow(anchorPane, javafx.scene.layout.Priority.ALWAYS);
        anchorPane.setPrefWidth(1000.0);

        signInButton.setLayoutX(400.0);
        signInButton.setLayoutY(525.0);
        signInButton.setOnAction(AuthController::signIn);
        signInButton.setPrefHeight(40.0);
        signInButton.setPrefWidth(200.0);
        signInButton.getStyleClass().add("button-primary");
        signInButton.setText("Sign In");

        usernameField.setAlignment(javafx.geometry.Pos.CENTER);
        usernameField.setLayoutX(350.0);
        usernameField.setLayoutY(325.0);
        usernameField.setPrefHeight(50.0);
        usernameField.setPrefWidth(300.0);
        usernameField.setPromptText("Username");

        passwordField.setAlignment(javafx.geometry.Pos.CENTER);
        passwordField.setLayoutX(350.0);
        passwordField.setLayoutY(400.0);
        passwordField.setPrefHeight(50.0);
        passwordField.setPrefWidth(300.0);
        passwordField.setPromptText("Password");

        back.setLayoutX(14.0);
        back.setLayoutY(14.0);
        back.setOnAction(HomeController::home);
        back.setPrefHeight(40.0);
        back.setPrefWidth(200.0);
        back.getStyleClass().add("button-primary");
        back.setText("Back");

        anchorPane.getChildren().add(signInButton);
        anchorPane.getChildren().add(usernameField);
        anchorPane.getChildren().add(passwordField);
        anchorPane.getChildren().add(back);
        getChildren().add(anchorPane);
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(TextField usernameField) {
        this.usernameField = usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(PasswordField passwordField) {
        this.passwordField = passwordField;
    }
}
