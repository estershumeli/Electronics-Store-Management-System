package controller;

import view.AdminView;
import view.BalanceView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;

public class BalanceController {
    public static void back(ActionEvent actionEvent) {
        Node thisNode = (Node) actionEvent.getSource();
        Stage stage = (Stage) thisNode.getScene().getWindow();
        AdminView view = new AdminView();
        Scene scene = new Scene(view, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void search(ActionEvent actionEvent) {
        Node thisNode = (Node) actionEvent.getSource();
        Stage stage = (Stage) thisNode.getScene().getWindow();
        BalanceView thisView = (BalanceView) thisNode.getScene().getRoot();
        LocalDate fromDate = thisView.getFromDate().getValue();
        LocalDate toDate = thisView.getToDate().getValue();

        BalanceView view = new BalanceView(true, fromDate, toDate);
        Scene scene = new Scene(view, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }
}
