package util;

import model.Item;
import repository.ItemRepository;
import repository.ItemRepositoryImpl;
import javafx.scene.control.ComboBox;

import java.util.Set;

public class  CashierUtil {
    private static final ItemRepository itemRepository = new ItemRepositoryImpl();

    public static void fillComboBoxItemsOptions(ComboBox itemsBox) {
        Set<Item> items = (Set<Item>) itemRepository.findAll();

        for (Item item: items) {
            itemsBox.getItems().add(item.getName());
        }
    }
}
