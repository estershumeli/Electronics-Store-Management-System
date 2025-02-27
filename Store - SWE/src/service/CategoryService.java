package service;

import model.Category;
import repository.CategoryRepository;
import repository.CategoryRepositoryImpl;
import validator.CategoryValidator;
import validator.CategoryValidatorImpl;
import javafx.scene.control.TextInputDialog;

import java.util.List;
import java.util.Set;

import static util.Alerter.showError;
import static validator.CategoryValidator.catExistsMessage;

public class CategoryService {
    private static final CategoryRepository catRepository;
    private static final CategoryValidator catValidator;

    static {
    	catRepository = new CategoryRepositoryImpl();
    	catValidator = new CategoryValidatorImpl();
    }

    public Set<Category> findAll() {
        return (Set<Category>) catRepository.findAll();
    }

    public boolean delete(Category cat) {
        Category deleted =  catRepository.delete(cat);
        return deleted != null;
    }

    public boolean create(TextInputDialog inputDialog) {
        String name = inputDialog.getEditor().getText();
        Category cat = new Category(name);

        Category created =  catRepository.create(cat);

        boolean validCreatedCat = catValidator.validateCreatedCategory(created);
        if (!validCreatedCat) {
            showError(catExistsMessage);
            return false;
        }

        return true;
    }

    public Category findCategoryByName(String name) {
        return catRepository.findByName(name);
    }
}
