package service;

import model.Item;
import model.Category;
import repository.ItemRepository;
import repository.ItemRepositoryImpl;
import validator.ItemValidator;
import validator.ItemValidatorImpl;
import view.EditItemView;

import java.util.Set;

import static util.Alerter.showError;
import static validator.ItemValidator.itemsExistsErrorMessage;

public class ItemService {
    private static final ItemRepository itemRepository;
    private static final ItemValidator itemValidator;

    static {
    	itemRepository = new ItemRepositoryImpl();
    	itemValidator = new ItemValidatorImpl();
    }

    public Set<Item> findAll() {
        return (Set<Item>) itemRepository.findAll();
    }

    public Item findById(String id) {
        return itemRepository.findById(id);
    }

    public boolean update(EditItemView view, String id) {
        String title = view.getName().getText();
        Category cat = view.getCategories().getValue();
        double buyPrice = Double.parseDouble(view.getBuyPrice().getText());
        double sellPrice = Double.parseDouble(view.getSellPrice().getText());

        Item newItem = new Item(title, cat, buyPrice, sellPrice);
        newItem.setId(id);

        Item updated = itemRepository.update(newItem);
        boolean validUpdatedItem = itemValidator.validateUpdatedItem(updated);

        if (!validUpdatedItem) {
            showError(itemsExistsErrorMessage);
            return false;
        }

        return true;
    }

    public boolean delete(Item item) {
        Item deleted = itemRepository.delete(item);
        return deleted != null;
    }
}
