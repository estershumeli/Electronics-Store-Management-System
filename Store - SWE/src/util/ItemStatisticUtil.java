package util;

import model.Bill;
import model.Item;
import model.Category;
import model.Supplier;
import repository.*;
import repository.BillRepositoryImpl;
import repository.CategoryRepositoryImpl;
import repository.SupplierRepositoryImpl;
import statistic.BalanceStatistic;
import javafx.scene.chart.PieChart;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ItemStatisticUtil {
    private static final CategoryRepository catRepository;
    private static final BillRepository billRepository;
    private static final SupplierRepository supplierRepository;

    static {
    	catRepository = new CategoryRepositoryImpl();
        billRepository = new BillRepositoryImpl();
        supplierRepository = new SupplierRepositoryImpl();
    }

    public static List<PieChart.Data> searchItemsSoldByCategory(LocalDate fromDate, LocalDate toDate) {
        List<PieChart.Data> itemsSoldByCategory = new ArrayList<>();

        Set<Category> categories = (Set<Category>) catRepository.findAll();
        for(Category cat: categories) {
            PieChart.Data itemSoldByCategory = getItemsSoldByCategory(cat, fromDate, toDate);
            itemsSoldByCategory.add(itemSoldByCategory);
        }

        return itemsSoldByCategory;
    }

    private static PieChart.Data getItemsSoldByCategory(Category cat, LocalDate fromDate, LocalDate toDate) {
        String catName = cat.getName();
        double count = 0;

        List<Bill> bills = billRepository.findAll()
                .stream()
                .filter(bill -> {
                    if (fromDate == null || toDate == null) {
                        return true;
                    }

                    // Make sure the date is within the range
                    return (bill.getIssueDate().equals(fromDate) || bill.getIssueDate().isAfter(fromDate)) &&
                            (bill.getIssueDate().equals(toDate) || bill.getIssueDate().isBefore(toDate));
                })
                .map(bill -> {
                        Bill newBill = new Bill();
                        newBill.setItems(
                            bill.getItems()
                                    .stream()
                                    .filter(item -> item.getCategory() != null && item.getCategory().getId().equals(cat.getId()))                                     .collect(Collectors.toCollection(ArrayDeque::new))
                        );
                        return newBill;
                    }
                )
                .collect(Collectors.toList());

        // Count the sold quantities
        for (Bill bill : bills) {
           for (Item item : bill.getItems()) {
               if (item != null) {
                   count += item.getSoldQuantity();
               }
           }
        }

        PieChart.Data itemSoldByCategory = new PieChart.Data(catName, count);
        return itemSoldByCategory;
    }

    public static List<PieChart.Data> searchItemsBoughtByCategory(LocalDate fromDate, LocalDate toDate) {
        List<PieChart.Data> itemsBoughtByCategory = new ArrayList<>();
        
        Set<Category> categories = (Set<Category>) catRepository.findAll();
        for(Category category: categories) {
            PieChart.Data itemBoughtByCategory = getItemsBoughtByCategory(category, fromDate, toDate);
            itemsBoughtByCategory.add(itemBoughtByCategory);
        }

        return itemsBoughtByCategory;
    }

    private static PieChart.Data getItemsBoughtByCategory(Category category, LocalDate fromDate, LocalDate toDate) {
        String catName = category.getName();
        double count = 0;

        List<Supplier> suppliers = supplierRepository.findAll()
                .stream()
                .filter(supplier -> {
                    if (fromDate == null || toDate == null) {
                        return true;
                    }

                    // Ensure the register date is within the range
                    return (supplier.getRegisterDate().equals(fromDate) || supplier.getRegisterDate().isAfter(fromDate)) &&
                            (supplier.getRegisterDate().equals(toDate) || supplier.getRegisterDate().isBefore(toDate));
                })
                .filter(supplier -> supplier.getItem() != null && supplier.getItem().getCategory() != null && supplier.getItem().getCategory().getId().equals(category.getId())) 
                .collect(Collectors.toList());

        for (Supplier supplier : suppliers) {
            if (supplier.getItem() != null) {
                count += supplier.getItemQuantity();
            }
        }

        PieChart.Data itemBoughtByCategory = new PieChart.Data(catName, count);
        return itemBoughtByCategory;
    }
}
