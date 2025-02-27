package util;

import model.Category;
import repository.CategoryRepository;
import repository.CategoryRepositoryImpl;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Set;

public class CreateItemViewUtil {
    private static final CategoryRepository catRepository = new CategoryRepositoryImpl();

    public static void fillComboBoxCategoriesOptions(ComboBox<Category> categoriesBox) {
        Set<Category> cats = (Set<Category>) catRepository.findAll();

        for (Category cat: cats) {
        	categoriesBox.getItems().add(cat);
        }
    }
}
