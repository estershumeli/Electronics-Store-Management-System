package repository;

import model.Item;
import repository.CRUDRepository;

public interface ItemRepository extends CRUDRepository<Item> {
    Item findByName(String name);
}
