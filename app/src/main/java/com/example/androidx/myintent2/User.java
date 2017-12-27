package com.example.androidx.myintent2;

import java.io.Serializable;

/**
 * Created by androidx on 12/17/17.
 */

public class User implements Serializable {

    private String name = "";
    private String tel = "";
    private String email = "";
    private String miscell = "";
    private String place = "";


    public User(String name, String tel, String email, String miscell, String place) {
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.miscell = miscell;
        this.place = place;
    }


    public User() {


    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", miscell='" + miscell + '\'' +
                ", place='" + place + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMiscell() {
        return miscell;
    }

    public void setMiscell(String miscell) {
        this.miscell = miscell;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }







}
