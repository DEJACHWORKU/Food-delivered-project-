package com.example.derartu;

public class HelperClass {
    private String name;
    private String email;
    private String username;
    private String password;
    private String copass;
    private String campus = "student";  // Set "student" as the default value for campus

    public HelperClass(String name, String email, String username, String password, String copass) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.copass = copass;
        // Campus will be set to "student" by default
    }

    public HelperClass() {
        // Default constructor
        this.campus = "student";  // Set "student" as the default value for campus
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCopass() {
        return copass;
    }

    public String getCampus() {
        return campus;
    }

    // The following setter is optional, depending on your requirements

    public void setCampus(String campus) {
        this.campus = campus;
    }
}
