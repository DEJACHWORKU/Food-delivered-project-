package com.example.derartu;

public class OrderItem {
    private String foodName;
    private String foodDescription;
    private double totalPrice;

    public OrderItem() {
        // Default constructor required for Firebase
    }

    public OrderItem(String foodName, String foodDescription, double totalPrice) {
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.totalPrice = totalPrice;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

