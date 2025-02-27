package controller;

import model.Item;
import service.ItemService;
import view.ItemView;
import view.EditItemView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditItemController {
    private static final ItemService cdService;

    static {
        cdService = new ItemService();
    }

    public static void back(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ItemView view = new ItemView();
        Scene scene = new Scene(view, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void update(ActionEvent actionEvent, String id) {
        EditItemView view = (EditItemView) ((Node) actionEvent.getSource()).getScene().getRoot();
        boolean updated = cdService.update(view, id);

        if (updated) {
            back(actionEvent);
        }
    }

    public static void delete(ActionEvent actionEvent, String id) {
        Item cd = new Item();
        cd.setId(id);

        boolean deleted = cdService.delete(cd);

        if (deleted) {
            back(actionEvent);
        }
    }
}
