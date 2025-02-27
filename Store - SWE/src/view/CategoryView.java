package view;

import controller.EditCategoryController;
import controller.CategoryController;
import model.Category;
import service.CategoryService;
import view.View;
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

public class CategoryView extends View {
    private static final CategoryService catService;
    private static final int rowsPerPage;
    private static List<Category> categories;

    static {
    	catService = new CategoryService();
        rowsPerPage = 5;
    }

    private AnchorPane anchorPane;
    private TableView tableView = new TableView();
    TableColumn<Category, Void> tableColumn1;
    private Button back;
    private Label label;
    private Button add;
    private Pagination pagination;

    public CategoryView() {
    	categories = new ArrayList<>();
        categories.addAll(catService.findAll());

        anchorPane = new AnchorPane();
        back = new Button();
        label = new Label();
        add = new Button();
        pagination = new Pagination((categories.size() / rowsPerPage + 1), 0);
        pagination.setPageFactory(this::createTable);

        setPrefHeight(600.0);
        setPrefWidth(1000.0);
        getStyleClass().add("main");

        VBox.setVgrow(anchorPane, javafx.scene.layout.Priority.ALWAYS);
        anchorPane.setPrefWidth(1000.0);

        back.setLayoutX(14.0);
        back.setLayoutY(14.0);

        back.setOnAction(CategoryController::back);
        back.setPrefHeight(40.0);
        back.setPrefWidth(200.0);
        back.getStyleClass().add("button-primary");
        back.setText("Back");

        TableColumn tableColumn = new TableColumn<>();

        tableColumn.setPrefWidth(500.0);
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumn.setText("Name");

        tableView.getColumns().addAll(tableColumn);
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
        label.setText("Categories");

        add.setLayoutX(789.0);
        add.setLayoutY(14.0);
        add.setOnAction(CategoryController::add);
        add.setPrefHeight(40.0);
        add.setPrefWidth(200.0);
        add.getStyleClass().add("button-secondary");
        add.setText("Add");

        anchorPane.getChildren().add(back);
        anchorPane.getChildren().add(tableView);
        anchorPane.getChildren().add(pagination);
        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(add);
        getChildren().add(anchorPane);
    }

    private Node createTable(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, categories.size());
        tableView.setItems(FXCollections.observableArrayList(categories.subList(fromIndex, toIndex)));
        tableView.getColumns().remove(tableColumn1);
        addButtonColumn();
        return new AnchorPane();
    }

    private void addButtonColumn() {
        tableColumn1 = new TableColumn("Action");
        tableColumn1.setMinWidth(0.0);
        tableColumn1.setPrefWidth(470.0);

        Callback<TableColumn<Category, Void>, TableCell<Category, Void>> cellFactory = param -> {
            final TableCell<Category, Void> cell = new TableCell<>() {
                private final Button button = new Button("Delete");

                {
                    button.setPrefHeight(30);
                    button.setPrefWidth(100.0);
                    button.getStyleClass().add("button-danger");
                    button.setOnAction((ActionEvent actionEvent) -> {
                        Category genre = getTableView().getItems().get(getIndex());
                        EditCategoryController.delete(actionEvent, genre.getId());
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


        tableColumn1.setCellFactory(cellFactory);
        tableView.getColumns().add(tableColumn1);
    }
}
