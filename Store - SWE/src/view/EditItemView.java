package view;

import controller.EditItemController;
import model.Item;
import model.Category;
import service.ItemService;
import service.CategoryService;
import view.View;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;

import static util.CreateItemViewUtil.fillComboBoxCategoriesOptions;

public class EditItemView extends View {
    private static final ItemService itemService;

    static {
    	itemService = new ItemService();
    }

    private AnchorPane anchorPane;
    private static final CategoryService catService;

    static {
    	catService = new CategoryService();
    }

    private Button back;
    private TextField name;
    private ComboBox<Category> categories;
    private TextField buyPrice;
    private TextField sellPrice;
    private Button update;
    private Button delete;
    private final String id;
    private final Item item;

    public EditItemView(String id) {
        this.id = id;
        this.item = itemService.findById(id);

        anchorPane = new AnchorPane();
        back = new Button();
        name = new TextField();
        categories = new ComboBox<>();
        buyPrice = new TextField();
        sellPrice = new TextField();
        update = new Button();
        delete = new Button();

        setPrefHeight(600.0);
        setPrefWidth(1000.0);
        getStyleClass().add("main");

        VBox.setVgrow(anchorPane, javafx.scene.layout.Priority.ALWAYS);
        anchorPane.setPrefWidth(1000.0);

        back.setLayoutX(14.0);
        back.setLayoutY(14.0);

        back.setOnAction(EditItemController::back);
        back.setPrefHeight(40.0);
        back.setPrefWidth(200.0);
        back.getStyleClass().add("button-primary");
        back.setText("Back");

        name.setAlignment(javafx.geometry.Pos.CENTER);
        name.setLayoutX(350.0);
        name.setLayoutY(115.0);
        name.setPrefWidth(300.0);
        name.setPromptText("Name");
        name.setText(item.getName());
        name.setDisable(true);
        name.setStyle("-fx-opacity: 0.75;");

        categories.setLayoutX(350.0);
        categories.setLayoutY(200.0);
        categories.setPrefWidth(300.0);
        categories.setPromptText("Category");
        fillComboBoxCategoriesOptions(categories);
        Callback<ListView<Category>, ListCell<Category>> factory = lv -> new ListCell<>() {
            @Override
            protected void updateItem(Category cat, boolean empty) {
                super.updateItem(cat, empty);
                if (cat == null || empty) {
                    setGraphic(null);
                } else {
                    setText(cat.getName());
                }
            }
        };
        categories.setConverter(new StringConverter<>() {
            @Override
            public String toString(Category cat) {
                return cat.getName();
            }

            @Override
            public Category fromString(String name) {
                return catService.findCategoryByName(name);
            }
        });
        categories.setValue(item.getCategory());
        categories.setCellFactory(factory);
        categories.setDisable(true);
        categories.setStyle("-fx-opacity: 1.0;");

        buyPrice.setAlignment(javafx.geometry.Pos.CENTER);
        buyPrice.setLayoutX(350.0);
        buyPrice.setLayoutY(285.0);
        buyPrice.setPrefWidth(300.0);
        buyPrice.setPromptText("Buy Price");
        buyPrice.setText(String.valueOf(item.getBuyPrice()));

        sellPrice.setAlignment(javafx.geometry.Pos.CENTER);
        sellPrice.setLayoutX(350.0);
        sellPrice.setLayoutY(370.0);
        sellPrice.setPrefWidth(300.0);
        sellPrice.setPromptText("Sell Price");
        sellPrice.setText(String.valueOf(item.getSellPrice()));

        update.setLayoutX(400.0);
        update.setLayoutY(455);
        update.setOnAction(e -> EditItemController.update(e, this.id));
        update.setPrefHeight(40.0);
        update.setPrefWidth(200.0);
        update.getStyleClass().add("button-secondary");
        update.setText("Update");

        delete.setLayoutX(400.0);
        delete.setLayoutY(530);
        delete.setOnAction(e -> EditItemController.delete(e, this.id));
        delete.setPrefHeight(40.0);
        delete.setPrefWidth(200.0);
        delete.getStyleClass().add("button-danger");
        delete.setText("Delete");

        anchorPane.getChildren().add(back);
        anchorPane.getChildren().add(name);
        anchorPane.getChildren().add(categories);
        anchorPane.getChildren().add(buyPrice);
        anchorPane.getChildren().add(sellPrice);
        anchorPane.getChildren().add(update);
        anchorPane.getChildren().add(delete);
        getChildren().add(anchorPane);
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public Button getBack() {
        return back;
    }

    public void setBack(Button back) {
        this.back = back;
    }

    public TextField getName() {
        return name;
    }

    public void setName(TextField name) {
        this.name = name;
    }

    public ComboBox<Category> getCategories() {
        return categories;
    }

    public void setCategories(ComboBox<Category> categories) {
        this.categories = categories;
    }

    public TextField getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(TextField buyPrice) {
        this.buyPrice = buyPrice;
    }

    public TextField getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(TextField sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Button getUpdate() {
        return update;
    }

    public void setUpdate(Button update) {
        this.update = update;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }
}
