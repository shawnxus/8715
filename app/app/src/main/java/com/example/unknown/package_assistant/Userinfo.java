package com.example.unknown.package_assistant;

import java.util.ArrayList;

public class Userinfo {
    static ArrayList<User> Users;
    static void add(User user){
        if(Users == null){
            Users = new ArrayList<User>();
            Users.add(user);
        }
        else{
            if(search(user.name) == null)
                Users.add(user);
        }
    }
   static User search(String email){
        if(Users == null || Users.size() == 0) {
            Users = new ArrayList<User>();
            System.out.println("seach contains pro");
        }
        else{
            for(User u : Users) {
                if (u.email.toString() == email.toString())
                    System.out.println("Found user info: "+u.toString());
                    return u;
            }
        }
       return null;
    }
    static void update(User user){
        User us = search(user.name);
        us.password = user.password;
        us.email = user.email;
        us.address = user.address;
        us.phone = user.phone;
    }
}
