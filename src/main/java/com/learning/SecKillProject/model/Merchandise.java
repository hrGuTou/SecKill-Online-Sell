package com.learning.SecKillProject.model;

public class Merchandise {
    private int merchandise_id;
    private String merchandise_name;
    private String merchandise_img;
    private String merchandise_detail;
    private double merchandise_price;
    private int merchandise_stock;

    public int getMerchandise_id() {
        return merchandise_id;
    }

    public void setMerchandise_id(int merchandise_id){ this.merchandise_id = merchandise_id; }

    public String getMerchandise_name() {
        return merchandise_name;
    }

    public String getMerchandise_img() {
        return merchandise_img;
    }

    public String getMerchandise_detail() {
        return merchandise_detail;
    }

    public double getMerchandise_price() {
        return merchandise_price;
    }

    public int getMerchandise_stock() {
        return merchandise_stock;
    }

    public void setMerchandise_name(String merchandise_name) {
        this.merchandise_name = merchandise_name;
    }

    public void setMerchandise_img(String merchandise_img) {
        this.merchandise_img = merchandise_img;
    }

    public void setMerchandise_detail(String merchandise_detail) {
        this.merchandise_detail = merchandise_detail;
    }

    public void setMerchandise_price(double merchandise_price) {
        this.merchandise_price = merchandise_price;
    }

    public void setMerchandise_stock(int merchandise_stock) {
        this.merchandise_stock = merchandise_stock;
    }
}
