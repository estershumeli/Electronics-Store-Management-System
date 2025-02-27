package view;

import controller.ItemController;
import model.Item;
import model.Category;
import service.ItemService;
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

import java.util.ArrayList;
import java.util.List;

public class ItemView extends View {
    private static final ItemService itemsService;
    private static final int rowsPerPage;
    private static List<Item> items;

    static {
    	itemsService = new ItemService();
        rowsPerPage = 5;
    }

    private AnchorPane anchorPane;
    private TableView tableView = new TableView();
    TableColumn<Item, Void> tableColumn5;
    private Button back;
    private Label label;
    private Button add;
    private Button categories;
    private Pagination pagination;

    public ItemView() {
    	items = new ArrayList<>();
        items.addAll(itemsService.findAll());

        anchorPane = new AnchorPane();
        back = new Button();
        label = new Label();
        add = new Button();
        categories = new Button();
        pagination = new Pagination((items.size() / rowsPerPage + 1), 0);
        pagination.setPageFactory(this::createTable);

        setPrefHeight(600.0);
        setPrefWidth(1000.0);
        getStyleClass().add("main");
        VBox.setVgrow(anchorPane, javafx.scene.layout.Priority.ALWAYS);
        anchorPane.setPrefWidth(1000.0);

        back.setLayoutX(14.0);
        back.setLayoutY(14.0);
        
        back.setOnAction(ItemController::back);
        back.setPrefHeight(40.0);
        back.setPrefWidth(200.0);
        back.getStyleClass().add("button-primary");
        back.setText("Back");

        TableColumn tableColumn = new TableColumn<>();
        TableColumn<Item, String> tableColumn0 = new TableColumn<>();
        TableColumn tableColumn1 = new TableColumn();
        TableColumn tableColumn2 = new TableColumn();
        TableColumn tableColumn3 = new TableColumn();
        TableColumn tableColumn4 = new TableColumn();

        tableColumn.setPrefWidth(130.0);
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumn.setText("Name");
        tableColumn0.setMinWidth(0.0);
        tableColumn0.setPrefWidth(139.0);
        tableColumn0.setCellValueFactory(data -> {
            Category cat = data.getValue().getCategory();
            String name = cat != null ? cat.getName() : "Unknown";
            return new ReadOnlyStringWrapper(name);
        });
        tableColumn0.setText("Category");

        tableColumn1.setMinWidth(0.0);
        tableColumn1.setPrefWidth(140.0);
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("buyPrice"));
        tableColumn1.setText("Buy Price");

        tableColumn2.setMinWidth(0.0);
        tableColumn2.setPrefWidth(153.0);
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("boughtQuantity"));
        tableColumn2.setText("Bought");

        tableColumn3.setMinWidth(0.0);
        tableColumn3.setPrefWidth(167.0);
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        tableColumn3.setText("Sell Price");

        tableColumn4.setMinWidth(0.0);
        tableColumn4.setPrefWidth(147.0);
        tableColumn4.setCellValueFactory(new PropertyValueFactory<>("soldQuantity"));
        tableColumn4.setText("Sold");

        tableView.getColumns().addAll(tableColumn, tableColumn0, tableColumn1, tableColumn2, tableColumn3, tableColumn4);
        addButtonColumn();

        tableView.setLayoutX(15.0);
        tableView.setLayoutY(180.0);
        tableView.setPrefHeight(350.0);
        tableView.setPrefWidth(970.0);

        pagination.setLayoutX(15.0);
        pagination.setLayoutY(530.0);

        label.setLayoutX(442.0);
        label.setLayoutY(80.0);
        label.getStyleClass().add("font-secondary");
        label.setText("Items");

        add.setLayoutX(789.0);
        add.setLayoutY(14.0);
        add.setOnAction(ItemController::add);
        add.setPrefHeight(40.0);
        add.setPrefWidth(200.0);
        add.getStyleClass().add("button-secondary");
        add.setText("Add");

        categories.setLayoutX(789.0);
        categories.setLayoutY(75.0);
        categories.setOnAction(ItemController::categories);
        categories.setPrefHeight(40.0);
        categories.setPrefWidth(200.0);
        categories.getStyleClass().add("button-primary");
        categories.setText("Categories");

        anchorPane.getChildren().add(back);
        anchorPane.getChildren().add(tableView);
        anchorPane.getChildren().add(pagination);
        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(add);
        anchorPane.getChildren().add(categories);
        getChildren().add(anchorPane);
    }

    private Node createTable(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, items.size());
        tableView.setItems(FXCollections.observableArrayList(items.subList(fromIndex, toIndex)));
        tableView.getColumns().remove(tableColumn5);
        addButtonColumn();
        return new AnchorPane();
    }

    private void addButtonColumn() {
        tableColumn5 = new TableColumn("Action");
        tableColumn5.setMinWidth(0.0);
        tableColumn5.setPrefWidth(93.0);

        Callback<TableColumn<Item, Void>, TableCell<Item, Void>> cellFactory = param -> {
            final TableCell<Item, Void> cell = new TableCell<>() {
                private final Button button = new Button("Edit");

                {
                    button.setPrefHeight(30);
                    button.setPrefWidth(80.0);
                    button.getStyleClass().add("button-primary");
                    button.setOnAction((ActionEvent actionEvent) -> {
                        Item item = getTableView().getItems().get(getIndex());
                        ItemController.edit(actionEvent, item.getId());
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
