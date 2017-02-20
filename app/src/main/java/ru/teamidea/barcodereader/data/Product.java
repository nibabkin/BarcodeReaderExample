package ru.teamidea.barcodereader.data;

/**
 * Created by nikita on 2/17/17.
 */

public class Product {

    private int id;
    private String code;
    private String name;
    private String category;
    private float price;
    private int quantity;
    private String firm;

    public Product(int id, String code, String name, String category, float price, int quantity, String firm) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.firm = firm;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getFirm() {
        return firm;
    }

}
