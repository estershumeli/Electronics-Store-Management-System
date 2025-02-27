package view;

import controller.AdminController;
import view.View;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class AdminView extends View {
    private AnchorPane anchorPane;
    private Button back;
    private Button store;
    private Button staff;
    private Button billing;
    private Button balance;

    public AdminView() {
        anchorPane = new AnchorPane();
        back = new Button();
        store = new Button();
        staff = new Button();
        billing = new Button();
        balance = new Button();

        setPrefHeight(600.0);
        setPrefWidth(1000.0);
        getStyleClass().add("main");

        VBox.setVgrow(anchorPane, javafx.scene.layout.Priority.ALWAYS);
        anchorPane.setPrefWidth(1000.0);

        back.setLayoutX(14.0);
        back.setLayoutY(14.0);
        
        back.setOnAction(AdminController::back);
        back.setPrefHeight(40.0);
        back.setPrefWidth(200.0);
        back.getStyleClass().add("button-primary");
        back.setText("Back");

        balance.setLayoutX(400.0);
        balance.setLayoutY(300.0);
        balance.setOnAction(AdminController::balance);
        balance.setPrefHeight(40.0);
        balance.setPrefWidth(200.0);
        balance.getStyleClass().add("button-primary");
        balance.setText("Balance");

        billing.setLayoutX(400.0);
        billing.setLayoutY(375.0);
        billing.setOnAction(AdminController::cashier);
        billing.setPrefHeight(40.0);
        billing.setPrefWidth(200.0);
        billing.getStyleClass().add("button-primary");
        billing.setText("Billing");

        store.setLayoutX(400.0);
        store.setLayoutY(450.0);
        store.setOnAction(AdminController::manager);
        store.setPrefHeight(40.0);
        store.setPrefWidth(200.0);
        store.getStyleClass().add("button-primary");
        store.setText("Store");

        staff.setLayoutX(400.0);
        staff.setLayoutY(525.0);
        staff.setOnAction(AdminController::staff);
        staff.setPrefHeight(40.0);
        staff.setPrefWidth(200.0);
        staff.getStyleClass().add("button-primary");
        staff.setText("Staff");

        anchorPane.getChildren().add(back);
        anchorPane.getChildren().add(balance);
        anchorPane.getChildren().add(store);
        anchorPane.getChildren().add(staff);
        anchorPane.getChildren().add(billing);
        getChildren().add(anchorPane);
    }
}
