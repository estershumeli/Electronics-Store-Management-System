package service;

import model.Item;
import model.Category;
import repository.*;
import validator.*;
import view.CreateItemView;

import static util.Alerter.showError;
import static validator.ItemValidator.itemsExistsErrorMessage;

public class CreateItemService {
    private static final ItemRepository itemRepository;
    private static final ItemValidator createItemValidator;

    static {
    	itemRepository = new ItemRepositoryImpl();
    	createItemValidator = new ItemValidatorImpl();
    }

    public boolean createItem(CreateItemView view) {
        String name = view.getName().getText();
        Category cat = view.getCategories().getValue();
        double buyPrice = Double.parseDouble(view.getBuyPrice().getText());
        double sellPrice = Double.parseDouble(view.getSellPrice().getText());
        Item item = new Item(name, cat, buyPrice, sellPrice);

        Item created = itemRepository.create(item);
        boolean validCreatedItem = createItemValidator.validateCreatedItem(created);

        if (!validCreatedItem) {
            showError(itemsExistsErrorMessage);
            return false;
        }

        return true;
    }
}
