package com.example.derartu;

import java.math.BigDecimal;

public class PostData {
    private BigDecimal amount;
    private String currency;
    private String firstName;
    private String lastName;
    private String email;
    private String txRef;
    private String callbackUrl;
    private String returnUrl;
    private String subAccountId;
    private Customization customization;

    public PostData(BigDecimal amount, String currency, String firstName, String lastName,
                         String email, String txRef, String callbackUrl, String returnUrl,
                         String subAccountId, Customization customization) {
        this.amount = amount;
        this.currency = currency;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.txRef = txRef;
        this.callbackUrl = callbackUrl;
        this.returnUrl = returnUrl;
        this.subAccountId = subAccountId;
        this.customization = customization;
    }

    // Getters and setters
    // Add getters and setters for all fields here



    // Getters and setters
    // Add getters and setters for all fields here
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTxRef() {
        return txRef;
    }

    public void setTxRef(String txRef) {
        this.txRef = txRef;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getSubAccountId() {
        return subAccountId;
    }

    public void setSubAccountId(String subAccountId) {
        this.subAccountId = subAccountId;
    }

    public Customization getCustomization() {
        return customization;
    }

    public void setCustomization(Customization customization) {
        this.customization = customization;
    }
}
