package view;

import controller.CashierController;
import view.View;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import static util.CashierUtil.fillComboBoxItemsOptions;

public class CashierView extends View {
    private AnchorPane anchorPane;
    private TextArea billInformation;
    private ComboBox item;
    private TextField quantity;
    private Button addToBill;
    private Button checkOut;
    private Button removeLast;
    private Button back;

    public CashierView() {
        anchorPane = new AnchorPane();
        billInformation = new TextArea();
        item = new ComboBox();
        quantity = new TextField();
        addToBill = new Button();
        checkOut = new Button();
        removeLast = new Button();
        back = new Button();

        fillComboBoxItemsOptions(item);

        setPrefHeight(600.0);
        setPrefWidth(1000.0);
        getStyleClass().add("main");

        VBox.setVgrow(anchorPane, javafx.scene.layout.Priority.ALWAYS);
        anchorPane.setPrefWidth(1000.0);

        billInformation.setLayoutX(550.0);
        billInformation.setLayoutY(50.0);
        billInformation.setPrefHeight(500.0);
        billInformation.setPrefWidth(400.0);
        billInformation.setEditable(true);
        billInformation.setDisable(true);
        billInformation.setText("Total:    0.0\n");
        billInformation.setStyle("-fx-opacity: 1.0;");

        item.setLayoutX(14.0);
        item.setLayoutY(197.0);
        item.setPrefHeight(50.0);
        item.setPrefWidth(250.0);
        item.setPromptText("Item");

        quantity.setAlignment(javafx.geometry.Pos.CENTER);
        quantity.setLayoutX(14.0);
        quantity.setLayoutY(272.0);
        quantity.setPrefHeight(50.0);
        quantity.setPrefWidth(250.0);
        quantity.setPromptText("Quantity");

        addToBill.setLayoutX(330.0);
        addToBill.setLayoutY(425.0);
        addToBill.setOnAction(CashierController::addToBill);
        addToBill.setPrefHeight(40.0);
        addToBill.setPrefWidth(200.0);
        addToBill.getStyleClass().add("button-primary");
        addToBill.setText("Add To Bill");

        checkOut.setLayoutX(39.0);
        checkOut.setLayoutY(425.0);
        checkOut.setOnAction(CashierController::checkOut);
        checkOut.setPrefHeight(40.0);
        checkOut.setPrefWidth(200.0);
        checkOut.getStyleClass().add("button-secondary");
        checkOut.setText("Check Out");

        removeLast.setLayoutX(330.0);
        removeLast.setLayoutY(500.0);
        removeLast.setOnAction(CashierController::removeLast);
        removeLast.setPrefHeight(40.0);
        removeLast.setPrefWidth(200.0);
        removeLast.getStyleClass().add("button-danger");
        removeLast.setText("Remove Last");

        back.setLayoutX(14.0);
        back.setLayoutY(14.0);
        
        back.setOnAction(CashierController::back);
        back.setPrefHeight(40.0);
        back.setPrefWidth(200.0);
        back.getStyleClass().add("button-primary");
        back.setText("Back");

        anchorPane.getChildren().add(billInformation);
        anchorPane.getChildren().add(item);
        anchorPane.getChildren().add(quantity);
        anchorPane.getChildren().add(addToBill);
        anchorPane.getChildren().add(checkOut);
        anchorPane.getChildren().add(removeLast);
        anchorPane.getChildren().add(back);
        getChildren().add(anchorPane);
    }

    public ComboBox getItem() {
        return item;
    }

    public void setItem(ComboBox item) {
        this.item = item;
    }

    public TextField getQuantity() {
        return quantity;
    }

    public void setQuantity(TextField quantity) {
        this.quantity = quantity;
    }

    public TextArea getBillInformation() {
        return billInformation;
    }

    public void setBillInformation(TextArea billInformation) {
        this.billInformation = billInformation;
    }
}
