package controller;

import service.StaffService;
import view.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static util.Constant.appTitle;

public class StaffController {
    private static final StaffService staffService;

    static {
        staffService = new StaffService();
    }

    public static void back(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        AdminView view = new AdminView();
        Scene scene = new Scene(view, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void add(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        CreateStaffView view = new CreateStaffView();
        Scene scene = new Scene(view, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void edit(ActionEvent actionEvent, String id) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        EditStaffView view = new EditStaffView(id);
        Scene scene = new Scene(view, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }
}
