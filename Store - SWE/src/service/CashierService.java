package service;

import controller.HomeController;
import model.*;
import repository.*;
import validator.*;
import view.CashierView;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static util.Alerter.showError;
import static util.Constant.baseLocation;
import static validator.CashierValidator.itemBalanceErrorMessage;

public class CashierService {
    private static final ItemRepository itemRepository;
    private static final BillRepository billRepository;
    private static final CashierValidator cashierValidator;

    private Bill bill;
    private CashierView view;

    static {
    	itemRepository = new ItemRepositoryImpl();
        billRepository = new BillRepositoryImpl();
        cashierValidator = new CashierValidatorImpl();
    }

    public CashierService(Bill bill, CashierView view) {
        this.bill = bill;
        this.view = view;
    }

    public Bill saveBill() {
        User issuer = HomeController.getUser();
        bill.setIssuer(issuer);
        bill.setBillInformation(view.getBillInformation().getText());

        for (Item item: bill.getItems()) {
        	itemRepository.update(item);
        }

//        boolean validBill = cashierValidator.validateBill(bill);

//        if (validBill) {
            return billRepository.create(bill);
//        }

//        return null;
    }

    public boolean saveBillFile(Bill bill) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        String timestamp =  now.format(formatter);
//            Test
        String dataLocation = "static/bills/bill_" + timestamp;

//            Production
//        String dataLocation = baseLocation + "/bills/bill_" + timestamp;
        File billFile = new File(dataLocation);

        tryToSaveBillFile(billFile);
        boolean saved = tryToWriteBillInformation(billFile, bill.getBillInformation());
        return saved;
    }

    private static void tryToSaveBillFile(File billFile) {
        try {
            billFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean tryToWriteBillInformation(File billFile, String billInformation) {
        try {
            FileWriter myWriter = new FileWriter(billFile.getAbsolutePath());
            myWriter.write(billInformation);
            myWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean addItemToBill() {
        String name = (String) view.getItem().getValue();
        int quantity = Integer.parseInt(view.getQuantity().getText());

        Item item = itemRepository.findByName(name);
        int soldQuantity = item.getSoldQuantity();

        boolean validItemBalance = cashierValidator.validateItemBalance(item, quantity);
        if (!validItemBalance) {
            showError(itemBalanceErrorMessage);
            return false;
        }

        item.setSoldQuantity(soldQuantity + quantity);

        double price = item.getSellPrice() * quantity;
        double total = bill.getTotal() + price;
        bill.setTotal(total);

        return bill.getItems().add(item);
    }

    public void addItemToBillInformation() {
        TextArea billInformationNode = view.getBillInformation();
        String billInformation = billInformationNode.getText();
        billInformation = billInformation.substring(billInformation.indexOf("\n"));
        String total = "Total:    " + bill.getTotal();
        String title = (String) view.getItem().getValue();
        int quantity = Integer.parseInt(view.getQuantity().getText());

        Item item = bill.getItems().getLast();
        double price = item.getSellPrice() * quantity;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(total).append(billInformation).append("\n");
        stringBuilder.append("Item Name:    ").append(title).append("\n")
        .append("Quantity:    ").append(quantity).append("\n")
        .append("Price:    ").append(price);

        billInformation = stringBuilder.toString();

        billInformationNode.setText(billInformation);
    }


    public boolean removeItemFromBill() {
        int quantity = Integer.parseInt(view.getQuantity().getText());

        Item item = itemRepository.findByName(bill.getItems().getLast().getName());
        int soldQuantity = item.getSoldQuantity();
        item.setSoldQuantity(soldQuantity - quantity);

        double price = item.getSellPrice() * quantity;
        double total = bill.getTotal() - price;
        bill.setTotal(total);

        return bill.getItems().remove(item);
    }

    public void removeItemFromBillInformation() {
        TextArea billInformationNode = view.getBillInformation();
        String total = "Total:    " + bill.getTotal();
        String billInformation = billInformationNode.getText();
        billInformation = billInformation.substring(billInformation.indexOf("\n"));

        for (int i = 0; i < 3; i++) {
            billInformation = billInformation.substring(0, billInformation.lastIndexOf("\n"));
        }

        billInformation = total + billInformation;

        billInformationNode.setText(billInformation);
    }


    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public CashierView getView() {
        return view;
    }

    public void setView(CashierView view) {
        this.view = view;
    }
}
