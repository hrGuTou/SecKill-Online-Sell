package com.learning.SecKillProject.model;

import java.io.Serializable;

public class OrderRecord implements Serializable {
    private int merchandise_id;
    private int user_id;

    public OrderRecord(int merchandise_id, int user_id) {
        this.merchandise_id = merchandise_id;
        this.user_id = user_id;
    }

    public int getMerchandise_id() {
        return merchandise_id;
    }

    public void setMerchandise_id(int merchandise_id) {
        this.merchandise_id = merchandise_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
