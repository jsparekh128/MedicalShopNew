package com.example.medicalshop;

public class Products {
    private String categoryname;
    private String productname;
    private String productcontnt;
    private Float productprice;
    private String  productid;

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public Products() {
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductcontnt() {
        return productcontnt;
    }

    public void setProductcontnt(String productcontnt) {
        this.productcontnt = productcontnt;
    }

    public Float getProductprice() {
        return productprice;
    }

    public void setProductprice(Float productprice) {
        this.productprice = productprice;
    }
}

