package com.example.derartu;

public class InitializeResponseData {
    private String status;
    private Data data;

    // Constructor, getters, and setters
    public InitializeResponseData(String status, Data data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private String checkoutUrl;

        // Constructor, getters, and setters
        public Data(String checkoutUrl) {
            this.checkoutUrl = checkoutUrl;
        }

        public String getCheckoutUrl() {
            return checkoutUrl;
        }

        public void setCheckoutUrl(String checkoutUrl) {
            this.checkoutUrl = checkoutUrl;
        }
    }
}
