package service;

import model.Item;
import model.Supplier;
import repository.ItemRepository;
import repository.SupplierRepository;
import repository.ItemRepositoryImpl;
import repository.SupplierRepositoryImpl;
import view.CreateSupplierView;

public class CreateSupplierService {
    private static final SupplierRepository supplierRepository;
    private static final ItemRepository itemRepository;

    static {
        supplierRepository = new SupplierRepositoryImpl();
        itemRepository = new ItemRepositoryImpl();
    }

    public Supplier create(CreateSupplierView view) {
        String chosenItem = (String) view.getItems().getValue();
        Item item = itemRepository.findByName(chosenItem);
        String name = view.getName().getText();
        int quantity = Integer.parseInt(view.getQuantity().getText());
        Supplier supplier = new Supplier(name, item, quantity);

        Supplier created = supplierRepository.create(supplier);
        return created;
    }

    public Item updateItem(Supplier supplier) {
        Item item = supplier.getItem();
        int boughtQuantity = item.getBoughtQuantity() + supplier.getItemQuantity();
        item.setBoughtQuantity(boughtQuantity);

        Item updated = itemRepository.update(item);
        return updated;
    }
}
