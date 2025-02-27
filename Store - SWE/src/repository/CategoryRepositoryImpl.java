package repository;

import start.Application;
import model.Category;
import repository.CategoryRepository;

import java.io.*;
import java.util.*;

import static util.Constant.baseLocation;

public class CategoryRepositoryImpl implements CategoryRepository {
//        Test
    private static final String dataLocation = "Store - SWE/static/data/categories.dat";

    private static ObjectOutputStream categoriesOutput;

    private static Set<Category> categories;

    static {
        initializeData();
    }

    private static void initializeData() {
    	categories = tryToInitializeData();
    }

    private static Set<Category> tryToInitializeData() {
        Set<Category> cat = new HashSet<>();  // Initialize as an empty set by default

        try {
            // Try reading the categories from the file
            ObjectInputStream catInp = new ObjectInputStream(new FileInputStream(dataLocation));
            cat = (Set<Category>) catInp.readObject();  // This will override the empty set if the file is found and readable
        } catch (FileNotFoundException e) {
            System.out.println("Failed to find categories data.");
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            if (!(e instanceof EOFException)) {
                System.out.println("Failed to read categories data.");
                e.printStackTrace();
            }
        }

        return cat;  // Return the (possibly empty) set
    }


    private static void initializeOutput() {
        try {
        	categoriesOutput = new ObjectOutputStream(new FileOutputStream(dataLocation));
        } catch (FileNotFoundException e) {
        	System.out.println("Failed to find categories data.");
            e.printStackTrace();
        } catch (IOException e) {
        	System.out.println("Failed to read categories data.");
            e.printStackTrace();
        }
    }


    @Override
    public Category findByName(String name) {
        for (Category cat: categories) {
            if (cat.getName().equals(name)) {
                return cat;
            }
        }

        return null;
    }


    @Override
    public Category findById(String id) {
        Category found = null;

        for(Category cat: categories) {
            if (cat.getId().equals(id)) {
                found = cat;
            }
        }

        return found;
    }


    @Override
    public Category create(Category cat) {
        Category created = tryToCreateCategory(cat);
        return created;
    }

    public Category tryToCreateCategory(Category cat) {
        initializeOutput();
        try {
            boolean unique = categories.add(cat);
            System.out.println(unique);
            if (unique) {
            	categoriesOutput.writeObject(categories);
            	categoriesOutput.flush();
                return cat;
            }
        } catch (IOException e) {
        	System.out.println("Failed to create category.");
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Set<Category> findAll() {
        
        return categories;
    }


    @Override
    public Category update(Category cat) {
        initializeOutput();
        Category before = findById(cat.getId());

        Category updated = tryToUpdateCategory(before, cat, categories);
        return updated;
    }

    private Category tryToUpdateCategory(Category before, Category after, Set<Category> categories) {
        try {
        	categories.remove(before);
            boolean unique = categories.add(after);
            if (unique) {
            	categoriesOutput.writeObject(categories);
            	categoriesOutput.flush();
                return after;
            }
        } catch (IOException e) {
        	System.out.println("Failed to update category.");
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Category delete(Category cat) {
        initializeOutput();
        cat = findById(cat.getId());

        Category deleted = tryToDeleteCategory(cat, categories);
        return deleted;
    }

    private static Category tryToDeleteCategory(Category cat, Set<Category> categories) {
        try {
        	categories.remove(cat);
            categoriesOutput.writeObject(categories);
            categoriesOutput.flush();
            return cat;
        } catch (IOException e) {
        	System.out.println("Failed to delete category.");
            e.printStackTrace();
        }

        return null;
    }
}
