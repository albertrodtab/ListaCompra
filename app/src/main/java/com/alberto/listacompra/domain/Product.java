package com.alberto.listacompra.domain;

public class Product {
    private String name;
    private String category;
    private int quantity;
    private float price;
    private boolean important;


    public Product(String name, String category, int quantity, float price, boolean important) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price * quantity;
        this.important = important;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    @Override
    public String toString() {
        return name + "(" + quantity + ") = " + price + " €.";
    }
}
