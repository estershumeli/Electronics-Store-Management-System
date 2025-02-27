package controller;

import service.StaffService;
import view.EditStaffView;
import view.StaffView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditStaffController {
    private static final StaffService staffService;

    static {
        staffService = new StaffService();
    }

    public static void back(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        StaffView view = new StaffView();
        Scene scene = new Scene(view, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void update(ActionEvent actionEvent, String id) {
        EditStaffView view = (EditStaffView) ((Node) actionEvent.getSource()).getScene().getRoot();

        boolean updated = staffService.update(view, id);

        if (updated) {
            back(actionEvent);
        }
    }

    public static void delete(ActionEvent actionEvent, String id) {
        boolean deleted = staffService.delete(id);

        if (deleted) {
            back(actionEvent);
        }
    }
}
