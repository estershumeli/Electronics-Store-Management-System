package view;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import static util.Constant.appTitle;

public class TestApplication extends javafx.application.Application {
    private static class TestView extends VBox {
        private AnchorPane anchorPane;
        private TextField username;
        private PasswordField passwordField;
        private Button signIn;

        public TestView() {
            anchorPane = new AnchorPane();
            username = new TextField();
            passwordField = new PasswordField();
            signIn = new Button();
            signIn.setOnAction(e -> {
                Scene scene = ((Node) e.getSource()).getScene();
                Parent root;
                Stage stage = (Stage) scene.getWindow();

                root = new TestView();
                scene.setRoot(root);
            });
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        TestView view = new TestView();
        Scene scene = new Scene(view, 1000, 600);
        stage.setTitle(appTitle);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }
}

