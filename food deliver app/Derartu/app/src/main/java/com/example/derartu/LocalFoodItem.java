package com.example.derartu;

import android.graphics.Bitmap;

public class LocalFoodItem {

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCampus() {
        return campus;
    }

    public String getImage() {
        return image;
    }

    public String getQrimage() {
        return qrimage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setQrimage(String qrimage) {
        this.qrimage = qrimage;
    }


    public String getLoggedInUserId() {
        return loggedInUserId;
    }

    public void setLoggedInUserId(String loggedInUserId) {
        this.loggedInUserId = loggedInUserId;
    }


    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    private String currentLocation;
    private String mobileNumber;
    private String loggedInUserId; // Added loggedInUserId field
    private String name;
    private String username;
    private String password;
    private String key;
    private String campus;
    private String image;
    private String qrimage;
    Bitmap bitmap; // Add a Bitmap field

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    // Default constructor required for Firebase
    public LocalFoodItem() {}

    public LocalFoodItem(String name, String username, String password, String campus, String image, String qrimage) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.campus = campus;
        this.image = image;
        this.qrimage = qrimage;
    }

    // Add getters and setters
    // ...

    public LocalFoodItem(String price, String foodname, String description, String loggedInUserId) {
        this.name = price;
        this.username = foodname;
        this.password = description;
        this.loggedInUserId = loggedInUserId; // Assign loggedInUserId


    }

    public LocalFoodItem(String fullName, String currentLocation, String mobileNumber) {
        this.fullName = fullName;
        this.currentLocation = currentLocation;
        this.mobileNumber = mobileNumber;
    }

    // Other code...
}

