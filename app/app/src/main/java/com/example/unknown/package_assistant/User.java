package com.example.unknown.package_assistant;

public class User {
    String name, password, email, address, role;
    User(String name, String password, String email, String address, String role){
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
        this.role = role;
    }
    @Override
    public String toString(){
        return "name: "+name + ", password: "+password+", email: "+email+", address: "+address +", role: "+role;
    }
}
