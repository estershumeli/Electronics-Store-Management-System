package start;

import view.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static util.Constant.appTitle;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        HomeView view = new HomeView();
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