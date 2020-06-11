package com.learning.SecKillProject.model;

import java.util.Date;

public class OrderInfo {
    private int order_id;
    private int user_id;
    private int merchandise_id;
    private int order_status;//0: new created unpaid, 1 paid
    private Date create_date;
    private Date pay_date;

    public void setOrder_id(int order_id) { this.order_id = order_id; }

    public int getOrder_id() { return order_id; }

    public int getUser_id() {
        return user_id;
    }

    public int getMerchandise_id() {
        return merchandise_id;
    }

    public int getOrder_status() {
        return order_status;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public Date getPay_date() {
        return pay_date;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setMerchandise_id(int merchandise_id) {
        this.merchandise_id = merchandise_id;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public void setPay_date(Date pay_date) {
        this.pay_date = pay_date;
    }
}
