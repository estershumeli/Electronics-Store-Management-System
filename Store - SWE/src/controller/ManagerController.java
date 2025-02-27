package controller;

import util.Role;
import view.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ManagerController {
	
    public static void item(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        ItemView view = new ItemView();
        Scene scene = new Scene(view, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void statistics(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        StatisticsView view = new StatisticsView(false, null, null);
        Scene scene = new Scene(view, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void supplier(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SupplierView view = new SupplierView();
        Scene scene = new Scene(view, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void bill(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        BillView view = new BillView(false, null, null);
        Scene scene = new Scene(view, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void back(ActionEvent actionEvent) {
        if (HomeController.getUser().getRole() == Role.ADMIN) {
            Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            AdminView view = new AdminView();
            Scene scene = new Scene(view, 1000, 600);
            stage.setScene(scene);
            stage.show();
        } else {
            HomeController.home(actionEvent);
        }
    }
}
