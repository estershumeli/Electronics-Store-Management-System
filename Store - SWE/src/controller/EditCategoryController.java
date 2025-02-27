package controller;

import model.Item;
import model.Category;
import service.CategoryService;
import view.CategoryView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.lang.reflect.GenericArrayType;

public class EditCategoryController {
    private static final CategoryService genreService;

    static {
        genreService = new CategoryService();
    }

    public static void delete(ActionEvent actionEvent, String id) {
        Category genre = new Category();
        genre.setId(id);

        boolean deleted = genreService.delete(genre);

        if (deleted) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            CategoryView view = new CategoryView();
            Scene scene = new Scene(view, 1000, 600);
            stage.setScene(scene);
            stage.show();
        }
    }
}
