package com.example.ramanbindal.panakeia;

import com.google.firebase.database.IgnoreExtraProperties;


/**
 * Created by Raman Bindal on 02-Nov-16.
 */


import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class User {

    public String name;
    public String email;
    public String phone;
    public String password;
    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String name,String phone,String email,String password) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.email = email;
    }
}
