package controller;

import service.CreateStaffService;
import view.CreateItemView;
import view.CreateStaffView;
import view.StaffView;
import view.SupplierView;
import view.View;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static util.Constant.appTitle;

public class CreateStaffController {
    private static final CreateStaffService createStaffService;

    static {
        createStaffService = new CreateStaffService();
    }

    public static void back(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        StaffView view = new StaffView();
        Scene scene = new Scene(view, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void create(ActionEvent actionEvent) {
        CreateStaffView view = (CreateStaffView) ((Node) actionEvent.getSource()).getScene().getRoot();
        boolean isCreated =  createStaffService.createUser(view);

        if (isCreated) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            View newView = new StaffView();
            Scene scene = new Scene(newView, 1000, 600);
            stage.setScene(scene);
            stage.show();
        }
    }
}
