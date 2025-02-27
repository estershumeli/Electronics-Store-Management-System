package model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Item implements Serializable {
	
    private String id;
    private String name;
    private Category cat;
    private double buyPrice;
    private int boughtQuantity;
    private double sellPrice;
    private int soldQuantity;

    {
        id = UUID.randomUUID().toString();
    }

    public Item() {
    }

    public Item(String name, Category cat, double buyPrice, double sellPrice) {
        this.name = name;
        this.cat = cat;
        this.buyPrice = buyPrice;
        this.boughtQuantity = 0;
        this.sellPrice = sellPrice;
        this.soldQuantity = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return cat;
    }

    public void setCategory(Category cat) {
        this.cat = cat;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getBoughtQuantity() {
        return boughtQuantity;
    }

    public void setBoughtQuantity(int boughtQuantity) {
        this.boughtQuantity = boughtQuantity;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
