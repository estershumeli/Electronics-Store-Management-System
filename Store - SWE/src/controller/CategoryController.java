package controller;

import model.Category;
import service.CategoryService;
import view.ItemView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class CategoryController {
    private static final CategoryService catService;

    static {
    	catService = new CategoryService();
    }

    public static void back(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ItemView view = new ItemView();
        Scene scene = new Scene(view, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void add(ActionEvent actionEvent) {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText("Category");
        inputDialog.setContentText("Category Name");
        inputDialog.showAndWait();

        boolean created = catService.create(inputDialog);

        if (created) {
            ItemController.categories(actionEvent);
        }
    }
}
