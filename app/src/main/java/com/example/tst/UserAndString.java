package com.example.tst;

import java.io.Serializable;

public class UserAndString implements Serializable {
    private User user;

    public User getUser() {
        return user;
    }

    public String getString() {
        return string;
    }

    private String string;
    public UserAndString(User user, String string){
        this.user = user;
        this.string = string;
    }
}
