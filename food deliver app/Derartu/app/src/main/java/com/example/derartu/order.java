package com.example.derartu;

public class order {
    private String fullName;
    private String mobile;
    private String price;
    private String description;
    private String userId;
    private String currentMap;
    private String foodName; // Corrected property name

    public order() {
        // Default constructor required for calls to DataSnapshot.getValue(Order.class)
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public order(String price, String foodName, String description, String fullName, String mobile, String currentMap) {
        this.price = price;
        this.foodName = foodName;
        this.description = description;
        this.fullName = fullName;
        this.mobile = mobile;
        this.currentMap = currentMap;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFoodName() { // Corrected getter method name
        return foodName;
    }

    public void setFoodName(String foodName) { // Corrected setter method name
        this.foodName = foodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(String currentMap) {
        this.currentMap = currentMap;
    }
}
