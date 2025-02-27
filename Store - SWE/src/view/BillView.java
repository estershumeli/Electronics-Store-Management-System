package view;

import controller.BillController;
import statistic.CashierStatistic;
import service.BillService;
import view.View;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.List;

public class BillView extends View {
    private static final BillService billService;
    private static final int rowsPerPage;
    private static List<CashierStatistic> cashierStatistics;

    static {
        billService = new BillService();
        rowsPerPage = 5;
    }

    private AnchorPane anchorPane;
    private Button back;
    private Button search;
    private TableView tableView;
    private TableColumn tableColumn;
    private TableColumn tableColumn0;
    private TableColumn tableColumn1;
    private TableColumn tableColumn2;
    private DatePicker fromDate;
    private DatePicker toDate;
    private Label cashiersLabel;
    private Pagination pagination;

    public BillView(boolean searching, LocalDate fromDate, LocalDate toDate) {
        anchorPane = new AnchorPane();
        back = new Button();
        search = new Button();
        tableView = new TableView();
        tableColumn = new TableColumn();
        tableColumn0 = new TableColumn();
        tableColumn1 = new TableColumn();
        tableColumn2 = new TableColumn();
        this.fromDate = new DatePicker();
        this.toDate = new DatePicker();
        cashiersLabel = new Label();

        setPrefHeight(600.0);
        setPrefWidth(1000.0);
        getStyleClass().add("main");

        VBox.setVgrow(anchorPane, javafx.scene.layout.Priority.ALWAYS);
        anchorPane.setPrefWidth(1000.0);

        back.setLayoutX(14.0);
        back.setLayoutY(14.0);

        back.setOnAction(BillController::back);
        back.setPrefHeight(40.0);
        back.setPrefWidth(200.0);
        back.getStyleClass().add("button-primary");
        back.setText("Back");

        search.setLayoutX(750.0);
        search.setLayoutY(100.0);

        search.setOnAction(BillController::search);
        search.setPrefHeight(40.0);
        search.setPrefWidth(200.0);
        search.getStyleClass().add("button-secondary");
        search.setText("Search");

        tableView.setLayoutX(15.0);
        tableView.setLayoutY(166.0);
        tableView.setPrefHeight(350.0);
        tableView.setPrefWidth(970.0);

        this.fromDate.setLayoutX(500);
        this.fromDate.setLayoutY(14.0);
        this.fromDate.setPrefWidth(200.0);
        this.fromDate.setPromptText("From Date");
        this.fromDate.setValue(fromDate);

        this.toDate.setLayoutX(750);
        this.toDate.setLayoutY(14.0);
        this.toDate.setPrefWidth(200.0);
        this.toDate.setPromptText("To Date");
        this.toDate.setValue(toDate);

        if (searching) {
            LocalDate fromDateValue = this.fromDate.getValue();
            LocalDate toDateValue = this.toDate.getValue();

            cashierStatistics = billService.search(fromDateValue, toDateValue);

            this.fromDate.setValue(null);
            this.toDate.setValue(null);
        } else {
            cashierStatistics = billService.getAll();
        }

        tableColumn.setPrefWidth(231.0);
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("issuer"));
        tableColumn.setText("Name");

        tableColumn0.setMinWidth(0.0);
        tableColumn0.setPrefWidth(232.0);
        tableColumn0.setCellValueFactory(new PropertyValueFactory<>("bills"));
        tableColumn0.setText("Billls Issued");

        tableColumn1.setMinWidth(0.0);
        tableColumn1.setPrefWidth(221.0);
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("items"));
        tableColumn1.setText("Items Sold");

        tableColumn2.setMinWidth(0.0);
        tableColumn2.setPrefWidth(285.0);
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("profit"));
        tableColumn2.setText("Profit");

        tableView.getColumns().add(tableColumn);
        tableView.getColumns().add(tableColumn0);
        tableView.getColumns().add(tableColumn1);
        tableView.getColumns().add(tableColumn2);

        cashiersLabel.setLayoutX(442.0);
        cashiersLabel.setLayoutY(80.0);
        cashiersLabel.getStyleClass().add("font-secondary");
        cashiersLabel.setText("Cashiers");

        pagination = new Pagination((cashierStatistics.size() / rowsPerPage + 1), 0);
        pagination.setPageFactory(this::createTable);
        pagination.setLayoutX(15.0);
        pagination.setLayoutY(530.0);

        anchorPane.getChildren().add(back);
        anchorPane.getChildren().add(tableView);
        anchorPane.getChildren().add(cashiersLabel);
        anchorPane.getChildren().add(pagination);
        anchorPane.getChildren().add(this.fromDate);
        anchorPane.getChildren().add(this.toDate);
        anchorPane.getChildren().add(search);
        getChildren().add(anchorPane);
    }

    private Node createTable(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, cashierStatistics.size());
        tableView.setItems(FXCollections.observableArrayList(cashierStatistics.subList(fromIndex, toIndex)));

        return new AnchorPane();
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

    public static List<CashierStatistic> getCashierStatistics() {
        return cashierStatistics;
    }

    public static void setCashierStatistics(List<CashierStatistic> cashierStatistics) {
        BillView.cashierStatistics = cashierStatistics;
    }
}
