package com.example.tst;

import android.provider.ContactsContract;

import java.io.Serializable;

public class User implements Serializable { //собственно класс юзера, не добавил почту тк зачем оно надо, если мы их не сохраняем в бд?
    private String name;

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getStarsCount() {
        return starsCount;
    }

    private String login;
    private String password;
    private int starsCount;
    public User(){}

    public User (String name, String login, String password){
        this.name = name;
        this.login = login;
        this.password = password;
        this.starsCount = 0;
    }
    //звездочка(назвал звездочкой, тк не знаю как медаль на англиканском) дается за пройденную цель
    public void GetStarToUser (){
        starsCount++;
    }
}
