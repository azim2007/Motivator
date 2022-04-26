package com.example.tst;

import android.provider.ContactsContract;

public class User { //собственно класс юзера, не добавил почту тк зачем оно надо, если мы их не сохраняем в бд?
    private String name;
    private int starsCount;
    public User(){}

    public User (String name){
        this.name = name;
        this.starsCount = 0;
    }
    //звездочка(назвал звездочкой, тк не знаю как медаль на англиканском) дается за пройденную цель
    public void GetStarToUser (){
        starsCount++;
    }
}
