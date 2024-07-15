package com.example.foodbuy;

public class CartItem {
    private int imageResourceId;
    private String itemName;
    private String itemPrice;

    public CartItem() {
    }

    public CartItem(int imageResourceId, String itemName, String itemPrice) {
        this.imageResourceId = imageResourceId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
}
