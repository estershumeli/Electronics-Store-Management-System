package view;

import controller.EditSupplierController;
import controller.SupplierController;
import model.Item;
import model.Supplier;
import service.SupplierService;
import view.View;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.List;

public class SupplierView extends View {
    private static final SupplierService supplierService;
    private static final int rowsPerPage;
    private static List<Supplier> suppliers;

    static {
        supplierService = new SupplierService();
        rowsPerPage = 5;
    }

    private AnchorPane anchorPane;
    private Button back;
    private TableView tableView;
    private TableColumn tableColumn;
    private TableColumn<Supplier, String> tableColumn0;
    private TableColumn tableColumn1;
    private TableColumn tableColumn2;
    private Label label;
    private Button add;
    private Pagination pagination;

    public SupplierView() {
        suppliers = supplierService.getAll();

        anchorPane = new AnchorPane();
        back = new Button();
        tableView = new TableView();
        tableColumn = new TableColumn();
        tableColumn0 = new TableColumn();
        tableColumn1 = new TableColumn();
        tableColumn2 = new TableColumn();
        label = new Label();
        add = new Button();
        pagination = new Pagination((suppliers.size() / rowsPerPage + 1), 0);
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
        
        back.setOnAction(SupplierController::back);
        back.setPrefHeight(40.0);
        back.setPrefWidth(200.0);
        back.getStyleClass().add("button-primary");
        back.setText("Back");

        tableView.setLayoutX(14.0);
        tableView.setLayoutY(168.0);
        tableView.setPrefHeight(350.0);
        tableView.setPrefWidth(970.0);

        tableColumn.setPrefWidth(300.0);
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumn.setText("Name");

        tableColumn0.setMinWidth(0.0);
        tableColumn0.setPrefWidth(370.0);
        tableColumn0.setCellValueFactory( data -> {
            Item item = data.getValue().getItem();
            String name = item.getName();
            return new ReadOnlyStringWrapper(name);
        });
        tableColumn0.setText("Supplier");

        tableColumn1.setMinWidth(0.0);
        tableColumn1.setPrefWidth(300.0);
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("itemQuantity"));
        tableColumn1.setText("Quantity");

        tableColumn2.setMinWidth(0.0);
        tableColumn2.setPrefWidth(170.0);
        tableColumn2.setText("Action");

        tableView.getColumns().add(tableColumn);
        tableView.getColumns().add(tableColumn0);
        tableView.getColumns().add(tableColumn1);
//        addButtonColumn();

        label.setLayoutX(442.0);
        label.setLayoutY(80.0);
        label.getStyleClass().add("font-secondary");
        label.setText("Suppliers");

        add.setLayoutX(786.0);
        add.setLayoutY(14.0);
        add.setOnAction(SupplierController::add);
        add.setPrefHeight(40.0);
        add.setPrefWidth(200.0);
        add.getStyleClass().add("button-secondary");
        add.setText("Add");

        anchorPane.getChildren().add(back);
        anchorPane.getChildren().add(tableView);
        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(add);
        anchorPane.getChildren().add(pagination);
        getChildren().add(anchorPane);
    }

    private Node createTable(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, suppliers.size());
        tableView.setItems(FXCollections.observableArrayList(suppliers.subList(fromIndex, toIndex)));

        return new AnchorPane();
    }

    private void addButtonColumn() {
        TableColumn<Supplier, Void> tableColumn2 = new TableColumn("Action");
        tableColumn2.setMinWidth(0.0);
        tableColumn2.setPrefWidth(170.0);

        Callback<TableColumn<Supplier, Void>, TableCell<Supplier, Void>> cellFactory = param -> {
            final TableCell<Supplier, Void> cell = new TableCell<>() {
                private final Button button = new Button("Delete");

                {
                    button.setPrefHeight(30);
                    button.setPrefWidth(160.0);
                    button.getStyleClass().add("button-danger");
                    button.setOnAction((ActionEvent actionEvent) -> {
                        Supplier supplier = getTableView().getItems().get(getIndex());
                        EditSupplierController.delete(actionEvent, supplier.getId());
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


        tableColumn2.setCellFactory(cellFactory);
        tableView.getColumns().add(tableColumn2);
    }
}
