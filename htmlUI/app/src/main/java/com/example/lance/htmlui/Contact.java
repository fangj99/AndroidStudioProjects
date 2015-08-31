package com.example.lance.htmlui;

import android.content.Intent;

/**
 * Created by lance on 7/16/2015.
 */
public class Contact {


    private Integer id;
    private String name;

    private String phone;
    private Integer amount;

    public Contact( Integer id, String name, String phone, Integer amount) {
        this.amount = amount;
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
