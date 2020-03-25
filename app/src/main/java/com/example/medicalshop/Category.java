package com.example.medicalshop;

public class Category {
    String categoryid;
    String categoryname;

    public Category(){

    }

    public Category(String categoryid, String categoryname) {
        this.categoryid = categoryid;
        this.categoryname = categoryname;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }
}
