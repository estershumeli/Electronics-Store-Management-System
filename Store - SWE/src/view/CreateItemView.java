package view;

import controller.CreateItemController;
import model.Category;
import service.CategoryService;
import view.View;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;

import static util.CreateItemViewUtil.fillComboBoxCategoriesOptions;

public class CreateItemView extends View {
    private static final CategoryService catService;

    static {
    	catService = new CategoryService();
    }

    private AnchorPane anchorPane;
    private Button back;
    private TextField name;
    private ComboBox<Category> categories;
    private TextField buyPrice;
    private TextField sellPrice;
    private Button create;

    public CreateItemView() {
        anchorPane = new AnchorPane();
        back = new Button();
        name = new TextField();
        categories = new ComboBox<>();
        buyPrice = new TextField();
        sellPrice = new TextField();
        create = new Button();

        setPrefHeight(600.0);
        setPrefWidth(1000.0);
        getStyleClass().add("main");

        VBox.setVgrow(anchorPane, javafx.scene.layout.Priority.ALWAYS);
        anchorPane.setPrefWidth(1000.0);

        back.setLayoutX(14.0);
        back.setLayoutY(14.0);
        
        back.setOnAction(CreateItemController::back);
        back.setPrefHeight(40.0);
        back.setPrefWidth(200.0);
        back.getStyleClass().add("button-primary");
        back.setText("Back");

        name.setAlignment(javafx.geometry.Pos.CENTER);
        name.setLayoutX(351.0);
        name.setLayoutY(115.0);
        name.setPrefWidth(300.0);
        name.setPromptText("Name");

        categories.setLayoutX(351.0);
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
        categories.setCellFactory(factory);


        buyPrice.setAlignment(javafx.geometry.Pos.CENTER);
        buyPrice.setLayoutX(351.0);
        buyPrice.setLayoutY(285.0);
        buyPrice.setPrefWidth(300.0);
        buyPrice.setPromptText("Buy Price");

        sellPrice.setAlignment(javafx.geometry.Pos.CENTER);
        sellPrice.setLayoutX(350.0);
        sellPrice.setLayoutY(370.0);
        sellPrice.setPrefWidth(300.0);
        sellPrice.setPromptText("Sell Price");

        create.setLayoutX(400.0);
        create.setLayoutY(470.0);
        create.setOnAction(CreateItemController::create);
        create.setPrefHeight(40.0);
        create.setPrefWidth(200.0);
        create.getStyleClass().add("button-secondary");
        create.setText("Create");

        anchorPane.getChildren().add(back);
        anchorPane.getChildren().add(name);
        anchorPane.getChildren().add(categories);
        anchorPane.getChildren().add(buyPrice);
        anchorPane.getChildren().add(sellPrice);
        anchorPane.getChildren().add(create);
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

    public Button getCreate() {
        return create;
    }

    public void setCreate(Button create) {
        this.create = create;
    }
}
