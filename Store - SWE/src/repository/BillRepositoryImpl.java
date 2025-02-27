package repository;

import start.Application;
import model.Bill;
import repository.BillRepository;


import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static util.Constant.baseLocation;

public class BillRepositoryImpl implements BillRepository {
    //        Test
    private static final String dataLocation = "Store - SWE/static/data/categories.dat";

    //        Production
//    private static String dataLocation = baseLocation + "/data/bills.dat";
    private static ObjectOutputStream billsOutput;

    private static List<Bill> bills;

    static {
        initializeData();
    }

    private static void initializeData() {
        bills = tryToInitializeData();
    }

    private static List<Bill> tryToInitializeData() {
        List<Bill> bills = new ArrayList<>();

        try {
            ObjectInputStream billsInput = new ObjectInputStream(new FileInputStream(dataLocation));
            bills = (ArrayList<Bill>) billsInput.readObject();
        } catch (FileNotFoundException e) {
        	System.out.println("Failed to find bills data.");
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            if (! (e instanceof EOFException)) {
            	System.out.println("Failed to read bills data.");
                e.printStackTrace();
            }
        }

        return bills;
    }

    private static void initializeOutput() {
        try {
            billsOutput = new ObjectOutputStream(new FileOutputStream(dataLocation));
        } catch (FileNotFoundException e) {
        	System.out.println("Failed to find bills data.");
            e.printStackTrace();
        } catch (IOException e) {
        	System.out.println("Failed to read bills data.");
            e.printStackTrace();
        }
    }


    @Override
    public Bill create(Bill bill) {
        Bill created = tryToCreateBill(bill);
        return created;
    }

    private Bill tryToCreateBill(Bill bill) {
        initializeOutput();

        try {
            bills.add(bill);
            billsOutput.writeObject(bills);
            billsOutput.flush();
            return bill;
        } catch (IOException e) {
        	System.out.println("Failed to create bill.");
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Bill findById(String id) {
        Bill found = null;

        for(Bill bill: bills) {
            if (bill.getId().equals(id)) {
                found = bill;
            }
        }

        return found;
    }


    @Override
    public List<Bill> findAll() {
        return bills;
    }


    @Override
    public Bill update(Bill bill) {
        initializeOutput();
        Integer index = getIndexOfElement(bill, bills);

        Bill updated = tryToUpdateBill(index, bill, bills);
        return updated;
    }

    private static Bill tryToUpdateBill(Integer index, Bill bill, List<Bill> bills) {
        try {
            if (index != null) {
                bills.set(index, bill);
                billsOutput.writeObject(bills);
                billsOutput.flush();
                return bill;
            }
        } catch (IOException e) {
        	System.out.println("Failed to update bill.");
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Bill delete(Bill bill) {
        initializeOutput();
        Integer index = getIndexOfElement(bill, bills);

        Bill deleted = tryToDeleteBill(index, bills);
        return deleted;
    }

    private static Bill tryToDeleteBill(Integer index, List<Bill> bills) {
        try {
            if (index != null) {
                Bill bill = bills.remove((int) index);
                billsOutput.writeObject(bills);
                billsOutput.flush();
                return bill;
            }
        } catch (IOException e) {
        	System.out.println("Failed to delete bill.");
            e.printStackTrace();
        }

        return null;
    }


    private Integer getIndexOfElement(Bill bill, Collection<Bill> bills) {
        Integer index = null;

        Integer i = 0;
        for(Bill currentItem: bills) {
            if (currentItem.getId().equals(bill.getId())) {
                index = i;
            }
            i++;
        }

        return index;
    }
}
