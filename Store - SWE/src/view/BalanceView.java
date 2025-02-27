package view;

import controller.BalanceController;
import statistic.BalanceStatistic;
import view.View;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.List;

import static util.BalanceStatisticUtil.*;

public class BalanceView extends View {
    private static List<BalanceStatistic> balanceStatistics;

    private AnchorPane anchorPane;
    private Button back;
    private Button search;
    private TableView tableView;
    private TableColumn tableColumn;
    private TableColumn tableColumn0;
    private DatePicker fromDate;
    private DatePicker toDate;

    public BalanceView(boolean searching, LocalDate fromDate, LocalDate toDate) {
        anchorPane = new AnchorPane();
        back = new Button();
        search = new Button();
        tableView = new TableView();
        tableColumn = new TableColumn();
        tableColumn0 = new TableColumn();
        this.fromDate = new DatePicker();
        this.toDate = new DatePicker();

        setPrefHeight(600.0);
        setPrefWidth(1000.0);
        getStyleClass().add("main");

        VBox.setVgrow(anchorPane, javafx.scene.layout.Priority.ALWAYS);
        anchorPane.setPrefWidth(1000.0);

        back.setLayoutX(14.0);
        back.setLayoutY(14.0);

        back.setOnAction(BalanceController::back);
        back.setPrefHeight(40.0);
        back.setPrefWidth(200.0);
        back.getStyleClass().add("button-primary");
        back.setText("Back");

        search.setLayoutX(400.0);
        search.setLayoutY(476.0);
        search.setOnAction(BalanceController::search);
        search.setPrefHeight(40.0);
        search.setPrefWidth(200.0);
        search.getStyleClass().add("button-secondary");
        search.setText("Search");

        tableView.setLayoutX(300.0);
        tableView.setLayoutY(166.0);
        tableView.setPrefHeight(210.0);
        tableView.setPrefWidth(410.0);

        this.fromDate.setLayoutX(250.0);
        this.fromDate.setLayoutY(100.0);
        this.fromDate.setPrefWidth(200.0);
        this.fromDate.setPromptText("From Date");
        this.fromDate.setValue(fromDate);

        this.toDate.setLayoutX(550.0);
        this.toDate.setLayoutY(100.0);
        this.toDate.setPrefWidth(200.0);
        this.toDate.setPromptText("To Date");
        this.toDate.setValue(toDate);

        if (searching) {
            LocalDate fromDateValue = this.fromDate.getValue();
            LocalDate toDateValue = this.toDate.getValue();

            balanceStatistics = search(fromDateValue, toDateValue);

            this.fromDate.setValue(null);
            this.toDate.setValue(null);
        } else {
            balanceStatistics = search(null, null);
        }

        tableColumn.setPrefWidth(200.0);
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("source"));
        tableColumn.setText("Source");

        tableColumn0.setMinWidth(0.0);
        tableColumn0.setPrefWidth(200.0);
        tableColumn0.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tableColumn0.setText("Amount");

        tableView.getColumns().addAll(tableColumn, tableColumn0);
        tableView.setItems(FXCollections.observableArrayList(balanceStatistics));

        anchorPane.getChildren().add(back);
        anchorPane.getChildren().add(tableView);
        anchorPane.getChildren().add(this.fromDate);
        anchorPane.getChildren().add(this.toDate);
        anchorPane.getChildren().add(search);
        getChildren().add(anchorPane);
    }

    public DatePicker getFromDate() {
        return fromDate;
    }

    public void setFromDate(DatePicker fromDate) {
        this.fromDate = fromDate;
    }

    public DatePicker getToDate() {
        return toDate;
    }

    public void setToDate(DatePicker toDate) {
        this.toDate = toDate;
    }
}
