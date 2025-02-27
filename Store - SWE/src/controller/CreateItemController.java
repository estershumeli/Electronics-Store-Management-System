package controller;

import service.CreateItemService;
import view.ItemView;
import view.CreateItemView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateItemController {
    private static final CreateItemService createCdService;

    static {
        createCdService = new CreateItemService();
    }

    public static void back(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ItemView view = new ItemView();
        Scene scene = new Scene(view, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void create(ActionEvent actionEvent) {
        CreateItemView view = (CreateItemView) ((Node) actionEvent.getSource()).getScene().getRoot();
        boolean isCreated =  createCdService.createItem(view);

        if (isCreated) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            ItemView newView  = new ItemView();
            Scene scene = new Scene(newView, 1000, 600);
            stage.setScene(scene);
            stage.show();
        }
    }
}
