package com.example.medicalshop;

public class CartList {
    public String pid;
    public String pname;
    public String pqty;
    public Float totalAmt;

    public CartList() {
    }

    public CartList(String pid, String pname, String pqty, Float totalAmt) {
        this.pid = pid;
        this.pname = pname;
        this.pqty = pqty;
        this.totalAmt = totalAmt;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPqty() {
        return pqty;
    }

    public void setPqty(String pqty) {
        this.pqty = pqty;
    }

    public Float getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(Float totalAmt) {
        this.totalAmt = totalAmt;
    }
}
