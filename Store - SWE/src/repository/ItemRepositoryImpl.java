package repository;

import start.Application;
import model.Item;
import repository.ItemRepository;


import java.io.*;
import java.util.*;

import static util.Constant.baseLocation;

public class ItemRepositoryImpl implements ItemRepository {
    //        Test
    private static final String dataLocation = "Store - SWE/static/data/items.dat";

    private static ObjectOutputStream itemsOutput;

    private static Set<Item> items;

    static {
        initializeData();
    }

    private static void initializeData() {
    	items = tryToInitializeData();
    }

    private static Set<Item> tryToInitializeData() {
        Set<Item> items = new HashSet<>();  // Initialize as an empty set by default

        try {
            // Try reading the items from the file
            ObjectInputStream itemInput = new ObjectInputStream(new FileInputStream(dataLocation));
            items = (HashSet<Item>) itemInput.readObject();  // This will override the empty set if the file is found and readable
        } catch (FileNotFoundException e) {
            System.out.println("Failed to find items data.");
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            if (!(e instanceof EOFException)) {
                System.out.println("Failed to read items data.");
                e.printStackTrace();
            }
        }

        return items;  // Return the (possibly empty) set
    }


    private static void initializeOutput() {
        try {
        	itemsOutput = new ObjectOutputStream(new FileOutputStream(dataLocation));
        } catch (FileNotFoundException e) {
        	System.out.println("Failed to find items data.");
            e.printStackTrace();
        } catch (IOException e) {
        	System.out.println("Failed to read items data.");
            e.printStackTrace();
        }
    }


    @Override
    public Item findByName(String name) {
        for(Item item: items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }


    @Override
    public Item findById(String id) {
        Item found = null;

        for(Item item: items) {
            if (item.getId().equals(id)) {
                found = item;
            }
        }

        return found;
    }


    @Override
    public Item create(Item item) {
        Item created = tryToCreateItem(item);
        return created;
    }

    private Item tryToCreateItem(Item item) {
        initializeOutput();

        try {
            boolean unique = items.add(item);
            if (unique) {
            	itemsOutput.writeObject(items);
            	itemsOutput.flush();
                return item;
            }
        } catch (IOException e) {
        	System.out.println("Failed to create item.");
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Set<Item> findAll() {
        return items;
    }


    @Override
    public Item update(Item item) {
        initializeOutput();
        Item before = findById(item.getId());

        Item updated = tryToUpdateItem(before, item, items);
        return updated;
    }

    private Item tryToUpdateItem(Item before, Item after, Set<Item> items) {
        try {
        	items.remove(before);
            boolean unique = items.add(after);
            if (unique) {
            	itemsOutput.writeObject(items);
            	itemsOutput.flush();
                return after;
            }
        } catch (IOException e) {
        	System.out.println("Failed to update item.");
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Item delete(Item item) {
        initializeOutput();
        item = findById(item.getId());

        Item deleted = tryToDeleteItem(item, items);
        return deleted;
    }

    private static Item tryToDeleteItem(Item item, Set<Item> items) {
        try {
        	items.remove(item);
            itemsOutput.writeObject(items);
            itemsOutput.flush();
            return item;
        } catch (IOException e) {
        	System.out.println("Failed to delete item.");
            e.printStackTrace();
        }

        return null;
    }
}
