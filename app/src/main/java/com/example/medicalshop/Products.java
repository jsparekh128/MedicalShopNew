package com.example.medicalshop;

public class Products {
    public Integer productid;
    public String productname;
    public String productcontnt;
    public Float productprice;

    public Products(){
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
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

    public float getProductprice() {
        return productprice;
    }

    public void setProductprice(Float productprice) {
        this.productprice = productprice;
    }
}
