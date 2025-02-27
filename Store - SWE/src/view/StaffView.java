package view;

import controller.StaffController;
import model.Employee;
import service.StaffService;
import view.View;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.List;

public class StaffView extends View {
    private static final StaffService staffService;
    private static final int rowsPerPage;
    private static List<Employee> employees;

    static {
        staffService = new StaffService();
        rowsPerPage = 5;
    }

    private AnchorPane anchorPane;
    private Button back;
    private Button add;
    private TableView tableView;
    private TableColumn<Employee, Void> tableColumn5;
    private Label label;
    private Pagination pagination;

    public StaffView() {
        employees = staffService.getAllEmployees();

        anchorPane = new AnchorPane();
        back = new Button();
        add = new Button();
        tableView = new TableView();
        TableColumn tableColumn = new TableColumn();
        TableColumn tableColumn0 = new TableColumn();
        TableColumn tableColumn1 = new TableColumn();
        TableColumn tableColumn2 = new TableColumn();
        TableColumn tableColumn3 = new TableColumn();
        TableColumn tableColumn4 = new TableColumn();
        tableColumn5 = new TableColumn<>();
        label = new Label();
        pagination = new Pagination((employees.size() / rowsPerPage + 1), 0);
        pagination.setPageFactory(this::createTable);
        pagination.setLayoutX(15.0);
        pagination.setLayoutY(530.0);

        setPrefHeight(600.0);
        setPrefWidth(1000.0);
        getStyleClass().add("main");

        VBox.setVgrow(anchorPane, javafx.scene.layout.Priority.ALWAYS);
        anchorPane.setPrefWidth(1000.0);

        back.setLayoutX(14.0);
        back.setLayoutY(14.0);
        
        back.setOnAction(StaffController::back);
        back.setPrefHeight(40.0);
        back.setPrefWidth(200.0);
        back.getStyleClass().add("button-primary");
        back.setText("Back");

        add.setLayoutX(789.0);
        add.setLayoutY(14.0);
        add.setOnAction(StaffController::add);
        add.setPrefHeight(40.0);
        add.setPrefWidth(200.0);
        add.getStyleClass().add("button-secondary");
        add.setText("Add");

        tableView.setLayoutX(14.0);
        tableView.setLayoutY(168.0);
        tableView.setPrefHeight(350.0);
        tableView.setPrefWidth(970.0);

        tableColumn.setPrefWidth(151.0);
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumn.setText("Name");

        tableColumn0.setPrefWidth(152.0);
        tableColumn0.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        tableColumn0.setText("Birthday");

        tableColumn1.setPrefWidth(142.0);
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tableColumn1.setText("Phone");

        tableColumn2.setPrefWidth(149.0);
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableColumn2.setText("Email");

        tableColumn3.setPrefWidth(153.0);
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("salary"));
        tableColumn3.setText("Salary");

        tableColumn4.setPrefWidth(113.0);
        tableColumn4.setCellValueFactory(new PropertyValueFactory<>("role"));
        tableColumn4.setText("Role");

        tableView.getColumns().add(tableColumn);
        tableView.getColumns().add(tableColumn0);
        tableView.getColumns().add(tableColumn1);
        tableView.getColumns().add(tableColumn2);
        tableView.getColumns().add(tableColumn3);
        tableView.getColumns().add(tableColumn4);
        addButtonColumn();

        label.setLayoutX(385.0);
        label.setLayoutY(78.0);
        label.getStyleClass().add("font-secondary");
        label.setText("Users");

        anchorPane.getChildren().add(back);
        anchorPane.getChildren().add(tableView);
        anchorPane.getChildren().add(pagination);
        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(add);
        getChildren().add(anchorPane);
    }

    private Node createTable(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, employees.size());
        tableView.setItems(FXCollections.observableArrayList(employees.subList(fromIndex, toIndex)));
        tableView.getColumns().remove(tableColumn5);
        addButtonColumn();
        return new AnchorPane();
    }

    private void addButtonColumn() {
        tableColumn5 = new TableColumn("Action");
        tableColumn5.setMinWidth(0.0);
        tableColumn5.setPrefWidth(109.0);

        Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>> cellFactory = param -> {
            final TableCell<Employee, Void> cell = new TableCell<>() {
                private final Button button = new Button("Edit");

                {
                    button.setPrefHeight(30);
                    button.setPrefWidth(80.0);
                    button.getStyleClass().add("button-primary");
//                    FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
//                    icon.setStyle("-fx-fill: #2be4ea;");
//                    icon.setSize("1.5em");
//                    button.setGraphic(icon);
                    button.setOnAction((ActionEvent actionEvent) -> {
                        Employee employee = getTableView().getItems().get(getIndex());
                        StaffController.edit(actionEvent, employee.getId());
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(button);
                    }
                }
            };

            return cell;
        };

        tableColumn5.setCellFactory(cellFactory);
        tableView.getColumns().add(tableColumn5);
    }
}
