package com.bill.invoicebackend.model;

public class Product {

    private String name;
    private String description;
    private Number price;
    private Number quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public Number getQuantity() {
        return quantity;
    }

    public void setQuantity(Number quantity) {
        this.quantity = quantity;
    }
}
