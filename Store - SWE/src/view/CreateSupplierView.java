package view;

import controller.CreateSupplierController;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import static util.CashierUtil.fillComboBoxItemsOptions;

public class CreateSupplierView extends VBox {
    private AnchorPane anchorPane;
    private Button back;
    private TextField name;
    private Button create;
    private ComboBox items;
    private TextField quantity;

    public CreateSupplierView() {
        anchorPane = new AnchorPane();
        back = new Button();
        name = new TextField();
        create = new Button();
        items = new ComboBox();
        quantity = new TextField();

        fillComboBoxItemsOptions(items);

        setPrefHeight(600.0);
        setPrefWidth(1000.0);
        getStyleClass().add("main");

        VBox.setVgrow(anchorPane, javafx.scene.layout.Priority.ALWAYS);
        anchorPane.setPrefWidth(1000.0);

        back.setLayoutX(14.0);
        back.setLayoutY(14.0);
        
        back.setOnAction(CreateSupplierController::back);
        back.setPrefHeight(40.0);
        back.setPrefWidth(200.0);
        back.getStyleClass().add("button-primary");
        back.setText("Back");

        name.setAlignment(javafx.geometry.Pos.CENTER);
        name.setLayoutX(351.0);
        name.setLayoutY(115.0);
        name.setPrefWidth(300.0);
        name.setPromptText("Name");

        create.setLayoutX(400.0);
        create.setLayoutY(370.0);
        create.setOnAction(CreateSupplierController::create);
        create.setPrefHeight(40.0);
        create.setPrefWidth(200.0);
        create.getStyleClass().add("button-secondary");
        create.setText("Create");

        items.setLayoutX(350.0);
        items.setLayoutY(200.0);
        items.setPrefWidth(300.0);
        items.setPromptText("Item");

        quantity.setAlignment(javafx.geometry.Pos.CENTER);
        quantity.setLayoutX(351.0);
        quantity.setLayoutY(285.0);
        quantity.setPrefWidth(300.0);
        quantity.setPromptText("Quantity");

        anchorPane.getChildren().add(back);
        anchorPane.getChildren().add(name);
        anchorPane.getChildren().add(create);
        anchorPane.getChildren().add(items);
        anchorPane.getChildren().add(quantity);
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

    public Button getCreate() {
        return create;
    }

    public void setCreate(Button create) {
        this.create = create;
    }

    public ComboBox getItems() {
        return items;
    }

    public void setItems(ComboBox items) {
        this.items = items;
    }

    public TextField getQuantity() {
        return quantity;
    }

    public void setQuantity(TextField quantity) {
        this.quantity = quantity;
    }
}
