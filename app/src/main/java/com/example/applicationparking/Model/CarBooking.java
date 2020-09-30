package com.example.applicationparking.Model;

public class CarBooking {
    private String name;
    private String contact;
    private String brand;
    private String model;
    private String number;
    private String date;
    private String time;
    private String etime;

    public CarBooking(String fullname, String contact, String brand, String model, String number, String date, String time, String etime) {
        this.name = fullname;
        this.contact = contact;
        this.brand = brand;
        this.model = model;
        this.number = number;
        this.date = date;
        this.time = time;
        this.etime = etime;
    }

    public String getFullname() {
        return name;
    }

    public void setFullname(String fullname) {
        this.name = fullname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }
}
