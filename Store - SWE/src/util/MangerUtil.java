package util;

import model.*;
import model.Category;
import repository.*;

import java.util.Set;
import java.util.stream.Collectors;

import static util.Alerter.showWarning;

public class MangerUtil {
    private static final CategoryRepository catRepository;
    private static final ItemRepository itemRepository;

    static {
    	catRepository = new CategoryRepositoryImpl();
    	itemRepository = new ItemRepositoryImpl();
    }

    public static void showLowCategories() {
        Set<Category> categories = (Set<Category>) catRepository.findAll();

        for (Category cat: categories) {
            String lowCatWarningMessage = "Low stock in category: " + cat.getName();

            int catBalance = 0;

            Set<Item> items = itemRepository.findAll()
                    .stream()
                    .filter(item -> item.getCategory().getId().equals(cat.getId()))
                    .collect(Collectors.toSet());

            for (Item item: items) {
                int itemBalance = item.getBoughtQuantity() - item.getSoldQuantity();
                catBalance += itemBalance;
            }

            if (catBalance < 5) {
                showWarning(lowCatWarningMessage);
            }
        }
    }
}
