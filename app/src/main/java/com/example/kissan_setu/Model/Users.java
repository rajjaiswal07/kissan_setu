package com.example.kissan_setu.Model;

public class Users {
    private String address, adhar, name, password, phone;

    public Users() {

    }

    public Users(String address, String adhar, String name, String password, String phone) {
        this.address = address;
        this.adhar = adhar;
        this.name = name;
        this.password = password;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdhar() {
        return adhar;
    }

    public void setAdhar(String adhar) {
        this.adhar = adhar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
