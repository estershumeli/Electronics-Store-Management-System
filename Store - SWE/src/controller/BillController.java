package controller;

import service.BillService;
import view.BillView;
import view.ManagerView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;

import static util.Constant.appTitle;

public class BillController {
    private static final BillService billService;

    static {
        billService = new BillService();
    }

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
        BillView thisView = (BillView) thisNode.getScene().getRoot();
        LocalDate fromDate = thisView.getFromDate().getValue();
        LocalDate toDate = thisView.getToDate().getValue();

        BillView view = new BillView(true, fromDate, toDate);
        Scene scene = new Scene(view, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }
}
