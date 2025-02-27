package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class Supplier implements Serializable {
    private String id;
    private String name;
    private Item item;
    private int itemQuantity;
    private LocalDate registerDate;

    {
        id = UUID.randomUUID().toString();
        this.registerDate = LocalDate.now();
    }

    public Supplier() {
    }

    public Supplier(String name, Item item, int itemQuantity) {
        this.name = name;
        this.item = item;
        this.itemQuantity = itemQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }
}
