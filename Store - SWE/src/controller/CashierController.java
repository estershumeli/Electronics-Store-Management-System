package controller;

import model.Bill;
import service.CashierService;
import util.Role;
import view.AdminView;
import view.CashierView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CashierController {
    private static CashierService cashierService;

    public static void addToBill(ActionEvent actionEvent) {
        boolean itemAdded = cashierService.addItemToBill();
        if (itemAdded) {
            cashierService.addItemToBillInformation();
        }
    }

    public static void checkOut(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Bill created = cashierService.saveBill();
        if (created != null) {
            boolean savedBillFile = cashierService.saveBillFile(created);

            if (savedBillFile) {
                CashierView view = new CashierView();
                cashierService = new CashierService(new Bill(), view);

                Scene scene = new Scene(view, 1000, 600);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    public static void removeLast(ActionEvent actionEvent) {
        boolean cdRemoved = cashierService.removeItemFromBill();
        if (cdRemoved) {
            cashierService.removeItemFromBillInformation();
        }
    }

    public static void back(ActionEvent actionEvent) {
        if (HomeController.getUser().getRole() == Role.ADMIN) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            AdminView view = new AdminView();
            Scene scene = new Scene(view, 1000, 600);
            stage.setScene(scene);
            stage.show();
        } else {
            HomeController.home(actionEvent);
        }
    }

    public static CashierService getCashierService() {
        return cashierService;
    }

    public static void setCashierService(CashierService cashierService) {
        CashierController.cashierService = cashierService;
    }
}
