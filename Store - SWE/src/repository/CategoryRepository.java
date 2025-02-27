package repository;

import model.Category;
import repository.CRUDRepository;

public interface CategoryRepository extends CRUDRepository<Category> {
    Category findByName(String name);
}
