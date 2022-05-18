package com.example.tst;

import android.util.Log;
import android.util.MalformedJsonException;
import android.util.Pair;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.internal.Sleeper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//класс для работы с БД
public class Pusher {
    private DatabaseReference database;//ссылка на БД, инициализируется в конструкторе(стр. 19)
    private int countOfTargets; // переменная, хранящая общее количество целей, доступных в БД
    private int countOfUsers; // переменная, хранящая общее количество юзеров, доступных в БД
    private String scountOfTargets = "CountOfTargets"; //название ветки БД, где хранится кол-во целей
    private String scountOfUsers = "CountOfUsers"; //название ветки БД, где хранится кол-во юзеров
    private String starget = "Target"; // название ветки БД, где хранятся цели
    private String suser = "User"; // название ветки БД, где хранятся юзеры
    private Target buf = new Target(); // буфер обмена цели (используется в Get функциях)
    private User thisUser; // буфер обмена юзера (используется в Get функциях)

    public User getThisUser() {
        return thisUser;
    }

    private boolean IsUserUnique = true; // проверка по логину

    public boolean isUserUnique() {
        return IsUserUnique;
    }

    private List<String> targetsNames = new ArrayList<>();

    public List<String> getTargetsNames() {
        return targetsNames;
    }

    public Target getBuf() {
        return buf;
    }

    public Pusher(){
        database = FirebaseDatabase.getInstance().getReference();
        // стр. 21 - 38: получение инфы о кол-ве целей
        database.child(scountOfTargets).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.i("Azim","firebase" + "Error getting data" + task.getException());
                }
                else {
                    Log.i("Azim", "firebase" + String.valueOf(task.getResult().getValue()));
                    try{
                        countOfTargets = Integer.parseInt(String.valueOf(task.getResult().getValue()));
                    }
                    catch (Exception e){
                        countOfTargets = 0;
                        database.child(scountOfTargets).setValue(countOfTargets);
                    }
                }
            }
        });

        database.child(scountOfUsers).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.i("Azim","firebase" + "Error getting data" + task.getException());
                }
                else {
                    Log.i("Azim", "firebase" + String.valueOf(task.getResult().getValue()));
                    try{
                        countOfUsers = Integer.parseInt(String.valueOf(task.getResult().getValue()));
                    }
                    catch (Exception e){
                        countOfUsers = 0;
                        database.child(scountOfUsers).setValue(countOfUsers);
                    }
                }
            }
        });
        System.out.println("countOfTargets: " + countOfTargets);
    }
    public void PushTarget(Target t){// запушить цель в БД
        database.child(starget).child("" + countOfTargets).setValue(t);
        countOfTargets++;
        database.child(scountOfTargets).setValue(countOfTargets);
    }

    public void UpdateTargetByID(int id) {//получить цель по порядковому номеру в БД, в буфер, после чего из буфера можно получить в теле другой функции с помощью геттера
        //получение цели по id
        database.child(starget).child("" + id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    System.out.println("firebase" + "Error getting data" + task.getException());
                }
                else {
                    System.out.println("firebase " + task.getResult().getValue(Target.class).getName() + " " + task.getResult().getValue(Target.class).getSteps());
                    buf = task.getResult().getValue(Target.class);
                    System.out.println("buf = " + buf.getName() + " " + buf.getSteps());
                }
            }
        });
    }

    public void UpdateNameOfTargets(String substr){//вывод названия всех целей из БД содержаших substr в буфер, после чего из буфера можно получить в теле другой функции с помощью геттера
        targetsNames.clear();
        for (int i = 0; i < countOfTargets; i++){
            database.child(starget).child("" + i).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        System.out.println("firebase" + "Error getting data" + task.getException());
                    }
                    else {
                        //System.out.println("firebase " + task.getResult().getValue(Target.class).getName() + " " + task.getResult().getValue(Target.class).getSteps());
                        String targetName = task.getResult().getValue(Target.class).getName();
                        if(targetName.contains(substr)){
                            targetsNames.add(targetName);
                        }
                        //System.out.println("buf = " + buf.getName() + " " + buf.getSteps());
                    }
                }
            });
        }
    }

    public void UpdateTargetByName(String name) {//получить цель по названию в БД, в буфер, после чего из буфера можно получить в теле другой функции с помощью геттера
        //получение цели по названию
        Log.i("Azim", "поиск цели по имени " + countOfUsers);
        for (int i = 0; i < 9; i++){
            database.child(starget).child("" + i).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        System.out.println("firebase" + "Error getting data" + task.getException());
                    }
                    else {
                        System.out.println("try to find by Name res '" + name + "' '" + task.getResult().getValue(Target.class).getName() + "'");
                        if(task.getResult().getValue(Target.class).getName().equals(name)){//через == не работает(опять магия)
                            System.out.println("find by Name res" + task.getResult().getValue(Target.class).getName() + " " + task.getResult().getValue(Target.class).getSteps().get(0).get(0).getDescription());
                            buf = task.getResult().getValue(Target.class);
                        }
                    }
                }
            });
        }
    }

    public void PushUser(User user){// запушить юзера в БД
        database.child(suser).child("" + countOfUsers).setValue(user);
        countOfUsers++;
        database.child(scountOfUsers).setValue(countOfUsers);
    }

    public void UpdateThisUser(String login, String password) {//получить юзера
        //получение цели по названию
        for (int i = 0; i < countOfUsers; i++){
            database.child(suser).child("" + i).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        System.out.println("firebase" + "Error getting data" + task.getException());
                    }
                    else {
                        if(task.getResult().getValue(User.class).getLogin().equals(login) && task.getResult().getValue(User.class).getPassword().equals(password)){//через == не работает(опять магия)
                            thisUser = task.getResult().getValue(User.class);
                        }
                    }
                }
            });
        }
    }
}
