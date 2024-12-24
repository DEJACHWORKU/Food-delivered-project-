package com.example.derartu;

public class Customization {
    private String title;
    private String description;
    private String logoUrl;

    public Customization(String title, String description, String logoUrl) {
        this.title = title;
        this.description = description;
        this.logoUrl = logoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
