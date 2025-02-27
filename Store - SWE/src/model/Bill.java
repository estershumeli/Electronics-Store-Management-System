package model;

import model.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.UUID;

public class Bill implements Serializable  {
    private String id;
    private Deque<Item> items;
    private double total;
    private LocalDate issueDate;
    private User issuer;
    private String billInformation;

    {
        id = UUID.randomUUID().toString();
        this.issueDate = LocalDate.now();
    }

    public Bill() {
    	items = new ArrayDeque<>();
    }

    public Bill(Deque<Item> items, double total, LocalDate issueDate, User issuer) {
        this.items = items;
        this.total = total;
        this.issuer = issuer;
    }

    public Deque<Item> getItems() {
        return items;
    }

    public void setItems(Deque<Item> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public User getIssuer() {
        return issuer;
    }

    public void setIssuer(User issuer) {
        this.issuer = issuer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBillInformation() {
        return billInformation;
    }

    public void setBillInformation(String billInformation) {
        this.billInformation = billInformation;
    }
}
