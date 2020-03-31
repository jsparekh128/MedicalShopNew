package com.example.medicalshop;

public class ProductJay {
    private String productname;
    private String description;
    private int qty;
    private int rate;
    private int productid;
    private String category;

    public ProductJay() {
    }

    public ProductJay(String productname, String description, int qty, int rate, int productid, String category) {
        this.productname = productname;
        this.description = description;
        this.qty = qty;
        this.rate = rate;
        this.productid = productid;
        this.category = category;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
