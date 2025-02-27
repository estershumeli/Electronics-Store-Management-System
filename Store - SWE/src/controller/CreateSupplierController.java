package controller;

import model.Item;
import model.Supplier;
import service.CreateSupplierService;
import view.CreateSupplierView;
import view.SupplierView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateSupplierController {
    private static final CreateSupplierService createSupplierService = new CreateSupplierService();

    public static void back(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SupplierView view = new SupplierView();
        Scene scene = new Scene(view, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void create(ActionEvent actionEvent) {
        Scene scene = ((Node) actionEvent.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();
        
        CreateSupplierView view = (CreateSupplierView) scene.getRoot();
        Supplier createdSupplier = createSupplierService.create(view);
        
        if (createdSupplier != null) {
            Item updatedCd = createSupplierService.updateItem(createdSupplier);
            if (updatedCd != null) {
                SupplierView newView = new SupplierView();
                scene = new Scene(newView, 1000, 600);
                stage.setScene(scene);
                stage.show();
            }
        }
    }
}
