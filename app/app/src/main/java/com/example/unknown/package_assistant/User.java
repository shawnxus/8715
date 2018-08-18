package com.example.unknown.package_assistant;

public class User {
    String name, password, email, address, role;
    int phone;
    User(String name, String password, String email, String address, int phone, String role){
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
        this.role = role;
        this.phone = phone;
    }
    @Override
    public String toString(){
        return "account: "+email + ", password: "+password+", address: "+address +", nickname: "+name+", phone: "+phone+", role: "+role;
    }
}
