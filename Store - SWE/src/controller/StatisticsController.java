package controller;

import view.BalanceView;
import view.ManagerView;
import view.StatisticsView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;

public class StatisticsController {
    public static void back(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ManagerView view = new ManagerView();
        Scene scene = new Scene(view, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void search(ActionEvent actionEvent) {
        Node thisNode = (Node) actionEvent.getSource();
        Stage stage = (Stage) thisNode.getScene().getWindow();
        StatisticsView thisView = (StatisticsView) thisNode.getScene().getRoot();
        LocalDate fromDate = thisView.getFromDatePicker().getValue();
        LocalDate toDate = thisView.getToDatePicker().getValue();

        StatisticsView view = new StatisticsView(true, fromDate, toDate);
        Scene scene = new Scene(view, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }
}
